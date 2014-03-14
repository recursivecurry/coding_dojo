# CODECHEF - Prime Generator
# http://www.codechef.com/problems/PRIME1
final = {}
def exchange(n):
    if n == 0:
        return 0
    elif n in final:
        return final[n]
    more = exchange(n/4)+exchange(n/3)+exchange(n/2)
    if n < more:
        final[n] = more
        return more
    else:
        final[n] = n
        return n
 
if __name__ == '__main__':
    result = []
    for i in xrange(10):
        num = raw_input()
        if 0==len(num):
            break
        result.append(exchange(int(num)))
 
    for r in result:
        print r 
