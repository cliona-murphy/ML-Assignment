
public class TreeNode {
	
    private TreeNode parent;
    private TreeNode rightChild;
    private TreeNode leftChild;
    private double threshold;
    private double infoGain;

    public TreeNode(TreeNode parent, TreeNode rChild, TreeNode lChild, double thresh, double infoGain){
        this.parent = parent;
        rightChild = rChild;
        leftChild = lChild;
        threshold = thresh;
        this.infoGain = infoGain;
    }

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public TreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public TreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double getInfoGain() {
		return infoGain;
	}

	public void setInfoGain(double infoGain) {
		this.infoGain = infoGain;
	}
}
