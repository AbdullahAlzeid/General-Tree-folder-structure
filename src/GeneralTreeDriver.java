import java.util.*;
import java.io.*;

public class GeneralTreeDriver {

	public static void main(String[] args) {
		boolean valid = false;
		do {
			try {
				Scanner kb = new Scanner(System.in);
				String s;
				String path = "C:\\Users\\a_alz\\Desktop\\LabQuiz02";
				File folder = new File(path);
				GeneralTreeNode<String> root = new GeneralTreeNode<>(folder.getName());
				BuildTree(folder, root);
				GeneralTree<String> myTree = new GeneralTree<String>(root);
				boolean statue = true;
				do {
					int choice = menu();
					if (choice == 1) {
						myTree.printTree();
					} else if (choice == 2) {
						System.out.println("Enter the node you want to search for : ");
						path = kb.nextLine();
						GeneralTreeNode<String> node = new GeneralTreeNode<String>(path);
						System.out.println(myTree.search(node));

					} else if (choice == 3) {
						System.out.println("Enter BFS , Postorder or Preorder to display");
						path = kb.nextLine();
						if ("postorder".equalsIgnoreCase(path)) {
							myTree.postorder();
						} else if ("preorder".equalsIgnoreCase(path)) {
							myTree.preorder();
						} else if ("bfs".equalsIgnoreCase(path)) {
							myTree.BFS();
						} else {
							System.out.println("Sorry invalid traversal");
						}

					} else if (choice == 4) {
						myTree.sortByLevel();
						myTree.printTree();

					} else if (choice == 5) {
						System.out.println("Enter the node you want to delete : ");
						path = kb.nextLine();
						GeneralTreeNode<String> node = new GeneralTreeNode<String>(path);
						myTree.delete(node);

					} else if (choice == 6) {
						System.out.println("Enter the parent node and the node you want to add : ");
						path = kb.nextLine();
						s = kb.nextLine();
						GeneralTreeNode<String> parentnode = new GeneralTreeNode<String>(path);
						GeneralTreeNode<String> node = new GeneralTreeNode<String>(s);
						myTree.insert(parentnode, node);
					} else if (choice == 7) {
						System.out.println("Number of folders : " + myTree.countFolders(folder));
						System.out.println("Number of files : " + myTree.countFiles(folder));
					} else if (choice == 8) {
						System.out.println("Terminating...");
						statue = false;
						valid = false;
					}
				} while (statue);


			} catch (InputMismatchException e) {
				System.out.println("invalid input");
				valid = true ;
			}

		}
		while (valid);





	}

	public static int menu() {
		Scanner kb = new Scanner(System.in);
		int choice;
		do {
			System.out.print("Please enter your choice: " + "\n");
			System.out.println("1- Display tree");
			System.out.println("2- Search for a node");
			System.out.println("3- Print the tree in BFS, PreOrder, or PostOrder");
			System.out.println("4- Sort the tree and print it");
			System.out.println("5- Delete a Node");
			System.out.println("6- insert a node");
			System.out.println("7- display number of files and number folders");
			System.out.println("8- Exit");

			choice = kb.nextInt();
		} while (choice < 1 || choice > 8);
		return choice;
	}

	public static void BuildTree(File folder, GeneralTreeNode<String> root) {
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				GeneralTreeNode<String> node = new GeneralTreeNode<>(files[i].getName());
				root.addChild(node);
			} else if (files[i].isDirectory()) {
				GeneralTreeNode<String> node = new GeneralTreeNode<>(files[i].getName());
				root.addChild(node);
				folder = files[i];
				BuildTree(folder, node);
			}
		}

	}
}