import itertools as itl

correct = set(range(1,10))

def three(l):
	return [l[:3],l[3:6],l[6:9]]

def check(s):
	for r in s:
		if set(r) != correct:
			return False
	for c in zip(*s):
		if set(c) != correct:
			return False
	for r in three(s):
		for m in three(list(zip(*r))):
			ms = list(itl.chain(*m))
			if set(ms) != correct:
				return False
	return True

def solve(n, s):
	print("Case #{0}:".format(n+1))
	if check(s):
		print("Serendipity")
	else:
		for p in [((r1,c1),(r2,c2)) for r1 in range(9) for c1 in range(9) for r2 in range(r1,9) for c2 in range(c1,9)]:
			s[p[0][0]][p[0][1]], s[p[1][0]][p[1][1]] = s[p[1][0]][p[1][1]], s[p[0][0]][p[0][1]]
			if check(s):
				print("({0[0]},{0[1]}) <-> ({1[0]},{1[1]})".format((p[0][0]+1,p[0][1]+1), (p[1][0]+1,p[1][1]+1)))
			s[p[0][0]][p[0][1]], s[p[1][0]][p[1][1]] = s[p[1][0]][p[1][1]], s[p[0][0]][p[0][1]]


[solve(n, [[int(v) for v in input().split(' ')] for l in range(9)]) for n in range(int(input()))]