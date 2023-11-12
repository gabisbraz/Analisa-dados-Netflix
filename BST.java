public class BST extends BinaryTree {

	public BST() {
		super();
	}

	public BST(BTNode root) {
		super(root);
	}

	public BTNode search(ProgramaNetFlix programa) {
		return search(programa, false);
	}

	public BTNode searchIgnoreCase(ProgramaNetFlix programa) {
		return search(programa, true);
	}

	private BTNode search(ProgramaNetFlix programa, boolean ignoreCase) {
		return searchHelper(super.getRoot(), programa, ignoreCase);
	}

	private BTNode searchHelper(BTNode node, ProgramaNetFlix programa, boolean ignoreCase) {
		if (node == null) {
			return null;
		}

		int diff = diffCompare(programa, node.getData(), ignoreCase);

		if (diff < 0) {
			return searchHelper(node.getLeft(), programa, ignoreCase);
		} else if (diff > 0) {
			return searchHelper(node.getRight(), programa, ignoreCase);
		} else {
			return node;
		}
	}

	public void insert(ProgramaNetFlix programa) {
		insert(programa, false);
	}

	public void insertIgnoreCase(ProgramaNetFlix programa) {
		insert(programa, true);
	}

	private void insert(ProgramaNetFlix programa, boolean ignoreCase) {
		super.setRoot(insertHelper(super.getRoot(), null, programa, ignoreCase));
	}

	private BTNode insertHelper(BTNode node, BTNode parent, ProgramaNetFlix programa, boolean ignoreCase) {
		if (node == null) {
			return new BTNode(programa, parent);
		}

		int diff = diffCompare(programa, node.getData(), ignoreCase);

		if (diff < 0) {
			node.setLeft(insertHelper(node.getLeft(), node, programa, ignoreCase));
		} else if (diff > 0) {
			node.setRight(insertHelper(node.getRight(), node, programa, ignoreCase));
		} else {
			// Nessa implementação, não é permitida a inserção de duplicatas na BST.
		}

		return node;
	}

	public boolean remove(String programaID) {
		remove(programaID, false);
		return true;
	}

	public void removeIgnoreCase(String programaID) {
		remove(programaID, true);
	}

	private void remove(String programaID, boolean ignoreCase) {
		super.setRoot(removeHelper(super.getRoot(), programaID, ignoreCase));
	}

	private BTNode removeHelper(BTNode node, String programaID, boolean ignoreCase) {
		if (node == null) {
			return null;
		}

		int diff = diffCompareID(programaID, node.getData().getId(), ignoreCase);

		if (diff < 0) {
			node.setLeft(removeHelper(node.getLeft(), programaID, ignoreCase));
		} else if (diff > 0) {
			node.setRight(removeHelper(node.getRight(), programaID, ignoreCase));
		} else {
			node = removeNode(node, ignoreCase);
		}

		return node;
	}

private BTNode removeNode(BTNode node, boolean ignoreCase) {
    if (node.isLeaf()) {
        return null;
    }

    if (!node.hasLeftChild()) {
        return node.getRight();
    } else if (!node.hasRightChild()) {
        return node.getLeft();
    } else {
        BTNode predecessor = findPredecessor(node.getData());
        node.setData(predecessor.getData());
        node.setLeft(removeHelper(node.getLeft(), predecessor.getData().getId(), true));
    }

    return node;
}


	public BTNode findMin() {
		return findMinHelper(super.getRoot());
	}

	private BTNode findMinHelper(BTNode node) {
		if (node == null) {
			return null;
		} else {
			while (node.hasLeftChild()) {
				node = node.getLeft();
			}
			return node;
		}
	}

	public BTNode findMax() {
		return findMaxHelper(super.getRoot());
	}

	private BTNode findMaxHelper(BTNode node) {
		if (node == null) {
			return null;
		} else {
			while (node.hasRightChild()) {
				node = node.getRight();
			}
			return node;
		}
	}

	public BTNode findPredecessor(ProgramaNetFlix programa) {
		return predecessor(programa, false);
	}

	public BTNode findPredecessorIgnoreCase(ProgramaNetFlix programa) {
		return predecessor(programa, true);
	}

	private BTNode predecessor(ProgramaNetFlix programa, boolean ignoreCase) {
		BTNode node = search(programa, ignoreCase);
		return node == null ? null : predecessorHelper(node, ignoreCase);
	}

	private BTNode predecessorHelper(BTNode node, boolean ignoreCase) {
		if (node.hasLeftChild()) {
			return findMaxHelper(node.getLeft());
		} else {
			BTNode current = node;
			BTNode parent = node.getParent();

			while (parent != null && current == parent.getLeft()) {
				current = parent;
				parent = current.getParent();
			}

			return parent;
		}
	}

	public BTNode findSuccessor(ProgramaNetFlix programa) {
		return successor(programa, false);
	}

	public BTNode findSuccessorIgnoreCase(ProgramaNetFlix programa) {
		return successor(programa, true);
	}

	private BTNode successor(ProgramaNetFlix programa, boolean ignoreCase) {
		BTNode node = search(programa, ignoreCase);
		return node == null ? null : successorHelper(node, ignoreCase);
	}

	private BTNode successorHelper(BTNode node, boolean ignoreCase) {
		if (node.hasRightChild()) {
			return findMinHelper(node.getRight());
		} else {
			BTNode current = node;
			BTNode parent = node.getParent();

			while (parent != null && current == parent.getRight()) {
				current = parent;
				parent = current.getParent();
			}

			return parent;
		}
	}

    private int diffCompare(ProgramaNetFlix programa1, ProgramaNetFlix programa2, boolean ignoreCase) {
        if (ignoreCase) {
            return programa1.getId().compareToIgnoreCase(programa2.getId());
        } else {
            return programa1.getId().compareTo(programa2.getId());
        }
    }

    private int diffCompareID(String programId1, String programId2, boolean ignoreCase) {
        if (ignoreCase) {
            return programId1.compareToIgnoreCase(programId2);
        } else {
            return programId1.compareTo(programId2);
        }
    }

	@Override
	public String toString() {
		return "BST - isEmpty(): " + isEmpty()
				+ ", getDegree(): " + getDegree()
				+ ", getHeight(): " + getHeight()
				+ ", root => { " + super.getRoot().getData() + " }";
	}

}
