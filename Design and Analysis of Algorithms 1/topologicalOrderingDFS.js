function dfs(graph, ver) {
	exp[ver] = true;
	for (var i = 0; i < graph[ver].length; i++) {
		var vertex = graph[ver][i];
		if(!exp[vertex]) {
			dfs(graph, vertex);
		}
	}
	orderedGraph[ver] = currLabel--;
}
var directedGraph = {
	"s": ["v", "w"],
	"v": ["t"],
	"w": ["t"],
	"t": [] 
}
exp = {};
//mark all nodes unexplored
for(var vertex in directedGraph) {
	exp[vertex] = false;
}
var orderedGraph = {};
var currLabel = Object.keys(directedGraph).length;
for(var v in directedGraph) {
	if(!exp[vertex]) {
		dfs(directedGraph, v);
	}
}
console.log(orderedGraph);