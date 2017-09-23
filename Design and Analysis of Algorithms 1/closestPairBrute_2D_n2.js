var min = Infinity;
var closestPair = new Array();
/**
 * [closest description]
 * @param  {array of objects} arr [description]
 * @return {[type]}     [description]
 */
function closest(arr) {
	for(var i=0;i<arr.length;i++) {
		for (var j = 0; j < arr.length;j++) {
			if(dist(arr[i], arr[j]) <= min && !isEqual(arr[i], arr[j])) {
				min = dist(arr[i], arr[j]);
				closestPair[0] = arr[i];
				closestPair[1] = arr[j];
			}
		};
	}
}
function dist(p1, p2){
	var x1 = p1.x;
	var x2 = p2.x;
	var y1 = p1.y;
	var y2 = p2.y;
	return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
}
function isEqual(p1, p2) {
	if(p1.x === p2.x && p1.y === p2.y) return true;
	return false;
}
closest([{x: 0, y: 0}, {x: 1, y: 1}, {x: 2, y: 2}]);
console.log("min = " + min);
console.log("Closest pair:");
console.log("(" + closestPair[0].x + ", " + closestPair[0].y + ") and (" + closestPair[1].x + ", " + closestPair[1].y + ")");