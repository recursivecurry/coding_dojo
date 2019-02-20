type HitCounter struct {
  heap []int
}

func (h HitCounter) Len() int           { return len(h.heap) }
func (h HitCounter) Less(i, j int) bool { return h.heap[i] < h.heap[j] }
func (h HitCounter) Swap(i, j int)      { h.heap[i], h.heap[j] = h.heap[j], h.heap[i] }

func (h *HitCounter) Push(x interface{}) {
	// Push and Pop use pointer receivers because they modify the slice's length,
	// not just its contents.
	h.heap = append(h.heap, x.(int))
}

func (h *HitCounter) Pop() interface{} {
	old := h.heap
	n := len(old)
	x := old[n-1]
	h.heap = old[0 : n-1]
	return x
}

/** Initialize your data structure here. */
func Constructor() HitCounter {
  return HitCounter{heap:make([]int, 0)}
}


/** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
func (this *HitCounter) Hit(timestamp int)  {
  heap.Push(this, timestamp)
}


/** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
func (this *HitCounter) GetHits(timestamp int) int {
  for {
    if this.Len() == 0 {
      break
    }
    v := heap.Pop(this).(int)
    if v > timestamp - 300 {
      heap.Push(this, v)
      break
    }
  }
  return this.Len()
}


/**
 * Your HitCounter object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Hit(timestamp);
 * param_2 := obj.GetHits(timestamp);
 */
