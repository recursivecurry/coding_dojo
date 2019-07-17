import (
  "container/heap"
)
type Data struct {
  Tmp int
  Idx int
}
type DataHeap []Data

func (d DataHeap) Len() int {
  return len(d)
}

func (d DataHeap) Less(i, j int) bool {
  return d[i].Tmp < d[j].Tmp
}

func (d DataHeap) Swap(i, j int) {
  d[i], d[j] = d[j], d[i]
}

func (d *DataHeap) Push(x interface{}) {
  *d = append(*d, x.(Data))
}

func (d *DataHeap) Pop() interface{} {
  n := len(*d)
  ret := (*d)[n-1]
  *d = (*d)[0:n-1]
  return ret
}

func dailyTemperatures(T []int) []int {
  passed := new(DataHeap)
  ret := make([]int, len(T))
  for i, t := range T {
    for passed.Len() > 0 {
      if p := heap.Pop(passed).(Data); p.Tmp < t {
        ret[p.Idx] = i - p.Idx
      } else {
        heap.Push(passed, p)
        break
      }
    }
    heap.Push(passed, Data{Idx: i, Tmp: t})
  }
  for _, d := range *passed {
    ret[d.Idx] = 0
  }
  return ret
}
