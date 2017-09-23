//for programming assignment
//parsing the input
console.log("Iterative solution")
var inputArr = [[1, 9], [2, 3], [1, 12], [10, 5], [4, 1], [13, 4], [2, 7], [1, 2], [9, 10], [11, 8], [5, 4], [8, 3], [12, 10], [1, 3], [7, 6], [9, 13], [6, 2], [3, 11]]
var graph={};
var MAX=0;
for (var i = 0; i < inputArr.length; i++) {
	var head = inputArr[i][0].toString();
	var tail = inputArr[i][1].toString();
	if(graph[head] == undefined) {
		graph[head] = [];
	}
	graph[head].push(tail);
}
for(var ver in graph){
	for (var i = 0; i < graph[ver].length; i++) {
		if(MAX<graph[ver][i]) MAX = graph[ver][i];
	};
	if(MAX<ver) MAX = ver;
}
for (var i = 1; i <= MAX; i++) {
	if(graph[i]==undefined) graph[i]=[];
}
console.log(graph);


//computing strong components
//Kosaraju Algo
function DFSLoop(graph) {
	//assume nodes labelled 1 to n
	t=0; //num of nodes processed so far
	for (var i = Object.keys(graph).length; i > 0; i--) {
		if(!exp[i]) {
			s=i;
			console.log("s = " + s);
			DFS(graph, i);
		}
	};
}
function DFS(graph, node) {
	stack = [];
	stack.push(node);
	while(stack.length) {
		console.log("Stack:")
		console.log(stack)
		node = stack[stack.length - 1];
		exp[node] = true;
		leader.push(s);
		console.log("DFS in node : " + node);
		if(graph[node]) {
			end=true;
			for (var  j= graph[node].length-1; j >= 0; j--) {
				var vertex = graph[node][j];
				if(!exp[vertex]) {
					end=false;
					stack.push(vertex);
				}
			}
		}
		if(end) {
			console.log("Incrementing t");
			t++;
			console.log("Assigning t " + t + " to node " + node);
			f[node] = t;
			rem = stack.pop();
			//remove all instances of rem
			tmpArr=[];
			for(var i=0;i<stack.length;i++) {
				if(stack[i] != rem) {
					tmpArr.push(stack[i]);
				}
			}
			stack=tmpArr;
		}
	}
}
// var graph = {
// 	1: [7],
// 	2: [5],
// 	3: [9],
// 	4: [1],
// 	5: [8],
// 	6: [3, 8],
// 	7: [4,9],
// 	8: [2],
// 	9: [6]
// };

s=null; //current source vertex
f={}; //finishing time - magical ordering
leader=[];
exp={};
DFSLoop(graph);
console.log("Finishing times:");
console.log(f);

//2nd pass
//reverse graph with nodes replaced with finishing times
revGraph = {};
max=0;
for(var ver in graph) {
	for (var i = 0; i < graph[ver].length; i++) {
		var vertex = graph[ver][i];
		if(revGraph[f[vertex]] == undefined) revGraph[f[vertex]] = [];
		revGraph[f[vertex]].push(f[ver]);
	}
}
for(var ver in revGraph){
	for (var i = 0; i < revGraph[ver].length; i++) {
		if(max<revGraph[ver][i]) max = revGraph[ver][i];
	};
	if(max<ver) max = ver;
}
for (var i = 1; i <= max; i++) {
	if(revGraph[i]==undefined) revGraph[i]=[];
}
console.log("Reversed graph:")
console.log(revGraph);
t=0; //num of nodes processed so far
s=null; //current source vertex
f={}; //finishing time - magical ordering
leader=[];
exp={};
DFSLoop(revGraph);
console.log("Leaders:");
console.log(leader);
//For programming assignment
sizeArr=[];
sizeObj={};
for(var ind=0; ind<leader.length;ind++) {
	if(!sizeObj[leader[ind]]) {
		sizeObj[leader[ind]] = 0;
	}
	sizeObj[leader[ind]]++;
}
console.log("sizeObj");
console.log(sizeObj);
for(var ind in sizeObj) {
	sizeArr.push(sizeObj[ind]);
}
console.log("sizeArr");
console.log(sizeArr);
console.log("answer ...");
var sortedArr=sizeArr.sort(function(a, b){return b-a});
//sortedArr=sort([6,2,9,1,6,9]);
console.log(sortedArr.toString());
console.log(sortedArr.slice(0, 5).toString());