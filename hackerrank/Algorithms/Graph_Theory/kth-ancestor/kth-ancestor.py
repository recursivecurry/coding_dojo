from collections import deque, defaultdict

class Node:
    def __init__(self, name, level, parent):
        self.name = name
        self.level = level
        self.ancestor = [parent] if parent is not None else []
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

    def __repr__(self):
        return "{{name: {}, level: {}, ancestor: {}}}".format(self.name, self.level, [a.name for a in self.ancestor if a is not None])


def build_tree(edges):
    root = Node(edges.pop(0).pop(), 0, None)
    tree = {root.name: root}
    next_queue = deque([root])
    while next_queue:
        current = next_queue.popleft()
        for child in edges[current.name]:
            node = Node(child, current.level+1, current)
            tree[child] = node
            next_queue.append(node)
    return tree


def interpret(tree, *args):
    if args[0] == 0:
        tree[args[2]] = Node(args[2], tree[args[1]].level+1, tree[args[1]])
    elif args[0] == 1:
        tree.pop(args[1])
    elif args[0] == 2:
        ancestor = tree[args[1]].kth_ancestor(args[2]) if args[1] in tree.keys() else None
        print(0 if ancestor is None else ancestor.name)


def main():
    T = int(input())
    for _ in range(T):
        P = int(input())

        edges = defaultdict(list)
        for _ in range(P):
            X, Y = tuple(int(v) for v in input().split())
            edges[Y].append(X)

        tree = build_tree(edges)

        Q = int(input())
        for _ in range(Q):
            interpret(tree, *tuple(int(v) for v in input().split()))


if __name__ == "__main__":
    main()
