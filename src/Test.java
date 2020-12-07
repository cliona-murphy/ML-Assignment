import java.util.*;

public class Test {

	public Test() {
		
	}
	//accepts the testSet, the tree, and the array of classes
	public ArrayList<Result> testProgram(String[][] testSet, TreeNode tree, ArrayList<String> classes) {
		
		int numAttributes = testSet[0].length-1;					//storing the number of attributes in test set
		ArrayList<Result> results = new ArrayList<Result>();		//creating ArrayList to store results of each piece of data in dataset
		
		for(int i = 1; i < testSet.length; i++) {					//skipping first row because this stores attributes indexes
			
			boolean classified = false; 							//boolean to determine whether data has been classified by algorithm or not
			TreeNode current = tree;								//storing tree passed in in node current
			double threshold;										//double to store threshold
			int columnNum;											//int to store column number
			String prediction = "";									//String to store prediction of class for piece of data
			
			while(!classified) {									//loop through while classified is false
				threshold = current.getThreshold();					//set threshold to be equal to the threshold of the current node
				columnNum = Integer.parseInt(current.getAttIdentifier());			//set columnNum to be equal to the value of the attribute identifier
				
				if(threshold == -1) {								//checking if threshold is = -1
					prediction = classes.get(Integer.parseInt(current.getAttIdentifier()));//if so, the prediction is set to the corresponding class in classes
					classified = true;								//classified set to true
				}
				
				if(Double.parseDouble(testSet[i][columnNum]) <= threshold) { //if the value of the attribute is less than or equal threshold value
					if(current.getLeftChild() == null) {					//check if the left child of the current node is null
						prediction = classes.get(Integer.parseInt(current.getAttIdentifier()));	//if so, set prediction to be the class of the current node 
						classified = true;									//classified set to true
					} else {
						current = current.getLeftChild();					//else the current node is set to the left child of the current node
					}
						
				}else {
					if(current.getRightChild() == null) {				//if value of attribute is greater than threshold value
						prediction = classes.get(Integer.parseInt(current.getAttIdentifier()));	//prediction is set to be the class of the current node
						classified = true;								//classified is set to true
					} else {
						current = current.getRightChild();				//else then current node becomes the right child of the current node
					}
				}
			}
			
			String actualResult = testSet[i][0];			//storing the actual class of the piece of data 
			boolean result = prediction == actualResult ? true : false; //determining if the predicted result is true or false based on this 
			
			Result testResult = new Result(result, prediction, actualResult); //creating a result object with this data
			results.add(testResult);						//adding this result to the arraylist of results
		}
		
		return results;										//returning the results
	}
	
}