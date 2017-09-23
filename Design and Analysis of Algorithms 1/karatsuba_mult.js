//Kindergarten addition
/**
 * sum of two huge numbers
 * @param  {[string]} str1
 * @param  {[string]} str2
 * @return {[string]}      [sum]
 */
function sum(str1, str2) {
	var carry = 0;
	var result = "";
	var biggerString, smallerString;
	// console.log("sum of");
	// console.log("str1 = " + str1);
	// console.log("str2 = " + str2);
	if(str1.length >= str2.length) {
		biggerString = str1;
		smallerString = str2;
	} else {
		biggerString = str2;
		smallerString = str1;
	}
	var padding = biggerString.length - smallerString.length;
	for(var i=0;i<padding;i++) {
		smallerString = "0".concat(smallerString);
	}
	for(var i=biggerString.length-1; i>=0;i--) {
		var tmpSum = 0;
		tmpSum = parseInt(biggerString[i]) + carry;
		var tmpIndex = smallerString.length - biggerString.length + i;
		tmpSum += parseInt(smallerString[tmpIndex]);
		result = ("" + tmpSum%10).concat(result);
		carry = parseInt(tmpSum/10);
	}
	if(carry>0) {
		result = ("" + carry).concat(result);
	}
	// console.log("result = " + result);
	return result;
}
/**
 * Kindergarten subtraction
 * @param  {[string]} str1 [bigger number in string format]
 * @param  {[string]} str2 [smaller number in string format]
 * @return {[string]}   [str1-str2]
 */
function mod(str1, str2){
	var result = "";
	var biggerString, smallerString;
	// console.log("mod of");
	// console.log("str1 = " + str1);
	// console.log("str2 = " + str2);
	if(str1.length > str2.length) {
		biggerString = str1;
		smallerString = str2;
	} else if(str1.length < str2.length){
		biggerString = str2;
		smallerString = str1;
	} else {
		//TODO: Write code to handle case when str1.length==str2.length but str1<str2
		for(var i=0;i<str1.length;i++) {
			if(str1[i]>str2[i]) {
				biggerString = str1;
				smallerString = str2;
				break;
			} else if(str1[i]<str2[i]) {
				biggerString = str2;
				smallerString = str1;
				break;
			} else if(i==str1.length-1 && str1[i] == str2[i]){
				return "0";
			}
		}
	}
	// console.log("biggerString = " + biggerString);
	// console.log("smallerString = " + smallerString);
	var padding = biggerString.length - smallerString.length;
	for(var i=0;i<padding;i++) {
		smallerString = "0".concat(smallerString);
	}
	//debugger;
	for(i=biggerString.length-1;i>=0;i--) {
		if(biggerString[i] == smallerString[i]) {
			result = "0".concat(result);
		} else if(biggerString[i] > smallerString[i]) {
			result = ("" + parseInt(parseInt(biggerString[i]) - parseInt(smallerString[i]))).concat(result);
		} else {
			result = ("" + parseInt(10 + parseInt(biggerString[i]) - parseInt(smallerString[i]))).concat(result);
			if(0 < i && i <= biggerString.length-1) {
				var currIndex = i;
				while(biggerString[currIndex] == "0") {
					biggerString = biggerString.substr(0, currIndex) + "9"  + biggerString.substr(currIndex + 1);
					currIndex--;
					if(currIndex == 0) {
						currIndex = 1;
						break;
					}
				}
				if(biggerString[currIndex-1] == 0) return -1;
				biggerString = biggerString.substr(0, currIndex-1) + parseInt(biggerString[currIndex-1]-1)  + biggerString.substr(currIndex);
			} else {
				return -1;
			}
		}

	}
	// console.log("result = " + result);
	return result;
}
/**
 * Karatsuba multiplication
 * @param  {[string]} str1 [description]
 * @param  {[string]} str2 [description]
 * @return {[string]}      [description]
 */
function mult(str1, str2){
	var ans = "", numOfDigits;
	// console.log("mult of");
	// console.log("str1 = " + str1);
	// console.log("str2 = " + str2);
	if(str1.length == 1 || str2.length == 1) {
		ans = "" + (parseInt(str1) * parseInt(str2));
		// console.log("ans = " + ans);
		return ans;
	}
	var base;
	if(str1.length >= str2.length) {
		numOfDigits = parseInt(str1.length/2);
	} else {
		numOfDigits = parseInt(str2.length/2);
	}
	//debugger;
	var str11 = str1.slice(0,str1.length-numOfDigits);
	var str12 = str1.slice(str1.length-numOfDigits);
	var str21 = str2.slice(0,str2.length-numOfDigits);
	var str22 = str2.slice(str2.length-numOfDigits);
	var z2 = mult(str11, str21);
	var z0 = mult(str12, str22);
	var z1 = mod(mult(sum(str11, str12), sum(str21, str22)), sum(z0, z2));
	ans = sum(pad(pad(z2, numOfDigits), numOfDigits), pad(z1, numOfDigits));
	ans = sum(ans, z0);
	// console.log("z2 = " + z2);
	// console.log("z1 = " + z1);
	// console.log("z0 = " + z0);
	// console.log("ans = " + ans);
	return ans;
}
function pad(str, numOfDigits) {
	for(var i=0; i<numOfDigits;i++) {
		str+="0";
	}
	return str;
}
mult("12345", "678");