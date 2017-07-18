import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Item<K, V> {
    public K key;
    public V value;
    public Item<K, V> prev;
    public Item<K, V> next;

    public Item(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache<K, V> {
    int capacity;
    Map<K, Item<K, V>> map = new HashMap<K, Item<K, V>>();
    Item<K, V> head = null, tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(int key) {
        if (map.containsKey(key)) {
            Item<K, V> item = map.get(key);
            remove(item);
            setHead(item);
            return item.value;
        }
        return null;
    }

    private void remove(Item<K, V> item) {
        if (item.prev != null) {
            item.prev.next = item.next;
        } else {
            head = item.next;
        }
        if (item.next != null) {
            item.next.prev = item.prev;
        } else {
            tail = item.prev;
        }
    }

    private void setHead(Item<K, V> item) {
        item.next = head;
        item.prev = null;

        if (head != null)
            head.prev = item;
        head = item;

        if (tail == null)
            tail = head;
    }

    public void set(K key, V value) {
        if (map.containsKey(key)) {
            Item item = map.get(key);
            item.value = value;
            remove(item);
            setHead(item);
        } else {
            Item item = new Item(key, value);
            if (map.size() > capacity) {
                map.remove(tail.key);
                remove(tail);
                setHead(item);
            } else {
                setHead(item);
            }
            map.put(key, item);
        }
    }
}


class LRUCacheWithLibrary<K, V> {
    private static final float hashTableLoadFactor = 0.75f;

    private Map<K, V> map;
    private int cacheSize;

    public LRUCacheWithLibrary(int cacheSize) {
        this.cacheSize = cacheSize;
        int hashTableCapacity = (int) Math.ceil(cacheSize / hashTableLoadFactor) + 1;
        map = Collections.synchronizedMap(new LinkedHashMap<K, V>(hashTableCapacity, hashTableLoadFactor, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCacheWithLibrary.this.cacheSize;
            }
        });
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

}
