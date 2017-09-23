//Dijkstra's Shortest-Path Algorithm
//MinHeap: parent is less than both the children. root is the min.
//incomplete
var MinHeap = (function() {
	var arr;

	function init() {
		arr=[];
	}

	function insert(node) {
		arr.append(node);
		var index = arr.length -1;
		bubbleUp(arr.length -1);
	}

	function bubbleUp(index) {
		console.log("Before bubbling up:");
		console.log(arr.toString());
		if(index<=0) return;
		if(index%2 == 0) {
			if(arr[index/2] > arr[index]) {
				var tmp = arr[index];
				arr[index] = arr[index/2];
				arr[index/2] = tmp;
				console.log("After bubbling up:");
				console.log(arr.toString());
				bubbleUp(index/2);
			}
		} else {
			if(arr[parseInt(index/2)] > arr[index]) {
				var tmp = arr[index];
				arr[index] = arr[parseInt(index/2)];
				arr[parseInt(index/2)] = tmp;
				console.log("After bubbling up:");
				console.log(arr.toString());
				bubbleUp(parseInt(index/2));
			}
		}
	}

	function extractMin() {
		var min = arr[0];
		arr[0] = arr[arr.length-1];
		bubbleDown(0);
		return min;
	}

	function bubbleDown(index) {
		console.log("Before bubbling down:");
		console.log(arr.toString());
		if(arr[index*2] == undefined) return;
		if(arr[index] > arr[index*2]) {
			tmp = arr[index*2];
			arr[index*2] = arr[index];
			arr[index] = tmp;
			console.log("After bubbling down:");
			console.log(arr.toString());
			bubbleDown(index*2);
		}
		if(arr[index*2] + 1 == undefined) return;
		if(arr[index] > arr[index*2 + 1]) {
			tmp = arr[index*2 + 1];
			arr[index*2 + 1] = arr[index];
			arr[index] = tmp;
			console.log("After bubbling down:");
			console.log(arr.toString());
			bubbleDown(index*2 + 1);
		}
	}

	init();

	var me = {
		extractMin: extractMin,
		insert: insert
	}
})