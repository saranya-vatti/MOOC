#!/usr/bin/python
##str="[["
##with open('tmp.txt') as f:
##    str+=("],[".join(line.strip() for line in f))
##str=str.replace(" ", ",")
##str+="]]"
import time
import sys
#sys.setrecursionlimit(63000)
graph={}
MAX=0
print "Starting to read the file"
start_time = time.time()
with open('SCC.txt') as f:
#with open('tmp.txt') as f:
    for line in f:
        head, tail = [int(x) for x in line.split()]
        if not head in graph:
            graph[head]=[]
        graph[head].append(tail);
f.close()
elapsed_time = time.time() - start_time
print "time to read the file = " + str(elapsed_time)
for ver in graph:
    for vertex in graph[ver]:
        if(MAX<vertex):
            MAX=vertex
    if(MAX<ver):
        MAX=ver
for i in range(1, MAX + 1):
    if not i in graph:
        graph[i]=[]
elapsed_time = time.time() - start_time - elapsed_time
print "Graph created in seconds " + str(elapsed_time)
###print graph
##computing strong components
##Kosaraju Algo

t=0
s=None
f={}
leader={}
exp={}

def DFSLoop(graph):
    global s
    global exp
    global t
    t=0
    s=MAX
    ##assume nodes labelled 1 to n
    for i in range(1, s + 1):
        ind=s - i
        if not ind in exp:
            s=ind
            print "s = " + str(s)
            DFS(graph, ind)

def DFS(graph, node):
    print "DFS start"
    global t
    global s
    global f
    global leader
    global exp
    ###print "DFS in node : " + str(node)
    stack=[]
    stack.append(node)
    while(stack):
        node=stack[-1]
        exp[node]=True
        leader[node]=s
        #print "DFS in node : " + str(node)
        end=True
        if node in graph:
            for vertex in reversed(graph[node]):
                if not vertex in exp:
                    end=False
                    stack.append(vertex)
        if end:
            #print "Incrementing t"
            t=t+1
            #print "Assigning t " + str(t) + " to node " + str(node)
            f[node] = t
            rem=stack.pop()
            stack[:] = [x for x in stack if x != rem]
            print "stack length:" + str(len(stack))
    print "DFS ended"

print "Starting DFSLoop"
DFSLoop(graph)
elapsed_time = time.time() - elapsed_time - start_time
print "Finishing time calculated in seconds " + str(elapsed_time)
print "Finishing time:"
print f

##2nd pass
##reverse graph with nodes replaced with finishing times
revGraph = {};
for ver in graph:
    for vertex in graph[ver]:
        if not f[vertex] in revGraph:
            revGraph[f[vertex]] = []
        revGraph[f[vertex]].append(f[ver])
for i in range(1, MAX + 1):
    if not i in revGraph:
        revGraph[i]=[]
elapsed_time = time.time() - elapsed_time - start_time
print "Reveresed Graph created in seconds " + str(elapsed_time)
#print "Reversed graph:"
#print revGraph

t=0
s=None
f={}
leader={}
exp={}
DFSLoop(revGraph)
elapsed_time = time.time() - elapsed_time - start_time
print "Leaders calculated in seconds " + str(elapsed_time)
#print "Leaders:"
#print leader
##For programming assignment
sizeArr=[]
sizeObj={}
for  ind in leader:
    if not leader[ind] in sizeObj:
        sizeObj[leader[ind]] = 0
    sizeObj[leader[ind]] = sizeObj[leader[ind]] + 1

#print "sizeObj"
#print sizeObj
for ind in sizeObj:
    sizeArr.append(sizeObj[ind])
#print "sizeArr"
#print sizeArr

sizeArr.sort(reverse=True)

print "answer ..."
print sizeArr[:5]
