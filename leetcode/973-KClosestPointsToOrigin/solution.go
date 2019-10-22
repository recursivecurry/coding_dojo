type PointHeap [][]int

func (p PointHeap) Len() int {
    return len(p)
}

func (p PointHeap) Less(i, j int) bool {
    return p[i][0]*p[i][0] + p[i][1]*p[i][1] < p[j][0]*p[j][0] + p[j][1] * p[j][1]
}

func (h *PointHeap) Push(x interface{}) {
	*h = append(*h, x.([]int))
}

func (h *PointHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (p PointHeap)Swap(i, j int) {
    p[i], p[j] = p[j], p[i]
}

func kClosest(points [][]int, K int) [][]int {
    h := PointHeap(points)
    heap.Init(&h)
    r := make([][]int, 0, K)
    for i := 0; i < K; i++ {
        r = append(r, heap.Pop(&h).([]int))
    }
    return r
}
