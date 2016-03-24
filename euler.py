import sys
import math

def find_p(l):
    number_of_p = 0
    for p in range(1,l+1):
        if (is_prime(p)):
            for n in range(1, l+1):
                sum = math.pow(n,2) * (n + p)
                if (check_cube(sum, n+p)):
                    number_of_p = number_of_p + 1
    print(number_of_p)

def check_cube(sum, n_plus_p):
    for i in range(1, n_plus_p):
        if math.pow(i,3) == sum:
            return True
    return False


def is_prime(p):
    for i in range(2,int(round(math.sqrt(p)))+1):
        if p % i == 0:
            return False
    return True


if __name__ == "__main__":
    elems = []
    for num in sys.stdin:
        elems.append(int(num))
    for i in range(1,len(elems)):
        find_p(elems[i])