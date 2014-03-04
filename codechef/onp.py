def transform(exp):
    stack = []
    result = ''
    for ch in exp:
        if ch == ')':
            result += stack.pop()
        elif ch == '(':
            continue
        elif ch in '+-*/^':
            stack.append(ch)
        else:
            result += ch
    return result
 
if __name__ == '__main__':
    total = int(raw_input())
    inputs = []
    for count in range(total):
        inputs.append(raw_input())
    results = []
    for exp in inputs:
        results.append(transform(exp))
    for result in results:
        print result 
