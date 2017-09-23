//assume n even
function multBlocks(x,y) {
	if(x.length != y.length || x.length < 1) return;
	if(x.length == 1) {
		return [[x[0][0]*y[0][0]]];
	}
	var a = new Array();
	var b = new Array();
	var c = new Array();
	var d = new Array();
	var e = new Array();
	var f = new Array();
	var g = new Array();
	var h = new Array();
	for (var i = 0; i < x.length/2; i++) {
		a.push(x[i].splice(0, x[i].length/2));
		b.push(x[i]);
	};
	for (var i = x.length/2 ; i < x.length; i++) {
		c.push(x[i].splice(0, x[i].length/2));
		d.push(x[i]);
	};
	for (var i = 0; i < y.length/2; i++) {
		e.push(y[i].splice(0, y[i].length/2));
		f.push(y[i]);
	};
	for (var i = y.length/2; i < y.length; i++) {
		g.push(y[i].splice(0, y[i].length/2));
		h.push(y[i]);
	};
	var p1 = multBlocks(a, diffBlocks(f, h));
	var p2 = multBlocks(sumBlocks(a, b), h);
	var p3 = multBlocks(sumBlocks(c, d), e);
	var p4 = multBlocks(d, diffBlocks(g, e));
	var p5 = multBlocks(sumBlocks(a, d), sumBlocks(e, h));
	var p6 = multBlocks(diffBlocks(b, d), sumBlocks(g, h));
	var p7 = multBlocks(diffBlocks(a, c), sumBlocks(e, f));
	var topLeft = sumBlocks(p5, p4);
	topLeft = diffBlocks(topLeft, p2);
	topLeft = sumBlocks(topLeft, p6);
	var topRight = sumBlocks(p1, p2);
	var bottomLeft = sumBlocks(p3, p4);
	var bottomRight = sumBlocks(p1, p5);
	bottomRight = diffBlocks(bottomRight, p3);
	bottomRight = sumBlocks(bottomRight, p7);
	var halfLen = topLeft.length/2;
	var len = topLeft.length;
	var result = new Array();
	for (var i = 0; i < halfLen; i++) {
		result[i] = topLeft.concat(topRight);
	};
	for (var i = halfLen ; i < len; i++) {
		result[i] = bottomLeft.concat(bottomRight);
	};
	return result;
}
function sumBlocks(x, y){
	var c = new Array();
	for (var i = 0; i < x.length; i++) {
		c[i] = new Array();
		for (var j = 0; j < y.length; j++) {
			c[i][j] = x[i][j] + y[i][j];
		};
	};
	return c;
}
function diffBlocks(x, y) {
	var c = new Array();
	for (var i = 0; i < x.length; i++) {
		c[i] = new Array();
		for (var j = 0; j < y.length; j++) {
			c[i][j] = x[i][j] - y[i][j];
		};
	};
	return c;
}
function printDoubleDimArr(x) {
	for (var i = 0; i < x.length; i++) {
		var str = "";
		for (var j = 0; j < x[i].length; j++) {
			str += x[i][j] + "  ";
		};
		console.log(str);
	};
}
printDoubleDimArr(multBlocks([[1, 2], [3, 4]], [[5, 6], [7, 8]]));