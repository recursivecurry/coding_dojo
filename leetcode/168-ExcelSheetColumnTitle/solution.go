import (
	"fmt"
    "bytes"
)

func convertToTitle1(n int) string {
    b := convert(n)
    return b.String()
}

func convert(n int) bytes.Buffer {
	if n == 0 {
		return bytes.Buffer{}
	} else {
		b := convert((n-1)/26)
		b.WriteByte(byte((n-1)%26)+'A')
		return b
	}
}

func convertToTitle2(n int) string {
	n -= 1
	base := int('z') - int('a') + 1
	digit := 1
	max := base
	for n >= max {
		n -= max
		digit += 1
		max *= base
	}
	max /= base
	result := make([]byte, digit)
	for i := 0; i < digit; i++ {
		result[digit-i-1] = byte(int('A') + n % base)
		n /= base
	}
	return string(result)
}
