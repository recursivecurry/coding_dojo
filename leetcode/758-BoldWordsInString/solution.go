func boldWords(words []string, S string) string {
	bold := make([]bool, len(S))
	var bolded strings.Builder
	for i, s := range S {
		for _, w := range words {
			if strings.HasPrefix(S[i:], w) {
				for j := 0; j < len(w); j++ {
					bold[i+j] = true
				}
			}
		}
		if (i == 0 && bold[i]) || (i != 0 && !bold[i-1] && bold[i]) {
			bolded.WriteString("<b>")
			bolded.WriteRune(s)
		} else if (i != 0 && bold[i-1] && !bold[i]) {
			bolded.WriteString("</b>")
			bolded.WriteRune(s)
		} else {
			bolded.WriteRune(s)
		}
	}
    if (bold[len(S)-1]) {
		bolded.WriteString("</b>")
	}
	return bolded.String()
}
