//for programming assignment
//parsing the input
console.log("Iterative solution")
var inputArr = [[1, 2],  [1, 3],  [2, 1],  [2, 4],  [3, 2],  [4, 1]]
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
//Graph needs to have one entry for every node. Even the ones with no outgoing edges.
for(var ver in graph){
	for (var i = 0; i < graph[ver].length; i++) {
		if(MAX<parseInt(graph[ver][i])) MAX = graph[ver][i];
	};
	if(MAX<parseInt(ver)) MAX = parseInt(ver);
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
	s=MAX;
	//Iterate over all the nodes from high to low
	for (var i = s; i > 0; i--) {
		if(!exp[i]) {
			s=i;
			console.log("s = " + s);
			DFS(graph, i);
		}
	}
}
function DFS(graph, node) {
	stack = [];
	stack.push(node);
	while(stack.length) {
		console.log("Stack:")
		console.log(stack)
		//Take out the last node on the stack
		node = stack[stack.length - 1];
		//Mark it as explored
		exp[node] = true;
		//Save the leader of current node to be the node where the DFS started
		leader[node] = s;
		console.log("DFS in node : " + node);
		if(graph[node]) {
			//Have a flag to mark when the vertex and the vertices its connected to [lets say children] have been explored
			end=true;
			//Iterate over all the node's children
			for (var  j= graph[node].length-1; j >= 0; j--) {
				var vertex = graph[node][j];
				if(!exp[vertex]) {
					end=false;
					//Push the children onto the stack
					stack.push(vertex);
				}
			}
		}
		//If end is true, it means that the node has no unexplored children
		if(end) {
			console.log("Incrementing t");
			t++;
			console.log("Assigning t " + t + " to node " + node);
			f[node] = t;
			rem = stack.pop();
			//remove all instances of rem - test case [[1, 2], [2, 3], [2, 4], [2, 5], [3, 6], [4, 5], [4, 7], [5, 2], [5, 6], [5, 7], [6, 3], [6, 8], [7, 8], [7, 10], [8, 7], [9, 7], [10, 9], [10, 11], [11, 12], [12, 10]]
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

f={}; //finishing time - magical ordering
leader={};
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
for (var i = 1; i <= MAX; i++) {
	if(revGraph[i]==undefined) revGraph[i]=[];
}
console.log("Reversed graph:")
console.log(revGraph);
t=0; //num of nodes processed so far
f={}; //finishing time - magical ordering
leader={};
exp={};
DFSLoop(revGraph);
console.log("Leaders:");
console.log(leader);
//For programming assignment
sizeArr=[];
sizeObj={};
for(var ind in leader) {
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
console.log(sortedArr.slice(Math.max(sortedArr.length - 5, 0)).toString());