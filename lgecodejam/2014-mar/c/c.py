def process(target, other):
    result = [[] for ch in target]
    ret = []
    for xi, xv in enumerate(target):
        for yi, yv in enumerate(other):
            if xv != yv:
                result[xi].append(0)
            elif 0 == xi or 0 == yi:
                result[xi].append(1)
            else:
                result[xi].append(result[xi-1][yi-1]+1)
        ret.append(max(result[xi]))
    return ret
 
 
def find_shortest(word_length, sub_map):
 
    for l in range(1, word_length+1):
#        print "LEN: ", l
        for pos in range(l-1, word_length):
#            print "POS: ", pos
            flag = True
            for other in sub_map:
#                print l, other[pos]
                if l <= other[pos]:
                    flag = False
                    break
            if flag:
                return l
 
 
def solve(n, word_list):
 
    for (xi, xv) in enumerate(word_list):
        result = []
        for (yi, yv) in enumerate(word_list):
            if (xv != yv):
                result.append(process(xv, yv))
#        print xv, len(xv), result
        print find_shortest(len(xv), result)
 
 
if __name__ == '__main__':
    N = int(raw_input())
    WORD = []
    for n in xrange(N):
        WORD.append(raw_input().strip())
    solve(N, WORD)
