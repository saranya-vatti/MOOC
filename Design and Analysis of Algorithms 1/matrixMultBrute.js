function mult(arr1, arr2) {
	var c = new Array();
	for (var i = 0; i < arr1.length; i++) {
		c[i] = new Array();
		for (var j = 0; j < arr1.length; j++) {
			c[i][j] = 0;
			for (var k = 0; k < arr1.length; k++) {
				c[i][j] += arr1[j][k]*arr2[k][j];
			}
		}
	}
}