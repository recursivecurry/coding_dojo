func pushDominoes(dominoes string) string {
  current := []rune(dominoes)
  next := []rune(dominoes)
  changed := true
  
  for changed {
    changed = false
    for i, r := range current {
      switch r {
        case 'L', 'R':
        next[i] = r
        default:
        if 0 < i && i < len(current)-1 && current[i-1] == 'R' && current[i+1] == 'L' {
          next[i] = r
        } else if 0 < i && current[i-1] == 'R' {
          next[i] = 'R'
          changed = true
        } else if i < len(current)-1 && current[i+1] == 'L' {
          next[i] = 'L'
          changed = true
        } else {
          next[i] = r
        }
      }
    }
    current, next = next, current
  }
  return string(current)
}
