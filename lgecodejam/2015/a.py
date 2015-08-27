# LGE CODEJAM 2015 - Problem A

N2 = {"12", "14", "15",
      "21", "23", "24", "25", "26",
      "32", "35", "36",
      "41", "42", "45", "47", "48",
      "51", "52", "53", "54", "56", "57", "58", "59",
      "62", "64", "65", "68", "69",
      "74", "75", "78", "70",
      "84", "85", "86", "87", "89", "80",
      "95", "96", "98", "90",
      "08"}

N3 = {"123", "159", "147",
      "258",
      "321", "357", "369",
      "456",
      "580",
      "654",
      "741", "753", "789",
      "852",
      "963", "951", "987",
      "085"}


def check2(nums):
    if nums[0] == nums[1]:
        return 0
    elif nums[0:2] in N2:
        return 1
    else:
        return 2

def check3(nums):
    if nums[1] == nums[2]:
        return 0
    elif nums in N3:
        return 1
    elif nums[1:] in N2:
        return 2
    else:
        return 3

def main():
    T = int(input())
    for t in range(T):
        N = int(input())
        NS = [input() for n in range(N)]
        ans = None
        ans_point = 1000
        for nums in NS:
            current_point = check2(nums)
            for pos in range(0, len(nums)-2):
                current_point += check3(nums[pos:pos+3])
            if current_point < ans_point:
                ans_point = current_point
                ans = nums
        print(ans)

if __name__ == '__main__':
    main()
