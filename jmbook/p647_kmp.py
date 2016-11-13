
def build_partial(text):
    length = len(text)
    pi = [0 for c in text]
    for base in range(1, length):
        for i in range(length-base):
            if text[base+i] != text[i]:
                break
            else:
                pi[base+i] = max(pi[base+i], i+1)
    return pi


def kmp(full, text):
    result = []
    full_length = len(full)
    length = len(text)
    pi = build_partial(text)
    begin = 0
    matched = 0
    while begin <= full_length - length:
        if matched < length and full[begin+matched] == text[matched]:
            matched += 1
            if matched == length:
                result.append(begin)
        else:
            if matched == 0:
                begin += 1
            else:
                begin += (matched - pi[matched-1])
                matched = pi[matched-1]

    return result
