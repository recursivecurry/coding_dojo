func validUtf8(data []int) bool {
  var s State
  for _, c := range data {
    if !s.input(c) {
      return false
    }
  }
  if s.tp != 0 {
    return false
  }
  return true
}

type State struct {
  tp int
  pos int
}

func (s *State) input(c int) bool {
  if s.tp == 0 {
    if c & 0x80 == 0 {
      return true
    } else if c & 0xe0 == 0xc0 {
      s.tp = 1
      s.pos++
      return true
    } else if c & 0xf0 == 0xe0 {
      s.tp = 2
      s.pos++
      return true
    } else if c & 0xf8 == 0xf0 {
      s.tp = 3
      s.pos++
      return true
    }
  } else {
    if c & 0xc0 == 0x80 {
      if s.pos == s.tp {
        s.tp = 0
        s.pos = 0
      } else {
        s.pos++
      }
      return true
    }
  }
  return false
}
