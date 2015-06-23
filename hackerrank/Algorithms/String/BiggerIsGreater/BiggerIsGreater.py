import itertools

def solve(word):
	rev = "".join(reversed(word))
	for pos_ch, ch in enumerate(rev[1:], 1):
		passed = rev[:pos_ch]
		for pos_pch, pch in enumerate(passed):
			if ch < pch:
				prefix = ''.join(sorted(rev[:pos_pch] + rev[pos_pch+1:pos_ch+1], reverse=True))
				suffix = rev[pos_ch+1:]
				result = prefix + pch + suffix
				return "".join(reversed(result))
	return "no answer"


if __name__ == "__main__":
	for ans in [input() for x in range(int(input()))]:
		print(solve(ans))