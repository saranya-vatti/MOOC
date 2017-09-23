//randomized selection - using quicksort like algo
function swap(arr, i, j) {
	var tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}
function selection(arr, low, high, i) {
	if(high==low) return arr[low];
	if(low < high) {
		var j = partition(arr, low, high);
		console.log("j = " + j);
		if(j==i-1) {
			return arr[j];
		} else if(j>i-1) {
			return selection(arr, low, j, i);
		} else {
			return selection(arr, j+1, high, i);
		}
	}
}
function partition(arr, low, high) {
	if(low >= high) return low;
	var i=low+1, j=low+1;
	var p = chooseRandomPivot(arr);
	console.log("p = " + p);
	while(i<high && j<high) {
		if(arr[j] < p) {
			swap(arr, i, j);
			i++;
		}
		j++;
	}
	swap(arr, i-1, low);
	return i-1;
}
function printArr(arr) {
	var str = "";
	for (var i = 0; i < arr.length; i++) {
		str += arr[i] + ", ";
	}
	//console.log(str);
}
function chooseRandomPivot(arr) {
	for(i=0;i<arr.length/5;i++) {
		sort(arr, i*5, i*5+4);
	}
	if(arr.length%5!=0) {
		sort(arr, i*5, arr.length-1); //high and low inclusive
	}
}