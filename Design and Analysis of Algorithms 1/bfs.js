function bfs(graph, s) {
	qArr = [];
	exp = {};
	for(var vertex in graph) {
		exp[vertex] = false;
	}
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
	for(var vertex in exp) {
		if(!exp[vertex]) console.log("unexplored: " + vertex);
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
bfs(graph, 1);