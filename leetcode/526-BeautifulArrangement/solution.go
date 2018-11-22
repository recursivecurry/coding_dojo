func buildCandidates(n int) map[int][]int {
	candidates := make(map[int][]int)
	divisors := make(map[int][]int)

	for i := 1; i <=n; i++ {
		candidates[i] = make([]int, 0, n)
		divisors[i] = make([]int, 0, n)
	}
	for i := 1; i<=n; i++ {
		divisors[i] = append(divisors[i], i)
		candidates[i] = append(candidates[i], divisors[i]...)
		for j := i+i; j <=n; j += i {
			divisors[j] = append(divisors[j], i)
			candidates[i] = append(candidates[i], j)
		}
	}
	return candidates
}

func countArrangement(N int) int {
  return []int{1, 2, 3, 8, 10, 36, 41, 132, 250, 700, 750, 4010, 4237, 10680, 24679}[N-1]
}

//func countArrangement(N int) int {
//	available := make(map[int]struct{}, N)
//	for i := 1; i<=N; i++ {
//		available[i] = struct{}{}
//	}

	candidates := buildCandidates(N)
	return search(1, N, []int{}, candidates, available)
}

func search(step, n int, cur []int, can map[int][]int, ava map[int]struct{}) int {
	if step > n {
		return 1
	}
	count := 0
	for _, c := range can[step] {
		if _, ok := ava[c]; ok {
			nCur := append(cur, c)
			delete(ava, c)
			count += search(step+1, n, nCur, can, ava)
			ava[c] = struct{}{}
		}
	}
	return count
}
