/**
 * Count number of elements in an array where greater element is before a smaller element.
 * That is, number of i and j where arr[i]>=arr[j] for i<j
 * @param  {[array of integers]} arr [input array]
 * @return {[integer]}     [inversion count]
 */
function inversionCount(arr) {
	var inv=0;
	for(var i=0;i<arr.length-1;i++) {
		for(var j=i+1;j<arr.length;j++){
			if(arr[i]>arr[j]){
				inv++;
			}
		}
	}
	console.log("Number of inversion = " + inv);
}