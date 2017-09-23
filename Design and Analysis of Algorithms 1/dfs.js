function dfs(graph, ver) {
	exp[ver] = true;
	console.log("Explored " + ver);
	for (var i = 0; i < graph[ver].length; i++) {
		var vertex = graph[ver][i];
		if(!exp[vertex]) {
			console.log("Exploring " + vertex);
			dfs(graph, vertex);
		}
	}
}
var graph = {
	1: [2, 3, 4, 7],
	2: [1, 3, 4],
	3: [1, 2, 4],
	4: [1, 2, 3, 5],
	5: [4, 6, 7, 8],
	6: [5, 7, 8],
	7: [1, 5, 6, 8],
	8: [5, 6, 7]
};
exp = {};
for(var vertex in graph) {
	exp[vertex] = false;
}
dfs(graph, 1);