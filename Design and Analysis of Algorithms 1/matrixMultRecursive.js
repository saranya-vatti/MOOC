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
	printDoubleDimArr(a);
	printDoubleDimArr(b);
	printDoubleDimArr(c);
	printDoubleDimArr(d);
	printDoubleDimArr(e);
	printDoubleDimArr(f);
	printDoubleDimArr(g);
	printDoubleDimArr(h);
	var ae = multBlocks(a, e);
	var bg = multBlocks(b, g);
	var af = multBlocks(a, f);
	var bh = multBlocks(b, h);
	var ce = multBlocks(c, e);
	var dg = multBlocks(d, g);
	var cf = multBlocks(c, f);
	var dh = multBlocks(d, h);
	var aeSUMbg = sumBlocks(ae, bg);
	var afSUMbh = sumBlocks(af, bh);
	var ceSUMdg = sumBlocks(ce, dg);
	var cfSUMdh = sumBlocks(cf, dh);
	var result = new Array();
	for (var i = 0; i < aeSUMbg.length/2; i++) {
		result[i] = aeSUMbg.concat(afSUMbh);
	};
	for (var i = aeSUMbg.length/2 ; i < aeSUMbg.length; i++) {
		result[i] = ceSUMdg.concat(cfSUMdh);
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