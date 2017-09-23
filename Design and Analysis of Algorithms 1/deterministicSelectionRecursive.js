//deterministic selection - without random selection of pivot
//always takes max O(n) time
//worse constants, not in place - not good practically
function swap(arr, i, j) {
	var tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}
function dSelect(arr, low, high, i) {
	if(high==low) return arr[low];
	if(low < high) {
		console.log();
		var cArr = new Array();
		for(j=low;j<=high;j+=5) {
			//debugger;
			if(j+4 <= high) {
				sort(arr, j, j+4);//both inclusive
				console.log("Pushing :: " + arr[j+2]);
				cArr.push(arr[j+2]);
			} else {
				sort(arr, j, high);
				console.log("Pushing :: " + arr[j + parseInt((high-j + 1)/2)]);
				cArr.push(arr[j + parseInt((high-j+1)/2)]);
			}
		}
		console.log("cArr::");
		printArr(cArr, 0, cArr.length - 1);
		var pivot = dSelect(cArr, 0, cArr.length - 1, parseInt(cArr.length - 1 / 2));
		var j = partition(arr, low, high, pivot);
		console.log("j = " + j);
		if(j==i) {
			return arr[j];
		} else if(j>i) {
			return dSelect(arr, low, j, i);
		} else {
			return dSelect(arr, j+1, high, i);
		}
	}
}
function partition(arr, low, high, pivot) {
	if(low >= high) return low;
	var i=low+1, j=low+1;
	var p = pivot;
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
function printArr(arr, low, high) {
	var str = "";
	for (var i = low; i <= high; i++) {
		str += arr[i] + ", ";
	}
	console.log(str);
}
function sort(arr, low, high) {
	console.log("Before sorting::");
	printArr(arr, low, high);
	for (var i = low; i <= high; i++) {
		for (var j = low; j <= high; j++) {
			if(arr[i]<arr[j]) swap(arr, i, j);
		}
	}
	console.log("After sorting::");
	printArr(arr, low, high);
}
var arr = [3, 9, 8, 4, 6, 10, 2, 5, 7, 1, 12, 11];
console.log(dSelect(arr, 0, arr.length - 1, 3)); //find 3rd least element