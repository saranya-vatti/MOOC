function bfs(graph, s) {
	qArr = [];
	qArr.push(s);
	dist[s] = 0;
	while(qArr.length != 0) {
		v = qArr.shift();
		for (var i = 0; i < graph[v].length; i++) {
			w = graph[v][i];
			if(!exp[w]) {
				exp[w] = true;
				qArr.push(w);
			}
		}
	}
}
exp = {};
function computeComponents(graph) {
	for(var vertex in graph) {
		exp[vertex] = false;
	}
	for(var vertex in graph) {
		if(!exp[vertex]) {
			//The number of times this prints is the number of pieces in the graph
			console.log("Checking " + vertex + " and its components.");
			bfs(graph, vertex);
		}
	};
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
computeComponents(graph);