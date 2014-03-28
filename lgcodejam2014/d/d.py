import math
import itertools
 
def solve(width, text_list, length_list, length,):
    for rownum in xrange(1, length):
        sum = 0
        colnum = int(math.ceil(float(length)/rownum))
        max_list = []
        for n in xrange(colnum):
            colmax = max(length_list[rownum*n:rownum*(n+1)])
            max_list.append(colmax)
            sum += colmax
            if sum > width:
                break;
        if sum <= width:
            return rownum, colnum, max_list
 
def pad_underbar(word, max_length):
    if None == word:
        word = ""
    underbar = ('_' for x in xrange(max_length-len(word)))
    return word +"".join(underbar)
 
def answer(width, text_list, rownum, colnum, max_list):
    for row in xrange(rownum):
        line = "".join((pad_underbar(item[0], max_list[item[1]]) for item in list(itertools.izip_longest(text_list[row::rownum], xrange(colnum)))))
        print(pad_underbar(line, width))
 
if __name__ == '__main__':
    W, C = [int(x) for x in raw_input().split()]
    TEXT = []
    for n in xrange(C):
        TEXT.append(raw_input().strip())
    TEXT.sort()
    rownum, colnum, max_list =  solve(W, TEXT, [len(word)+2 for word in TEXT], len(TEXT))
    answer(W, TEXT, rownum, colnum, max_list)
