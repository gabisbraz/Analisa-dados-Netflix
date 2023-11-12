public class BTNode {

	protected ProgramaNetFlix programa;
	protected BTNode parent;
	protected BTNode left;
	protected BTNode right;
	private int fb; // Fator de balanceamento

	public BTNode() {
		this(null, 0, null, null, null);
	}

	public BTNode(ProgramaNetFlix programa) {
		this(programa, null);
	}

	public BTNode(ProgramaNetFlix programa, BTNode parent) {
		this.fb = 0;
		this.programa = programa;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}

	public BTNode(
			ProgramaNetFlix e, int fb, BTNode pai, BTNode subEsq, BTNode subDir) {
		setData(e);
		this.fb = fb;
		setParent(pai);
		setLeft(subEsq);
		setRight(subDir);
	}

	public int getFb() {
		return fb;
	}

	public void setFb(int fb) {
		this.fb = fb;
	}

	public ProgramaNetFlix getData() {
		return programa;
	}

	public void setData(ProgramaNetFlix programa) {
		this.programa = programa;
	}

	public BTNode getParent() {
		return parent;
	}

	public void setParent(BTNode parent) {
		this.parent = parent;
	}

	public BTNode getLeft() {
		return left;
	}

	public void setLeft(BTNode left) {
		this.left = left;

		if (this.left != null) {
			this.left.setParent(this);
		}
	}

	public BTNode getRight() {
		return right;
	}

	public void setRight(BTNode right) {
		this.right = right;

		if (this.right != null) {
			this.right.setParent(this);
		}
	}

	public boolean hasLeftChild() {
		return left != null;
	}

	public boolean hasRightChild() {
		return right != null;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

	public int getDegree() {
		int degree = 0;

		if (hasLeftChild()) {
			++degree;
		}

		if (hasRightChild()) {
			++degree;
		}

		return degree;
	}

	public int getLevel() {
		if (isRoot()) {
			return 0;
		}

		return parent.getLevel() + 1;
	}

	public int getHeight() {
		if (isLeaf()) {
			return 0;
		}

		int height = 0;

		if (hasLeftChild()) {
			height = Math.max(height, left.getHeight());
		}

		if (hasRightChild()) {
			height = Math.max(height, right.getHeight());
		}

		return height + 1;
	}

	@Override
	public String toString() {
		return "programa: " + programa
				+ ", parent: " + (parent != null ? parent.getData() : "null")
				+ ", left: " + (left != null ? left.getData() : "null")
				+ ", right: " + (right != null ? right.getData() : "null")
				+ ", isRoot(): " + isRoot()
				+ ", isLeaf(): " + isLeaf()
				+ ", getDegree(): " + getDegree()
				+ ", getLevel(): " + getLevel()
				+ ", getHeight(): " + getHeight();
	}

	public int compareTo(ProgramaNetFlix programa) {
        if (programa == null) {
            return 1;
        }

        return 0;
    }
}
