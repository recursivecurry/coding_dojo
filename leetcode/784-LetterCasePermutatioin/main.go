func letterCasePermutation(S string) []string {
	current := [][]rune{{}}
	for _, c := range S {
		next := [][]rune{}
		if unicode.IsLetter(c) {
			for _, cur := range current {
				next = append(next, append(append([]rune{}, cur...), unicode.ToLower(c)))
				next = append(next, append(append([]rune{}, cur...), unicode.ToUpper(c)))
			}
		} else {
			for _, cur := range current {
				next = append(next, append(append([]rune{}, cur...), c))
			}
		}
		current = next
	}
	result := make([]string, 0, len(current))
	for _, bs := range current {
		result = append(result, string(bs))
	}
	return result
}
