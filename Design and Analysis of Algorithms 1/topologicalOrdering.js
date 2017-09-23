//bfs
function main(g, s) {
	if(orderedGraph[s] == -1) orderedGraph[s] = index;
	if(index==Object.keys(g).length) return;
	for (var i = 0; i < g[s].length; i++) {
		if(orderedGraph[g[s][i]] == -1) {
			orderedGraph[g[s][i]] = ++index;
			console.log("order of " + g[s][i] + " is " + orderedGraph[g[s][i]]);
			console.log("index incremented");
		}
	}
}
var orderedGraph = [];
var directedGraph = {
	"s": ["v", "w"],
	"v": ["t"],
	"w": ["t"],
	"t": [] 
}
for(var vertex in directedGraph) {
	orderedGraph[vertex] = -1;
}
printAdjacencyList(directedGraph);
var index = 0;
for(var vertex in directedGraph) {
	main(directedGraph, vertex);
	printOrderedGraph(directedGraph, orderedGraph);
}
function printAdjacencyList(list) {
	console.log("Printing...");
	for (var vertex in list) {
		console.log(vertex + " :: " + list[vertex].toString());
	}
}
function printOrderedGraph(graph, orderedGraph) {
	for(var vertex in graph) {
		console.log(vertex + ":" + orderedGraph[vertex]);
	}
}