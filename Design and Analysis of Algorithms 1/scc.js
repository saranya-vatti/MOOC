//for programming assignment
//parsing the input
console.log("Recursive solution");
var inputArr = [[1, 2], [2, 3], [2, 4], [2, 5], [3, 6], [4, 5], [4, 7], [5, 2], [5, 6], [5, 7], [6, 3], [6, 8], [7, 8], [7, 10], [8, 7], [9, 7], [10, 9], [10, 11], [11, 12], [12, 10]]
var graph={};
var max=0;
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
		if(max<graph[ver][i]) max = graph[ver][i];
	};
	if(max<ver) max = ver;
}
for (var i = 1; i <= max; i++) {
	if(graph[i]==undefined) graph[i]=[];
}
console.log(graph);


//computing strong components
//Kosaraju Algo
function DFSLoop(graph) {
	//assume nodes labelled 1 to n
	for (var i = Object.keys(graph).length; i > 0; i--) {
		if(!exp[i]) {
			s=i;
			console.log("s = " + s);
			DFS(graph, i);
		}
	};
}
function DFS(graph, node) {
	exp[node] = true;
	leader[node] = s;
	sizeObj[s] = sizeObj[s] == undefined ? 1 : sizeObj[s]+1;
	console.log("DFS in node : " + node);
	if(graph[node]) {
		for (var  j= 0; j < graph[node].length; j++) {
			var vertex = graph[node][j];
			if(!exp[vertex]) {
				DFS(graph, vertex);
			}
		};
	}
	console.log("Incrementing t");
	t++;
	console.log("Assigning t " + t + " to node " + node);
	f[node] = t;
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
t=0; //num of nodes processed so far
s=null; //current source vertex
f={}; //finishing time - magical ordering
leader={};
exp={};
sizeObj={};
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
leader={};
exp={};
sizeObj={};
DFSLoop(revGraph);
console.log("Leaders:");
console.log(leader);
//For programming assignment
sizeArr=[];
for(var ver in sizeObj) {
	sizeArr.push(sizeObj[ver]);
}
console.log("sizeArr:");
console.log(sizeArr);


function merge(a, b) {
	var c = new Array();
	var k=0;
	while(a.length || b.length) {
		if(b.length == 0 || a[0]<=b[0]) {
			c[k] = a.shift();
		} else {
			c[k] = b.shift();
		}
		k++;
	}
	return c;
}
function sort(arr) {
	//base case:
	if(arr.length <= 1) {
		return arr;
	} else {
		return merge(sort(arr.splice(0,parseInt(arr.length/2))), sort(arr));
	}

}
console.log("answer ...");
var sortedArr=sort(sizeArr);
//sortedArr=sort([6,2,9,1,6,9]);
console.log(sortedArr.toString());
console.log(sortedArr.slice(Math.max(sortedArr.length - 5, 0)).toString());