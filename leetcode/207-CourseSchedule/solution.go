import (
	"fmt"
)

func canFinish(numCourses int, prerequisites [][]int) bool {
	passes := make(map[int]struct{})
	courses := make(map[int][]int)
	for i := 0; i < numCourses; i++ {
		courses[i] = make([]int, 0)
	}

	for _, prerequisite := range prerequisites {
		courses[prerequisite[0]] = append(courses[prerequisite[0]], prerequisite[1])
	}

	flag := true
	for flag {
		flag = false
		for k, v := range courses {
			newPre := make([]int, 0)
			for _, pre := range v {
				if _, ok := passes[pre]; !ok {
					newPre = append(newPre, pre)
				} else {
					flag = true
				}
			}
			if len(newPre) == 0 {
				delete(courses, k)
				passes[k] = struct{}{}
				flag = true
			} else {
				courses[k] = newPre
			}
		}
	}

	return len(courses) == 0
}
