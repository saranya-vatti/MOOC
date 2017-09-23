var count = 0;
function sortAndCount(arr) {
	//base case:
	if(arr.length <= 1) {
		return arr;
	} else {
		return mergeAndCountSplitInv(sortAndCount(arr.splice(0,parseInt(arr.length/2))), sortAndCount(arr));
	}
}
/**
 * Takes input two sorted arrays. Iterates over each array and adds them in sorted order to new array c.
 * Also count the number of times we copy 'b's element before 'a'
 * @param  {[type]} a [description]
 * @param  {[type]} b [description]
 * @return {[type]}   [description]
 */
function mergeAndCountSplitInv(a, b) {
	var c = new Array();
	var k=0;
	while(a.length || b.length) {
		if(b.length == 0 || a[0]<b[0]) {
			c[k] = a.shift();
		} else if(a.length == 0) {
			c[k] = b.shift();
		} else if(a[0]>=b[0]) {
			c[k] = b.shift();
			count += a.length;
		}
		k++;
	}
	return c;
}
sortAndCount([1,3,5,2,4,6]);
console.log("Number of iunversions = " + count);