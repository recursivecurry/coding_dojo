func find (parent []int, i int) int {
	if parent[i] != i {
		parent[i] = find(parent, parent[i])
	}
	return parent[i]
}

func findRedundantConnection(edges [][]int) []int {
	n := len(edges)
	parent := make([]int, n+1)
	rank := make([]int, n+1)
	for i := 0; i <= n; i++ {
		parent[i] = i
		rank[i] = 1
	}
	for _, edge := range edges {
		root0 := find(parent, edge[0])
		root1 := find(parent, edge[1])
		if root0 == root1 {
			return edge
		} else {
			if rank[root0] < rank[root1] {
				parent[root0] = root1
			} else if rank[root0] > rank[root1] {
				parent[root1] = root0
			} else {
				parent[root0] = parent[root1]
				rank[root1] += 1
			}
		}
	}
	return []int{}
}
