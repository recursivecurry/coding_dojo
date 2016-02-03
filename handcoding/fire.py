import itertools as it


def fire(manager_list, salary_list, productivity_list):
    acc_list = [val[0] - val[1]
                for val in it.chain([(0,0)], zip(productivity_list, salary_list))][-1::-1]
    for i, e in it.takewhile(lambda v: v[0] != 0, zip(range(len(acc_list)-1, -1, -1), acc_list)):
        acc_list[-1 - manager_list[i]] += e if 0 < e else 0
    return acc_list[-1]


if __name__ == "__main__":
    manager_list = [int(val) for val in it.chain([0], input().split())]
    productivity_list = [int(val) for val in input().split()]
    salary_list = [int(val) for val in input().split()]
    print(fire(manager_list, salary_list, productivity_list))
    #print(fire([0,0,0,0,1,1,2,2], [1,3,2,2,3,3,0], [2,2,1,1,4,1,4]))
