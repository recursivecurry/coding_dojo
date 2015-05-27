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
		for p in [(p0, p1) for p0 in range(81) for p1 in range(p0, 81)]:
			x1, y1, x2, y2 = p[0] // 9, p[0] % 9, p[1] // 9, p[1] % 9
			s[x1][y1], s[x2][y2] = s[x2][y2], s[x1][y1]
			if check(s):
				print("({},{}) <-> ({},{})".format(x1+1, y1+1, x2+1, y2+1))
			s[x1][y1], s[x2][y2] = s[x2][y2], s[x1][y1]

[solve(n, [[int(v) for v in input().split(' ')] for l in range(9)]) for n in range(int(input()))]