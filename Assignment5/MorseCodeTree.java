import java.util.ArrayList;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 5 - MorseCode
 */
public class MorseCodeTree<T> implements LinkedConverterTreeInterface<String> {
	
	 TreeNode<String> root;
	
	public MorseCodeTree() {
		root = new TreeNode<>("");
		buildTree();
	}

	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		this.root = newNode;
	}

	@Override
	public void insert(String code, String result) {
		addNode(root, code, result);
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if(code.length() ==  1) {
			if(code.equals(".")) {
				root.left = new TreeNode<>(letter);
			}
			else if(code.equals("-")) {
				root.right = new TreeNode<>(letter);
			}
			return;
		}
		if(code.length() > 1) {
			if(code.charAt(0) == '.') {
				if(root.left == null) root.left = new TreeNode<>("");
				addNode(root.left, code.substring(1), letter);
			}
			else if(code.charAt(0) == '-'){
				if(root.right == null) root.right = new TreeNode<>("");
				addNode(root.right, code.substring(1), letter);
			}	
		}
	}
	
	@Override
	public String fetch(String code) {
		String s = fetchNode(root, code);
		return s;
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if(root == null) {
			throw new IllegalArgumentException();
		}
		
		if(code.isEmpty()) {
			if(root.getData() == null) return "";
			else return root.getData();
		}
		switch(code.charAt(0)) {
		case '.':
			return fetchNode(root.left, code.substring(1));
		case '-':
			return fetchNode(root.right, code.substring(1));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
		root = new TreeNode<>("");
		
		//level 1
		insert(".", "e");
		insert("-", "t");
		
		//level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		//level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		//level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
		
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
		return list;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root == null) return;
		LNRoutputTraversal(root.left, list);
		if(root.getData() != null) {
			list.add(root.getData());
			}
		LNRoutputTraversal(root.right, list);
	}

}
