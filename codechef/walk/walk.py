# CODECHEF - Walk
# http://www.codechef.com/MARCH14/problems/WALK

if __name__ == '__main__':
    T = int(raw_input())
    for count in xrange(T):
        V = 0
        N = int(raw_input())
        W = [int(w) for w in raw_input().split()]
        for n in xrange(N-1, -1, -1):
            V+=1
            if V < W[n]:
                V = W[n]
        print V
