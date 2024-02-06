def solution(number, n, m):
    return int(not (number % n) and (number % m) == 0)