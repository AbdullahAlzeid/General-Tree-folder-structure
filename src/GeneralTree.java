import java.io.File;
import java.util.*;

public class GeneralTree<T extends Comparable<? super T>> {
	private int MAXIMUM = 10;
	protected GeneralTreeNode<T> root = null ;

	public GeneralTree() {

	}

	public GeneralTree(GeneralTreeNode<T> rootNode) {
		this.root = rootNode;
	}


	public void printTree() {
		LinkedList<GeneralTreeNode<T>> linkedList = new LinkedList<>();
		linkedList.add(root);
		Stack<String> stack = new Stack<>();
		while (linkedList.size() > 0) {
			for (int i = 0; i < linkedList.size(); i++) {
				GeneralTreeNode<T> currentNode = linkedList.poll();
				if (currentNode != null) {
					stack.push(currentNode.printNode());
				}
			}
		}
		System.out.println(String.join("", stack));
	}


	public void postorder (){
		ArrayList<T> postorderlist = new ArrayList<>();
		postorder(root,postorderlist);

		System.out.println(postorderlist);
	}

	private void  postorder (GeneralTreeNode<T> node,ArrayList <T> list){

		if(node == null){
			return ;
		}

		if(node.children != null) {

			for (GeneralTreeNode<T> tmp : node.getChildren()) {
				postorder(tmp, list);
			}

		}

		list.add(node.el);

	}


	public void preorder (){
		ArrayList<T> preorderlist = new ArrayList<>();
		preorder(root,preorderlist);

		System.out.println(preorderlist);
	}

	private void  preorder (GeneralTreeNode<T> node,ArrayList <T> list){

		if(node == null){
			return ;
		}

		list.add(node.el);

		if(node.children != null) {

			for (GeneralTreeNode<T> tmp : node.getChildren()) {
				preorder(tmp, list);
			}

		}



	}


	private void BFS (GeneralTreeNode<T> root){
		GeneralTreeNode<T> node = root;
		Queue<GeneralTreeNode<T>> queue = new Queue<>();
		if(node!=null){
			queue.enqueue(node);
			while (!queue.isEmpty()){
				node = queue.dequeue();
				visit(node);
				if(node.children != null) {
					for (int i = 0; i < node.children.size(); i++) {
						queue.enqueue(node.children.get(i));
					}
				}

			}
		}

	}

	public void BFS (){
		Queue<GeneralTreeNode<T>> queue = new Queue<>();
		BFS(root);
	}

	private void visit(GeneralTreeNode<T> p) {
		System.out.println(p.el + " ");
	}



	public boolean isEmpty() {
		return root == null;
	}


	public void insert (GeneralTreeNode<T> parentNode, GeneralTreeNode<T> newNode){
		if(root.compareTo(parentNode) == 0){
			root.children.add(newNode);
		}
		else if(searchCheck(parentNode)){

				insert(root,parentNode,newNode);

		}

		else {
			System.out.println("parent node does not exist");
		}

	}


	private void insert(GeneralTreeNode<T> root, GeneralTreeNode<T> parentNode,GeneralTreeNode<T> newNode) {
		if (root.children == null){
			return;
		}


			for (int i = 0; i < root.children.size(); i++) {
				if(root.children.get(i).compareTo(parentNode) == 0){
					root.children.get(i).addChild(newNode);
					System.out.println("Node have been added");
					return;
				}

				else {
					insert(root.children.get(i),parentNode,newNode);
				}

			}





	}

	public boolean searchCheck (GeneralTreeNode<T> Target){
		if(root.compareTo(Target) == 0){
			return true ;
		}


		else if(searchCheck(root,Target)) {
			return searchCheck(root,Target);
		}

		return false ;

	}



 	private boolean searchCheck(GeneralTreeNode<T> root, GeneralTreeNode<T>  Target) {

		boolean result = false;

		if ( root.compareTo(Target) == 0) {
			return true ;
		}


		else if(root.children == null){
			return false ;
		}



			for (int i = 0; i < root.children.size(); i++) {

				if (searchCheck(root.children.get(i),Target)) {
					result = true;
				}

			}

			return result ;

	}




	public String search(GeneralTreeNode<T> newNode) {

		if(searchCheck(newNode)) {
			if (root.el.equals(newNode.el)) {
				return root.el + "";
			}
			else {
				return search(root,newNode);
			}

		}

		else {
			System.out.println("The node your trying to search for does not exist");
		}


		return "" ;


		}



	private String search(GeneralTreeNode<T> root,GeneralTreeNode<T> newNode) {
		String path = "" ;
		if(root.children == null){
			return "";
		}

		if(check(root, newNode)){
			return root.el + " ---> " + newNode.el ;
		}

		else {
			int i = 0 ;

			while (!path.contains(newNode.el + "") && i < root.children.size()){
				path = root.el + " --- > " + search(root.children.get(i),newNode);
				i++ ;
			}
		}

		return path ;

	}

	private boolean check (GeneralTreeNode<T> tmp,GeneralTreeNode<T> node){
		for(int i = 0 ; i < tmp.children.size() ; i++){
			if(tmp.children.get(i).el.equals(node.el)){

				return true ;

			}
		}

		return false ;

	}


	private int checkIndex (GeneralTreeNode<T> tmp,GeneralTreeNode<T> node){
		for(int i = 0 ; i < tmp.children.size() ; i++){
			if(tmp.children.get(i).el.equals(node.el)){

				return i ;

			}
		}

		return 10 ;

	}






	public void delete (GeneralTreeNode<T> existingNode){
		if(root.compareTo(existingNode) == 0){
			root = null ;
		}

		else if(searchCheck(existingNode)) {
			delete(root, existingNode);
		}

		else {
			System.out.println("The node your trying to delete does not exist");
		}
	}




	private void delete(GeneralTreeNode<T> root,GeneralTreeNode<T> existingNode) {

		if (root.children == null){
			return;
		}

		if (check(root, existingNode)) {
			root.children.remove(checkIndex(root, existingNode));
			System.out.println("Node has been removed");
			return;

		} else {

			for (int i = 0; i < root.children.size(); i++) {
				delete(root.children.get(i),existingNode);

			}
		}


	}

	public void sortByLevel(){
		sortByLevel(root);
	}


	private void sortByLevel(GeneralTreeNode<T> root){
		if (root.children == null){
			return;
		}


		 else {

			Collections.sort(root.children);
		}

		for (int i = 0; i < root.children.size(); i++) {
			sortByLevel(root.children.get(i));

		}


	}

	public void setMax(int max) {
		this.MAXIMUM = max;
	}

	public void setRoot(GeneralTreeNode<T> root) {
		this.root = root;
	}

	public int countFiles(File file) {
		int k = 0;
		if (file.isDirectory()) {
			File[] F = file.listFiles();
			for (int i = 0; F != null & i < F.length; i++)
				k += countFiles(F[i]);
		} else
			k++;
		return k;

	}

	public int countFolders(File file) {
		int k = 0;
		if (file.isDirectory()) {
			File[] F = file.listFiles();
			for (int i = 0; F != null & i < F.length; i++)
				if(F[i].isDirectory()){
					k++;
					k+=countFolders(F[i]);
				}
		}

		return k;

	}



}
