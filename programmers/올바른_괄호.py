def solution(s):
    stack = []
    for c in s:
        if c == '(':
            stack.append(c)
        else:
            if not stack:
                return False
            stack.pop()

    return len(stack) == 0

import unittest

class TestSolution(unittest.TestCase):
    def test(self):
        self.assertEqual(solution("()()"), True)
        self.assertEqual(solution("(())()"), True)
        self.assertEqual(solution(")()("), False)
        self.assertEqual(solution("(()("), False)

if __name__ == '__main__':
    unittest.main()