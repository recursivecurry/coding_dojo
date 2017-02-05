def spiralOrder(matrix):
    return matrix and list(matrix[0]) + spiralOrder(list(zip(*matrix[1:]))[::-1])
