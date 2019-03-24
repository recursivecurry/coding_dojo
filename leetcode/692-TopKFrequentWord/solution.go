import "container/heap"

type Word struct {
  word string
  count int
}

type WordHeap []Word

func (w WordHeap) Len() int {
  return len(w)
}

func (w WordHeap) Less(i, j int) bool {
  if w[i].count == w[j].count {
    return w[i].word < w[j].word
  } else {
    return w[i].count > w[j].count
  }
}

func (w WordHeap) Swap(i, j int) {
  w[i], w[j] = w[j], w[i]
}

func (w *WordHeap) Push(x interface{}) {
  *w = append(*w, x.(Word))
}

func (w *WordHeap) Pop() interface{} {
  old := *w
  n := len(old)
  x := old[n-1]
  *w = old[0:n-1]
  return x
}

func topKFrequent(words []string, k int) []string {
  counter := map[string]int{}
  for _, w := range words {
    c, _ := counter[w]
    counter[w] = c + 1
  }
  
  wh := &WordHeap{}
  for w, c := range counter {
    heap.Push(wh, Word{w, c})
  }
  ret := make([]string, 0, k)
  for i:=0; i<k; i++ {
    ret = append(ret, heap.Pop(wh).(Word).word)
  }
  return ret
}
