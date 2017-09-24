n=5000
numbers =range(2,n)
results =[]
while (numbers != []):
    num=numbers[0]
    results.append(num)
    for i in numbers:
        if(i%num == 0):
            numbers.remove(i)
    
print len(results)