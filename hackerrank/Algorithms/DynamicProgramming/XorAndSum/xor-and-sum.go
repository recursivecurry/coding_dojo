package main

import (
	"fmt"
)

const (
	MOD int64 = 1e9 + 7
	N   int   = 314159
)

var counts []int

func countZeroOne(i int, n int) (int, int) {
	e := i + 1
	if e >= len(counts) {
		e = len(counts) - 1
	}
	var b int
	if n >= i {
		b = 0
	} else {
		b = i - n
	}
	one := counts[e] - counts[b]
	return i + 1 - one, one
}

func main() {
	var a, b string
	fmt.Scanf("%s", &a)
	fmt.Scanf("%s", &b)
	al := len(a)
	bl := len(b)

	counts = make([]int, bl+1)

	for i := range b {
		if b[bl-1-i] == '1' {
			counts[i+1] = counts[i] + 1
		} else {
			counts[i+1] = counts[i]
		}
	}

	var f int64 = 1
	var ans int64 = 0
	maxA := al
	if al < bl+N {
		maxA = bl + N
	}

	for i := 0; i < maxA; i++ {
		var ai int64
		if i >= al {
			ai = 0
		} else {
			if a[al-1-i] == '1' {
				ai = 1
			} else {
				ai = 0
			}
		}

		var ti int
		_, one := countZeroOne(i, N)

		if ai == 0 {
			ti = one
		} else {
			ti = N + 1 - one
		}
		ans = (ans + (f * int64(ti))) % MOD
		f = (f << 1) % MOD
	}
	fmt.Println(ans)
}
