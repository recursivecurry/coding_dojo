if __name__ == "__main__":
    edges = [int(e) for e in input().split(' ')]
    big_triangle = sum(edges[2:5]) ** 2
    small_triangle1 = edges[0] ** 2
    small_triangle2 = edges[2] ** 2
    small_triangle3 = edges[4] ** 2
    print(big_triangle - small_triangle1 - small_triangle2 - small_triangle3)
