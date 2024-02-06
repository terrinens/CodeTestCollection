str = input()
case = str
str = ""
for s in case:
    if s.islower():
        str += s.upper()
    else:
        str += s.lower()

print(str)