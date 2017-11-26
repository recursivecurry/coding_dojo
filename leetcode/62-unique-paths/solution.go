var comb [][]int

func initCombination() {
    comb = make([][]int, 200)
	for i := 0; i < 200; i++ {
		comb[i] = make([]int, 200)
	}
}

func combination(m, n int) int {
	fmt.Println("combination: ", m, ", ", n)

	if m == n {
		return 1
	}
    
    if n == 0 {
        return 1
    }
    
    if m < n {
        return 0
    }

	if n > (m - n) {
		n = m - n
	}

    if comb[m][n] != 0 {
		return comb[m][n]
	}
    
	comb[m][n] = combination(m-1, n-1) + combination(m-1, n)

	return comb[m][n]
}

func uniquePaths(m int, n int) int {
    if comb == nil {
        initCombination()
    }
	return combination(m-1+n-1, m-1)
}
