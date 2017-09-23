function dist(p1, p2){
	var x1 = p1.x;
	var x2 = p2.x;
	var y1 = p1.y;
	var y2 = p2.y;
	return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
}
function minDistThree(p1q1, p2q2, p3q3) {
	var p1 = p1q1[0];
	var q1 = p1q1[1];
	var p2 = p2q2[0];
	var q2 = p2q2[1];
	var p3 = p3q3[0];
	var q3 = p3q3[1];
	var dist1 = dist(p1, q1);
	var dist2 = dist(p2, q2);
	var dist3 = dist(p3, q3);
	if(dist1<=dist2 && dist2<=dist3) return p1q1;
	if(dist2<=dist1 && dist1<=dist3) return p2q2;
	return p3q3;
}
function minDist(p1q1, p2q2) {
	var p1 = p1q1[0];
	var q1 = p1q1[1];
	var p2 = p2q2[0];
	var q2 = p2q2[1];
	var dist1 = dist(p1, q1);
	var dist2 = dist(p2, q2);
	if(dist1<=dist2) return p1q1;
	return p2q2;
}
function isEqual(p1, p2) {
	if(p1.x === p2.x && p1.y === p2.y) return true;
	return false;
}
function closestBrute(arr) {
	var min = Infinity;
	var closestPairArr = new Array();
	for(var i=0;i<arr.length;i++) {
		for (var j = 0; j < arr.length;j++) {
			if(dist(arr[i], arr[j]) <= min && !isEqual(arr[i], arr[j])) {
				min = dist(arr[i], arr[j]);
				closestPairArr[0] = arr[i];
				closestPairArr[1] = arr[j];
			}
		};
	}
	return closestPairArr;
}
//merge sort by co-ordinates
function merge(a, b, coord) {
	var c = new Array();
	var k=0;
	while(a.length || b.length) {
		if(b.length == 0) {
			c[k] = a.shift();
		} else if(a.length == 0) {
			c[k] = b.shift();
		} else {
			var first;
			var second;
			if(coord == "x") {
				first = a[0].x;
				second = b[0].x;
			} else{
				first = a[0].y;
				second = b[0].y;
			}
			if(first <= second) {
				c[k] = a.shift();
			} else {
				c[k] = b.shift();
			}
		}
		k++;
	}
	return c;
}
function sort(pArr, coord) {
	if(pArr.length <= 1) {
		return pArr;
	} else {
		return merge(sort(pArr.splice(0,parseInt(pArr.length/2)), coord), sort(pArr, coord), coord);
	}
}
function min(a, b) {
	if(a<=b) return a;
	return b;
}
function closestSplitPair(pxArr, pyArr, tmpMin) {
	var xBar = pxArr[parseInt(pxArr.length/2)].x;
	var syArr = new Array();
	var best = tmpMin;
	var bestPair = new Array();
	for (var i = 0; i < pyArr.length; i++) {
		if(pyArr[i].x >= (xBar - tmpMin) && pyArr[i].x <= (xBar + tmpMin)) {
			syArr.push(pyArr[i]);
		}
	}
	for (var i = 0; i < syArr.length; i++) {
		for (var j = 0; j < min(8, syArr[i].length); j++) {
			var p = syArr[i];
			var q = syArr[i+j];
			var d = dist(p, q);
			if(d<best) {
				bestPair[0] = p;
				bestPair[1] = q;
			}
		};
	};
	return bestPair;
}
function cloneArr(arr) {
	var clone = new Array();
	for (var i = 0; i < arr.length; i++) {
		clone[i] = arr[i];
	};
	return clone;
}
function closestPair(pArr) {
	if(pArr.length <= 3) {
		return closestBrute(pArr);
	}
	var pxArr = sort(cloneArr(pArr), "x");
	var pyArr = sort(cloneArr(pArr), "y");
	var qArr = pxArr.splice(0,parseInt(pArr.length/2));
	var rArr = pxArr.splice(parseInt(pxArr.length/2));
	var p1q1 = closestPair(qArr);
	var p2q2 = closestPair(rArr);
	var pq = minDist(p1q1, p2q2);
	var tmpMin = dist(pq[0], pq[1]);
	var p3q4 = closestSplitPair(pxArr, pyArr, tmpMin);
	return p3q4.length == 2 ? p3q4 : pq;
}
var cp = closestPair([{x: 1, y: 1}, {x: 2, y: 2}, {x: 3, y: 3}, {x: 4, y: 4}, {x: 5, y: 5}, {x: 0, y: 0}]);
console.log("min = " + dist(cp[0], cp[1]));
console.log("Closest pair:");
console.log("(" + cp[0].x + ", " + cp[0].y + ") and (" + cp[1].x + ", " + cp[1].y + ")");