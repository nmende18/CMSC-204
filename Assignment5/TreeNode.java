/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 5 - MorseCode
 */
public class TreeNode<T> {
	
	T dataNode;
	TreeNode<T> left;
	TreeNode<T> right;	
	
	public TreeNode(T dataNode) {
		left = null;
		right = null;
		this.dataNode = dataNode;
		
	}
	
	public TreeNode(TreeNode<T> node) {
		this.dataNode = node.dataNode;
		
		if(node.left != null) this.left = new TreeNode<>(node.left);
		if(node.right != null) this.right = new TreeNode<>(node.right);
		
	}
	
	public T getData() {
		return dataNode;
	}

}
