def solution(str1, str2):
    k = ""
    for x in range(len(str1)):
        k += str1[x]+str2[x]
    
    return k