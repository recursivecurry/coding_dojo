def solution(progresses, speeds):
    import math
    jobs = []
    for i in range(len(progresses)):
        jobs.append(math.ceil((100-progresses[i])//speeds[i]))
    print(jobs)
    prev = 0
    answer = []
    for i in range(1, len(jobs)):
        if jobs[i] > jobs[prev]:
            answer.append(i-prev)
            prev = i
            print("answer: ", answer, prev)
    print(prev)
    if prev != len(jobs)-1:
        answer.append(len(jobs)-prev)
        print("answer2:", answer, prev)
    print(answer)
    return answer

import unittest

class TestSolution(unittest.TestCase):
    def test(self):
        self.assertEqual(solution([93, 30, 55], [1, 30, 5]), [2, 1])
        self.assertEqual(solution([95, 90, 99, 99, 80, 99], [1,1,1,1,1,1]), [1,3,2])

if __name__ == '__main__':
    unittest.main()