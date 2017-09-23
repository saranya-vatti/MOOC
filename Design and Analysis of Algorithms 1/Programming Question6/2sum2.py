#427
#naive algo
#better than naive-
#sort A in nlogn
#for each x in A
#   look for t-x via binary search
import time
import sys
import itertools
import collections
f1 = open('output.txt', 'w')
print("Starting to read the file")
f1.write("Starting to read the file\n")
start_time = time.time()
A={}
with open('algo1-programming_prob-2sum.txt') as f:
#with open('tmp.txt') as f:
    for line in f:
        A[int(line)] = 1
f.close()
print("Length of array : " + str(len(A)))
f1.write("Length of array : " + str(len(A)) + "\n")
time1 = time.time() - start_time
print("time to read the file and insert into array = " + str(time1))
f1.write("time to read the file and insert into array = " + str(time1) + "\n")
time0 = time.time() - time1 - start_time
print("time to take the unique keys from A = " + str(time0))
f1.write("time to take the unique keys from A = " + str(time0) + "\n")

A = collections.OrderedDict(sorted(A.items()))
time2 = time.time() - time1 - start_time
print("time to sort the array = " + str(time2))
f1.write("time to sort the array = " + str(time2) + "\n")
print("Length of array : " + str(len(A)))
f1.write("Length of array : " + str(len(A)) + "\n")

count=0
for t in range(-10000,10001):
    for x in A:
        if t-x >= 0 and t-x != x and t-x in A:
            #print( str(x) + " + " + str(t-x) + " = " + str(t))
            count = count + 1
            #print "count = " + str(count) + " and time = " + str( time.time())
            break
print("Number of elements where x+y=t are: " + str(count))
f1.write("Number of elements where x+y=t are: " + str(count) + "\n")
time3 = time.time() - time2 - start_time
print("time to count the t = " + str(time3))
f1.write("time to count the t = " + str(time3) + "\n")
f1.close()
