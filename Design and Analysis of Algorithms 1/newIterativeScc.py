#!/usr/bin/python
#nohup python iterativeScc.py > out.txt 2>&1
##str="[["
##with open('tmp.txt') as f:
##    str+=("],[".join(line.strip() for line in f))
##str=str.replace(" ", ",")
##str+="]]"
import time
import sys
#sys.setrecursionlimit(63000)
graph={}
MAX=4
#MAX=875714
print "Starting to read the file"
start_time = time.time()
#with open('SCC.txt') as f:
with open('tmp.txt') as f:
    for line in f:
        head, tail = [int(x) for x in line.split()]
        if not head in graph:
            graph[head]=[]
        graph[head].append(tail)
f.close()
elapsed_time = time.time() - start_time
print "time to read the file = " + str(elapsed_time)
for index1 in range(1, MAX + 1):
    if not index1 in graph:
        graph[index1]=[]
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
    try:
        for index2 in reversed(range(1, s + 1)):
            if not index2 in exp:
                s=index2
                print "s = " + str(s)
                DFS(graph, index2)
    except KeyError, e:
        print 'KeyError in DFSLoop - reason "%s"' % str(e)
    except IndexError, e:
        print 'IndexError in DFSLoop - reason "%s"' % str(e)
    except IOError as (errno, strerror):
        print "I/O error({0}): {1} in DFSLoop".format(errno, strerror)
    except:
         print "Unexpected error in DFSLoop:", sys.exc_info()[0]

def DFS(graph, node):
    global t
    global s
    global f
    global leader
    global exp
    #print "DFS in node : " + str(node)
    stack=[]
    stack.append(node)
    try:
        while(stack):
            node=stack[-1]
            exp[node]=True
            leader[node]=s
            #print "DFS in node : " + str(node)
            end=True
            if node in graph:
                for vertex1 in reversed(graph[node]):
                    if not vertex1 in exp:
                        end=False
                        stack.append(vertex1)
            if end:
                #print "Incrementing t"
                t=t+1
                #print "Assigning t " + str(t) + " to node " + str(node)
                f[node] = t
                rem=stack.pop()
                len1=len(stack)
                stack[:] = [x for x in stack if x != rem]
                len2=len(stack)
                if len1 != len2:
                    print "stack length:" + str(len2)
    except KeyError, e:
        print 'KeyError in DFS - reason "%s"' % str(e)
    except IndexError, e:
        print 'IndexError in DFS - reason "%s"' % str(e)
    except IOError as (errno, strerror):
        print "I/O error({0}): {1} in DFS".format(errno, strerror)
    except:
         print "Unexpected error in DFS:", sys.exc_info()[0]    

print "Starting DFSLoop - Iteration 1"
DFSLoop(graph)
elapsed_time = time.time() - elapsed_time - start_time
print "Finishing time calculated in seconds " + str(elapsed_time)
#print "Finishing time:"
#print f

##2nd pass
##reverse graph with nodes replaced with finishing times
try:
revGraph = {};
for vertex2 in graph:
    for vertex3 in graph[vertex2]:
        if not f[vertex3] in revGraph:
            revGraph[f[vertex3]] = []
        revGraph[f[vertex3]].append(f[vertex2])
for index3 in range(1, MAX + 1):
    if not index3 in revGraph:
        revGraph[index3]=[]
elapsed_time = time.time() - elapsed_time - start_time
print "Reversed Graph created in seconds " + str(elapsed_time)
#print "Reversed graph:"
#print revGraph

t=0
s=None
f={}
leader={}
exp={}
print "Starting DFSLoop - Iteration 2"
DFSLoop(revGraph)
elapsed_time = time.time() - elapsed_time - start_time
print "Leaders calculated in seconds " + str(elapsed_time)
#print "Leaders:"
#print leader
##For programming assignment
sizeArr=[]
sizeObj={}
for index4 in leader:
    if not leader[index4] in sizeObj:
        sizeObj[leader[index4]] = 0
    sizeObj[leader[index4]] = sizeObj[leader[index4]] + 1

#print "sizeObj"
#print sizeObj
for index5 in sizeObj:
    sizeArr.append(sizeObj[index5])
#print "sizeArr"
#print sizeArr

sizeArr.sort(reverse=True)

print "answer ..."
print sizeArr[:5]
