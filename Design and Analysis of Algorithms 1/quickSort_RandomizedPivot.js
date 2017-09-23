function quickSort(arr, low, high) {
	if(low < high) {
		console.log("Before partition::");
		printArr(arr);
		var p = partition(arr, low, high);
		quickSort(arr, low, p);
		quickSort(arr, p+1, high);
	}
}
function partition(arr, low, high) {
	debugger;
	if(low >= high) return low;
	var i=low+1, j=low+1;
	var pIndex = Math.floor(Math.random() * (high-low)) + low;
	var p = arr[pIndex];
	console.log("low = " + low);
	console.log("high = " + high);
	console.log("Pivot = " + p);
	while(i<high && j<high) {
		if(arr[j] < p) {
			var tmp = arr[j];
			arr[j] = arr[i];
			arr[i] = tmp;
			i++;
			console.log("After swap::");
			printArr(arr);
		}
		j++;
	}
	var tmp = arr[pIndex];
	arr[pIndex] = arr[i-1];
	arr[i-1] = tmp;
	console.log("After partition::");
	printArr(arr);
	return i-1;
}
function printArr(arr) {
	var str = "";
	for (var i = 0; i < arr.length; i++) {
		str += arr[i] + ", ";
	}
	console.log(str);
}
var arr = [3, 8, 2, 5, 1, 4, 7, 6];
quickSort(arr, 0, arr.length);