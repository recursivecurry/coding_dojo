func compress(chars []byte) int {
	if len(chars) <= 0 {
		return len(chars)
	}
	var prev = chars[0]
	var last int
	var count = 1
	for i:=1; i<len(chars); i++ {
		if chars[i] != prev {
			chars[last] = prev
			last++
			if count > 1 {
				ns := []byte(strconv.Itoa(count))
				for _, d := range ns {
					chars[last] = d
					last++
				}
			}
			prev = chars[i]
			count=1
		} else {
			count++
		}
	}
	chars[last] = chars[len(chars)-1]
	last++
	if count > 1 {
		ns := []byte(strconv.Itoa(count))
		for _, d := range ns {
			chars[last] = d
			last++
		}
	}
	return last
}
