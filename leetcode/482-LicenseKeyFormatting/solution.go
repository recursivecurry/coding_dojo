func licenseKeyFormatting(S string, K int) string {
  S = strings.Replace(S, "-", "", -1)
  var sb strings.Builder
  pos := K - len(S) % K
  for i, r := range S {
    if i != 0 && (i + pos) % K == 0 {
      sb.WriteRune('-')
      sb.WriteRune(unicode.ToUpper(r))
    } else {
      sb.WriteRune(unicode.ToUpper(r))
    }
  }
  return sb.String()
}
