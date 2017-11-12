package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
	"strconv"
)

func main() {
	var n, k int
	var nAndK []string
	scanner := bufio.NewScanner(os.Stdin)
	if scanner.Scan() {
		nAndK = strings.Split(scanner.Text(), " ")
	}
	if n64, err := strconv.ParseInt(nAndK[0], 0, 0); err == nil {
		n = int(n64)
	}
	if k64, err := strconv.ParseInt(nAndK[1], 0, 0); err == nil {
		k = int(k64)
	}

	var ass []string
	if scanner.Scan() {
		ass = strings.Split(scanner.Text(), " ")
	}
	as := make([]int, len(ass))
	for i, a := range ass {
		if n, err := strconv.ParseInt(a, 0, 32); err == nil {
			as[i] = int(n)
		}
	}
    fmt.Fprintln(os.Stderr, n, k, as)
	ans := 0
	for i, a1 := range as {
		for _, a2 := range as[i+1:] {
			if (a1+a2)%k == 0 {
				ans += 1
			}
		}
	}
	fmt.Println(ans)
}
