import java.util.*;

public class GeneralTreeNode<T extends Comparable<? super T>> implements Comparable<GeneralTreeNode<T>> {
	protected T el;
	protected ArrayList<GeneralTreeNode<T>> children;
	protected int MAXIMUM = 10;

	public GeneralTreeNode() {


	}

	public GeneralTreeNode(T el) {
		this(el, null);
	}

	public GeneralTreeNode(T el, ArrayList<GeneralTreeNode<T>> children) {
		this.el = el;
		this.children = children;
	}

	public ArrayList<GeneralTreeNode<T>> getChildren() {
		if (children == null) {
			return null;
		} else
			return children;
	}

	public int childCount() {
		if (this.children == null)
			return 0;
		else
			return children.size();
	}

	public void addChild(GeneralTreeNode<T> child) {
		if(this.childCount() < MAXIMUM) {

			if (this.children == null) {
				children = new ArrayList<>();
			}
			children.add(child);
		}
		else {
			System.out.println("Maximum number of children reached");
		}

	}

	public T getEl() {
		return el;
	}

	public void setChildren(ArrayList<GeneralTreeNode<T>> children) {
		this.children = children;
	}

	public void removeChild(int index) throws IndexOutOfBoundsException {
		children.remove(index);
	}

	public void setMax(int max) {
		this.MAXIMUM = max;
	}


	private void printNode(StringBuilder stringBuilderBuffer, String prefix, String childrenPrefix) {
		stringBuilderBuffer.append(prefix);
		stringBuilderBuffer.append(el);
		stringBuilderBuffer.append('\n');
		if (children != null) {
			for (Iterator<GeneralTreeNode<T>> iterator = children.iterator(); iterator.hasNext(); ) {
				GeneralTreeNode<T> nextNode = iterator.next();
				if (iterator.hasNext()) {
					nextNode.printNode(stringBuilderBuffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
				} else {
					nextNode.printNode(stringBuilderBuffer, childrenPrefix + "└── ", childrenPrefix + "    ");
				}
			}
		}
	}

	public String printNode() {
		StringBuilder stringBuilderBuffer = new StringBuilder(50);
		printNode(stringBuilderBuffer, "", "");
		return stringBuilderBuffer.toString();
	}


	@Override
	public int compareTo(GeneralTreeNode<T> o) {
		if (this.el.compareTo(o.el) > 0) {
			return 1;
		} else if (this.el.compareTo(o.el) < 0) {
			return -1;
		}

		else {
			return 0 ;
		}


	}

}