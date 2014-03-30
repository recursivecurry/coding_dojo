def solve(arects, irects, area, prev, xlist, ret):
    if (0==len(irects) and 1==len(arects)):
        prev_area = area.items()[0]
        return (ret[0], max(ret[1], (ylist[0]-prev)*(prev_area[0][1]-prev_area[0][0])+prev_area[1])
    active = arects[0]
    rect = irects[0]
    rects = irects[1:]
    if (irects[0][0] < xlist[0]):
        # 다음 사각형이 왔음
        # 이전 영역들에 대해서 넓이를 계산해서 추가하고
        # 공간을 y축에 대하여 영역을 나누어서 영역목록에 추가/삭제
        nextx = irects[0][0]
        # 재귀
    else:
        # 이전 사각형이 끝났음.
        # 이전 영역들에 대해서 넓이를 계산해서 추가
        # 공간을 y축에 대하여 영역을 나누어서 영역목록에 추가/삭제
        nextx = xlist[0]
        # 재귀

if __name__ == '__main__':
    rectNum = int(raw_input())
    rect_list = []
    for i in xrange(rectNum):
        (x1,y1,x2,y2) = [int(n) for n in raw_input().split()]
        rect_list.insert(0, (x1,y1,x2,y2))
    count, size = solve(rect_list[0], rect_list[1:], {(rect_list[0][1],rect_list[0][3]):0}, rect_list[0][0], [rect_list[0][2]], (0,0))
    print count, size