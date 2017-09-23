//Binary search tree
//O(log n) for all array actions - search, select, min, max, insert, delete
var Node = (function(num) {
	var leftChild;
	var rightChild;
	var keyValue = num;

	function left() {
		return leftChild;
	}

	function right() {
		return rightChild;
	}

	function set(newVal) {
		keyValue = newVal;
	}

	function key() {
		return keyValue;
	}

	return me = {
		left: left,
		right: right,
		set: set,
		key: key
	};
})(num);
var BST = (function () {

	var arr;
	var root;

	function init() {

	}

	function search(keyVal, currNode) {
		if (currNode.key() == keyVal) {
			return currNode;
		} else if (currNode.key() < keyVal) {
			if(currNode.left() == undefined) return -1;
			else return search(keyVal, currNode.left());
		} else {
			if(currNode.right() == undefined) return -1;
			else return search(keyVal, currNode.right());
		}
	}

	function min(startNode) {
		var currNode = startNode;
		while (currNode.left() != undefined) {
			currNode = currNode.left();
		}
		return currNode;
	}

	function max(startNode) {
		var currNode = startNode;
		while (currNode.right() != undefined) {
			currNode = currNode.right();
		}
		return currNode;
	}

	init();

	var me = {
	}
});
