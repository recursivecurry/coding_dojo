func convert(s string, numRows int) string {
  var sb strings.Builder
  if numRows == 1 {
    return s
  }
  for r:=0; r<numRows && r < len(s); r++ {
    p := r
    sb.WriteByte(s[p])
    s1, s2 := step(r, numRows)
    for {
      if s1 != 0 {
        p += s1
        if p < len(s) {
          sb.WriteByte(s[p])
        } else {
          break
        }
      }
      if s2 != 0 {
        p += s2
        if p < len(s) {
          sb.WriteByte(s[p])
        } else {
          break
        }
      }
    }
  }
  return sb.String()
}

func step(i, n int) (int, int) {
  dis := (n-1) * 2
  return dis-i*2, i*2
}
