func numUniqueEmails(emails []string) int {
  set := make(map[string]struct{})
  for _, email := range emails {
    var sb strings.Builder
    state := 0
    for _, c := range email {
      if c == '.' && state == 0{
        continue
      } else if c == '@' {
        state = 2
      } else if c == '+' {
        state = 1
        continue
      } else if state == 1 {
        continue
      }
      sb.WriteRune(c)
    }
    set[sb.String()] = struct{}{}
  }
    return len(set)
}
