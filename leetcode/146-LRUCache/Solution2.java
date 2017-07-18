public class LRUCache {

    int capacity;
    Map<Integer, Item> map = new HashMap<Integer, Item>();
    Item head = null, tail = null;
    
    class Item {
        public int key;
        public int value;
        public Item prev;
        public Item next;

        public Item(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Item item = map.get(key);
            remove(item);
            setHead(item);
            return item.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Item item = map.get(key);
            item.value = value;
            remove(item);
            setHead(item);
        } else {
            Item item = new Item(key, value);
            if (map.size() >= capacity) {
                map.remove(tail.key);
                remove(tail);
                setHead(item);
            } else {
                setHead(item);
            }
            map.put(key, item);
        }  
    }
    private void remove(Item item) {
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

    private void setHead(Item item) {
        item.next = head;
        item.prev = null;

        if (head != null)
            head.prev = item;
        head = item;

        if (tail == null)
            tail = head;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
