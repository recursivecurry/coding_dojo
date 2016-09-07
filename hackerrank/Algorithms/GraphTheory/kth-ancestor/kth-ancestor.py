class Node:
    def __init__(self, name, level, parent):
        self.name = name
        self.level = level
        self.ancestor = [] if parent is None else [parent]
        self.fill_ancestor()

    def fill_ancestor(self):
        number_of_link = self.level.bit_length()
        for i in range(1, number_of_link):
            self.ancestor.append(self.ancestor[i-1].kth_ancestor(2**(i-1)))

    def kth_ancestor(self, k):
        if k == 0:
            return self

        next_link = k.bit_length() - 1
        if next_link < len(self.ancestor):
            return self.ancestor[next_link].kth_ancestor(k - 2**next_link)
        else:
            return None

#    def __repr__(self):
#        return "{{name:{},level:{},ready:{},ancestor:{}}}".format(self.name,
#                                                                  self.level,
#                                                                  self.ready,
#                                                                  [a.name for a in self.ancestor if a is not None])


def main():
    T = int(input())
    for _ in range(T):
        P = int(input())

        X, Y = tuple(int(v) for v in input().split())
        root = Node(X, 0, None)
        tree = {root.name: root}
        for _ in range(P-1):
            X, Y = tuple(int(v) for v in input().split())
            node = Node(X, tree[Y].level+1, tree[Y])
            tree[X] = node
        Q = int(input())
        for _ in range(Q):
            cmd = tuple(int(v) for v in input().split())
            if cmd[0] == 0:
                tree[cmd[2]] = Node(cmd[2], tree[cmd[1]].level+1, tree[cmd[1]])
            elif cmd[0] == 1:
                tree.pop(cmd[1])
            elif cmd[0] == 2:
                ancestor = tree[cmd[1]].kth_ancestor(cmd[2]) if cmd[1] in tree.keys() else None
                print(0 if ancestor is None else ancestor.name)


if __name__ == "__main__":
    main()
