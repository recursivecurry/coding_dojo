#!/usr/bin/env python3

patterns = [((0, 1), (1, 0)),
            ((0, 1), (1, 1)),
            ((1, 0), (1, 1)),
            ((1, -1), (1, 0))]


def find_empty(board):
    for r, row in enumerate(board):
        for c, status in enumerate(row):
            if status == '.':
                return True, r, c
    return False, None, None


def try_put(board, r, c, p):
    try:
        if board[r+p[0][0]][c+p[0][1]] == '.' \
           and board[r+p[1][0]][c+p[1][1]] == '.':
            board[r][c] = '#'
            board[r+p[0][0]][c+p[0][1]] = '#'
            board[r+p[1][0]][c+p[1][1]] = '#'
            return True
        else:
            return False
    except IndexError:
        return False


def recover(board, r, c, p):
    board[r+p[0][0]][c+p[0][1]] = '.'
    board[r+p[1][0]][c+p[1][1]] = '.'
    board[r][c] = '.'


def count_solution(board):
    success, r, c = find_empty(board)

    if not success:
        return 1

    count = 0
    for p in patterns:
        if try_put(board, r, c, p):
            count += count_solution(board)
            recover(board, r, c, p)

    return count


def solve():
    T = int(input())
    for _ in range(T):
        R, C = tuple(int(n) for n in input().split())
        board = tuple(list(input()) for _ in range(R))
        print(count_solution(board))


if __name__ == '__main__':
    solve()
