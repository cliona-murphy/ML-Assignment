import java.util.*;
import java.io.*;

public class Classifier {
		
	public static FileReader fr;
	public static String[][] dataset; 								//dataset with class attribute removed
	public static String[][] datasetWClass;
	public static CreateNode nextNode;
	public static PrepareData prepData;
	
	public static void main(String args[]) {
		fr = new FileReader();							//dataset with class attribute included (needed for entropy calculations)
		fr.readData();										//reads data in
		
		dataset = fr.convertTo2DArray();
		datasetWClass = fr.convertTo2DArrayWithClasses();
		System.out.print(Arrays.deepToString(dataset));
		
	    nextNode = new CreateNode(fr.getNumRows(), datasetWClass); 
		
		prepData = new PrepareData(fr.getNumRows(), dataset);
		dataset = prepData.orderByCol(0); //not fully working - stopping after 4 
		System.out.println("\nData in dataset ordered by column 0 = ");
		System.out.print("\n"+Arrays.deepToString(dataset));	
		
		prepData.shuffleData(dataset); //seems to work
		System.out.print("\n"+Arrays.deepToString(dataset));
		
		String[][] trainingSet = prepData.generateTrainingSet(dataset); //working
		System.out.print("\n"+Arrays.deepToString(trainingSet));
		
		String[][] testSet = prepData.generateTestSet(dataset); //working
		System.out.print("\n"+Arrays.deepToString(testSet));
		
	}
	
	public TreeNode treeClassifier(String[][] data) { 
		
		int i = 0;
		TreeNode best = nextNode.createNextNode(dataset, i);
		double threshold = best.getThreshold();
		double infoGain = best.getInfoGain();
		TreeNode leftChildNode = null;
		TreeNode rightChildNode = null;
		String[][] leftChild = prepData.splitNode(threshold, i, true);
		String[][] rightChild = prepData.splitNode(threshold, i, false);
		leftChildNode = treeClassifier(leftChild);
		rightChildNode = treeClassifier(rightChild);
		
		return new TreeNode(best, leftChildNode, rightChildNode, threshold, infoGain);		
	}
}
