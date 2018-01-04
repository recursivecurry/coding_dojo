import (
	"unicode"
	"strings"
	"strconv"
	"fmt"
)

type Stack []string

func (s *Stack) Push(val string) {
	*s = append(*s, val)
}

func (s *Stack) Pop()string {
	n := len(*s)
	val := (*s)[n-1]
	*s = (*s)[0:n-1]
	return val
}

const (
	NONE = iota
	NUMBER
	TEXT
)

func DecodeString(s string) string {
	state := NONE
	stack := make(Stack, 0)
	for _, r := range s {
		if unicode.IsDigit(r) {
			if state == NUMBER {
				stack.Push(stack.Pop() + string(r))
			} else {
				stack.Push(string(r))
				state = NUMBER
			}
		} else if r == '[' {
			state = NONE
		} else if r == ']' {
			var text, word string
			var count int64
			var err error
			for i := len(stack); i >= 0; i-- {
				word = stack.Pop()
				count, err = strconv.ParseInt(word, 10, 64)
				if err == nil {
					break
				}
				text = word + text
			}
			stack.Push(strings.Repeat(text, int(count)))
			state = NONE
		} else {
			if state == TEXT {
				stack.Push(stack.Pop() + string(r))
			} else {
				stack.Push(string(r))
				state = TEXT
			}
		}
	}
	return strings.Join(stack, "")
}
