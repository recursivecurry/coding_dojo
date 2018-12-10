type Loop struct {
	begin int
	length int
	total int
}

func decodeAtIndex(S string, K int) string {
	K--
  loops := append(make([]Loop, 0, len(S)), Loop{0, 0, 0})
	start, length := 0, 0
	for i := 0; i<len(S); i++ {
		if isDigit(S[i]) {
			loops = append(loops, Loop{ begin:start, length:length, total:(loops[len(loops)-1].total+length)*int(S[i]-'0') })
			length = 0
		} else {
			if length == 0 {
				start = i
			}
			length++
		}
	}
	if !isDigit(S[len(S)-1]) {
		loops = append(loops, Loop{start, len(S)-start, loops[len(loops)-1].total+len(S)-start})
	}
	return string(S[find(loops, K)])
}

func find(loops []Loop, K int) int {
	for i, l := range loops {
		if K < l.total {
			next := K % (loops[i-1].total + l.length)
			if next < loops[i-1].total {
				return find(loops, next)
			} else {
				return l.begin + next - loops[i-1].total
			}
		}
	}
	return 0
}

func isDigit(b byte) bool {
	return '0' <= b && b <= '9'
}
