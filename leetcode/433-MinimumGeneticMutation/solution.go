import "math/bits"

func char2digit(r rune) uint {
    switch r {
    case 'A':
        return 1
    case 'C':
        return 2
    case 'G':
        return 4
    default:
        return 8
    }
}

func gene2int(g string) uint {
    var step uint = 1
    var i uint = 0
    for _, c := range g {
        i += (char2digit(c) * step)
        step <<=  4
    }
    return i
}

func isMutable(start, end uint) bool {
    diff := start ^ end
    return 2 == bits.OnesCount(diff)
}

func minMutation(start string, end string, bank []string) int {
    st := gene2int(start)
    ed := gene2int(end)
    bk := make([]uint, len(bank))
    for i := 0; i < len(bank); i++ {
        bk[i] = gene2int(bank[i])
    }
    current := []uint{st}
    step := 0
    for len(current) > 0 {
        next := []uint{}
        for _, c := range current {
            if c == ed {
                return step
            }
            nextBank := []uint{}
            for _, b := range bk {
                if isMutable(c, b) {
                    next = append(next, b)
                } else {
                    nextBank = append(nextBank, b)
                }
            } 
            bk = nextBank
        }
        current = next
        step++
    }
    return -1
}
