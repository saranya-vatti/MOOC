#naive algo
#answer: 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
import time
import sys
print ("Starting to read the file")
start_time = time.time()
G=[]
V=[]
START_NODE="1"
#with open('SCC.txt') as f:
with open('tmp.txt') as f:
    for line in f:
        list1 = line.split()
        vertex1 = list1.pop(0)
        V.append(vertex1)
        for tup1 in list1:
            G.append((vertex1, tup1.split(",")[0], int(tup1.split(",")[1])))


elapsed_time = time.time() - start_time
print ("time to read the file = " + str(elapsed_time))
    
X=[START_NODE]
V.remove(START_NODE)
##print ("G:")
##print (G)

##G=[
##    ("s", "v", 1), #sort in increasing order of weights
##    ("s", "w", 4),
##    ("v", "w", 2),
##    ("v", "t", 6),
##    ("w", "t", 3)
##]
A={}
cumulative_A=0
A[START_NODE] = (0, [])
iteration=1
while len(V):
    min_wt=1000000
    min_node=None
    min_ver=None
    min_path=[]
    ##print ("Iteration : " + str(iteration))
    ##elapsed_time = time.time() - start_time - elapsed_time
    ##print ("time taken = " + str(elapsed_time))
    ##iteration = iteration + 1
    for ver in X:
        for tup in G:
            if tup[0] in X and tup[1] in V:
                init_wt=A[tup[0]][0]
                init_path=list(A[tup[0]][1])
                if init_wt+tup[2]<min_wt:
                    min_wt=init_wt+tup[2]
                    min_node=tup[1]
                    min_ver=tup[0]
                    min_path=init_path
                    min_path.append(min_node)
    A[min_node] = (min_wt, min_path)
    X.append(min_node)
    V.remove(min_node)


elapsed_time = time.time() - start_time - elapsed_time
print ("time taken = " + str(elapsed_time))
print(A["7"])
print(A["37"])
print(A["59"])
print(A["82"])
print(A["99"])
print(A["115"])
print(A["133"])
print(A["165"])
print(A["188"])
print(A["197"])
