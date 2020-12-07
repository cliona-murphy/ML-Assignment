import java.util.*;

public class Test {

	public Test() {
		
	}
	
	public ArrayList<Result> testProgram(String[][] testData, TreeNode tree) {
		int numAttributes = testData[0].length;
		ArrayList<Result> results = new ArrayList<Result>();
		ArrayList<String> attributes = new ArrayList<String>();
		
		for(int i = 0; i < numAttributes; i++) {
			attributes.add(testData[0][i]);				//storing indexes in attributes arraylist
		}
		
		for(int i = 1; i < testData.length; i++) {
			
			boolean classified = false; 
			TreeNode current = tree;
			String attribute;
			double threshold;
			int columnNum;
			String prediction = "";
			
			while(!classified) {
				attribute = current.getAttIdentifier();
				threshold = current.getThreshold();
				columnNum = Integer.parseInt(attribute);
				
				if(threshold == -1) {
					prediction = attribute;
					classified = true;
				}
				
				if(Double.parseDouble(testData[i][columnNum]) <= threshold) {
					if(current.getLeftChild() == null) {
						prediction = current.getAttIdentifier(); //needs to match to a string stating the class
						classified = true;
					} else {
						current = current.getLeftChild();
					}
						
				}else {
					if(current.getRightChild() == null) {
						prediction = current.getAttIdentifier();
						classified = true;
					} else {
						current = current.getRightChild();
					}
				}
			}
			
			String actualResult = testData[i][numAttributes];
			boolean result = prediction == actualResult ? true : false;
			
			Result testResult = new Result(result, prediction, actualResult);
			results.add(testResult);
		}
		
		return results;
	}
	
}