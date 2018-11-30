package main

import (
	"container/heap"
	"fmt"
)

type sub struct {
	last   int
	length int
}

type subs []sub

func (s subs) Len() int { return len(s) }

func (s subs) Less(i, j int) bool {
	if s[i].last == s[j].last {
		return s[i].length < s[j].length
	}
	return s[i].last < s[j].last
}

func (s subs) Swap(i, j int) { s[i], s[j] = s[j], s[i] }

func (s *subs) Push(x interface{}) {
	*s = append(*s, x.(sub))
}

func (s *subs) Pop() interface{} {
	old := *s
	n := len(old)
	x := old[n-1]
	*s = old[:n-1]
	return x
}

func isPossible(nums []int) bool {
	hp := &subs{}
	for _, n := range nums {
		for hp.Len() > 0 && (*hp)[0].last+1 < n {
			if (*hp)[0].length < 3 {
				return false
			} else {
				heap.Pop(hp)
			}
		}
		if hp.Len() > 0 && (*hp)[0].last+1 == n {
			s := heap.Pop(hp).(sub)
			s.last++
			s.length++
			heap.Push(hp, s)
		} else {
			heap.Push(hp, sub{n, 1})
		}
	}
	for _, s := range *hp {
		if s.length < 3 {
			return false
		}
	}
	return true
}

func main() {
	fmt.Println(isPossible([]int{1, 2, 3, 3, 4, 5}))
}
