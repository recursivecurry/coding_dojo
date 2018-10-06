func isMatch2(s string, p string) bool {
	if len(p) == 0 {
		return len(s) == 0
	}

	matched := len(s) != 0 && (p[0] == '.' || s[0] == p[0])
	if len(p) >= 2 && p[1] == '*' {
		return isMatch(s, p[2:]) || (matched && isMatch(s[1:], p))
	} else {
		return matched && isMatch(s[1:], p[1:])
	}
}

func isMatch(s string, p string) bool {
	var dp = make([][]bool, len(s)+1)
	for i := 0; i<len(s)+1; i++ {
		dp[i] = make([]bool, len(p)+1)
	}
	dp[len(s)][len(p)] = true

	for i := len(s); i >= 0; i-- {
		for j := len(p)-1; j >= 0; j-- {
			matched := i < len(s) && (s[i] == p[j] || p[j] == '.')
			if j + 1 < len(p) && p[j+1] == '*' {
				dp[i][j] = dp[i][j+2] || (matched && dp[i+1][j])
			} else {
				dp[i][j] = matched && dp[i+1][j+1]
			}
		}
	}
	return dp[0][0]
}
