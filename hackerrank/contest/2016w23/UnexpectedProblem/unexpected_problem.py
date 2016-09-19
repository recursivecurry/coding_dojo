
def pattern(inputv):

    print(inputv)

    nxt = [0]*len(inputv)
    for i in range(1, len(nxt)):
        print("i: ", i)
        print("nxt: ", nxt)
        k = nxt[i - 1]
        while True:
            if inputv[i] == inputv[k]:
                print("first => i: ", i, " k: ", k)
                nxt[i] = k + 1
                break
            elif k == 0:
                print("second => i: ", i, " k: ", k)
                nxt[i] = 0
                break
            else:
                print("third => i: ", i, " k: ", k)
                k = nxt[k - 1]
                print
            print("nxt: ", nxt)

    print("nxt: ", nxt)
    smallPieceLen = len(inputv) - nxt[-1]
    if len(inputv) % smallPieceLen != 0:
        return inputv

    return inputv[0:smallPieceLen]

def main():
    s = input()
    m = int(input())
    pattern_length = len(pattern(s))
    print((m//pattern_length)%1000000007)

if __name__ == '__main__':
    main()
