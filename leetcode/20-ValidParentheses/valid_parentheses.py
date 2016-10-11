class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        stack = []
        for ch in s:
            if ch in '([{':
                stack.append(ch)
            elif ch in ')]}':
                if len(stack) > 0 and ((ch == ']' and stack[-1] == '[') or (ch == ')' and stack[-1] == '(') or (ch == '}' and stack[-1] == '{')):
                    stack = stack[:-1]
                else:
                    return False
        return len(stack) == 0
