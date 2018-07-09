func removeInvalidParentheses(s string) []string {
	result := map[string]int{"": 0}
	for _, c := range s {
		temp := map[string]int{}
		if c == '(' {
			for key, cnt := range result {
				temp[string(append([]rune(key), c))] = cnt+1
				temp[key] = cnt
			}
		} else if c == ')' {
			for key, cnt := range result {
				if cnt -1 >= 0 {
					temp[string(append([]rune(key), c))] = cnt-1
				}
				temp[key] = cnt
			}
		} else {
			for key, cnt := range result {
				temp[string(append([]rune(key), c))] = cnt
			}
		}
		result = temp
	}
	max := 0
	for key, cnt := range result {
		if cnt == 0 && len(key) >= max {
			max = len(key)
		}
	}
	answer := []string{}
	for key, cnt := range result {
		if cnt == 0 && len(key) == max {
			answer = append(answer, key)
		}
	}
	return answer
}
