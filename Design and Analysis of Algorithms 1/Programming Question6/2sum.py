#naive algo
#better than naive-
#sort A in nlogn
#for each x in A
#   look for t-x via binary search
import time
import sys
import itertools
print ("Starting to read the file")
start_time = time.time()
A=[]
#with open('algo1-programming_prob-2sum.txt') as f:
with open('tmp.txt') as f:
    for line in f:
        if int(line) not in A:
            A.append(int(line))

time1 = time.time() - start_time
print ("time to read the file and insert into array = " + str(time1))
A.sort()
time2 = time.time() - time1 - start_time
print ("time to sort the array = " + str(time2))

def isPresent(elem, A, start, stop):
    #binary search
    if start > stop:
        return False
    if start == stop and A[start] != elem:
        return False
    if stop - start == 1:
        if A[start] == elem:
            return True
        elif A[stop] == elem:
            return True
        return False
    mid = int((start+stop)/2)
    if elem < A[mid]:
        return isPresent(elem, A, start, mid)
    elif elem > A[mid]:
        return isPresent(elem, A, mid, stop)
    else:
        return True
count=0
for t in range(-10000,10001):
    for x in A:
        if t-x != x and isPresent(t-x, A, 0, len(A) - 1):
            #print( str(x) + " + " + str(t-x) + " = " + str(t))
            count = count + 1
            break;

print("Number of elements where x+y=t are:")
print (count)
time3 = time.time() - time2 - start_time
print ("time to count the t = " + str(time3))

            
