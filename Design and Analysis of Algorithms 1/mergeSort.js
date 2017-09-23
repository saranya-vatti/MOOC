function merge(a, b) {
	var c = new Array();
	var k=0;
	while(a.length || b.length) {
		if(b.length == 0 || a[0]<=b[0]) {
			c[k] = a.shift();
		} else {
			c[k] = b.shift();
		}
		k++;
	}
	return c;
}
function sort(arr) {
	//base case:
	if(arr.length <= 1) {
		return arr;
	} else {
		return merge(sort(arr.splice(0,parseInt(arr.length/2))), sort(arr));
	}

}
console.log("answer ...");
console.log(sort([6,4,9,2,8,1,10]));