var graph = {
	1: [7],
	2: [5],
	3: [9],
	4: [1],
	5: [8],
	6: [3, 8],
	7: [4,9],
	8: [2],
	9: [6]
};
inDegree={};
for(var ver in graph) {
	inDegree[ver] = 0;
}
for(var ver in graph) {
	for (var i = 0; i < graph[ver].length; i++) {
		inDegree[graph[ver][i]]++;
	};
}
console.log(inDegree);