import java.util.*;

public class PrepareData {
	private int numRows;
	private String[][] dataset;
	
	public PrepareData(int numRows, String[][] dataset) {
		this.numRows = numRows;
		this.dataset = dataset;
	}
	
	//method to order dataset based on the column 
	public String[][] orderByCol(int colNum) {
		String[] valuesToOrder = new String[numRows];		//array to store all values in specified column
		
		for(int i = 0; i < numRows; i++) {					//loops through array
			valuesToOrder[i] = dataset[i][colNum];			//populates array with value for the column in each row
		}
		sortAsc(valuesToOrder);								//passes array to sortAsc which sorts the array in ascending order
		return dataset;										//returns the sorted datase
	}
	
	public void sortAsc(String[] vals) { 					//sorts array in ascending order using bubble sort
		 String[] tempArray;								//temp array to store values
	     String temp;										//temp string to store values

	     for(int i=0; i<vals.length; i++){					//loops through the array
            	for(int j = i+1; j<vals.length-1; j++){		//loops through the array 1 place ahead of previous loop
                if(Double.parseDouble(vals[i]) > Double.parseDouble(vals[j])){ //compares 2 values next to each other
                    tempArray = dataset[i];					//if 1st val is greater than 2nd val the tempArray holds the corresponding line of data in the dataset
                    temp = vals[i];							//temp stores the value stored in this position in vals
                    dataset[i] = dataset[j];				//the lines of data in dataset are switched
                    dataset[j] = tempArray;
                    vals[j] = temp;							//the value of temp is placed back into the vals array in correct position
                }
            }
        }
	}
	
	//method to shuffle the data randomly
	public String[][] shuffleData(String[][] data) {
		List<String[]> listToShuffle= new ArrayList<String[]>(); //ArrayList to store values to shuffle
		listToShuffle = Arrays.asList(data);					//convert array passed in to an arraylist
		//String[] header = listToShuffle.get(0);
		listToShuffle.remove(0);								//removes 1st line with attribute identifiers before shuffling
		Collections.shuffle(listToShuffle);						//shuffle the list
		//listToShuffle.add(header);							//option to add back in first line if needed?
		String[][] shuffledData = (String[][]) listToShuffle.toArray(); //convert list back to array
		return shuffledData;									//returns shuffled array
    }
	
	//generates the training set for the program
	public String[][] generateTrainingSet(String[][] data) {
		int setSize = (int)(data.length/3) * 2; 				//sets size of training set to  2/3 of size of dataset
		String[][] trainingSet = new String[setSize][];			//initializing the array to store training set
		
		for(int i = 0; i < setSize; i++) {						//loop through dataset
			trainingSet[i] = data[i];							//populate training set with data
		}
		return trainingSet;										//return training set
	}
	
	
	//generates the test data for the program
	public String[][] generateTestSet(String[][] data){
		int setSize = (int)(data.length/3); 					//sets size of training set to  2/3 of size of dataset
		String[][] testSet = new String[setSize][];				//initializing the array to store test set
		
		for(int i = 0; i < setSize; i++) {						//loop through dataset
			testSet[i] = data[i];								//populate test set with data
		}
		return testSet;											//return test set
	}
	
	// method which splits the dataset based on the node with the highest information gain and returns either the dataset which is less than the 
	// threshold or the dataset greater than the threshold based on whether returnLeftSide is set to true or false
	public String[][] splitNode(double threshold, int attributeIndex, boolean returnLeftSide){ 
		String[][] leftSideDataset = new String[dataset.length][]; //array to store the left child of node
		String[][] rightSideDataset = new String[dataset.length][]; //array to store the right child of node
		
		for(int i = 1; i < dataset.length-1; i++) {					//loop through dataset skipping 1st line MAY NOT BE NEEDED BECAUSE 1ST LINE IS REMOVED IN SHUFFLE DATA
			if(Integer.parseInt(dataset[i][attributeIndex]) < threshold) { //checking if the value at index is less than the threshold
				leftSideDataset[i] = dataset[i];					//if so, add line of data to leftChild array
			}
			else {
				rightSideDataset[i] = dataset[i];					//else add line of data to rightChild array
			}
		}
		if (returnLeftSide) {										//if return left side is true
			return leftSideDataset;									//return the left child
		} else {
			return rightSideDataset;								//else return the right child
		}		
	}
	
}
