import java.util.*;

public class PrepareData {
	private int numRows;
	private String[][] dataset;
	
	public PrepareData(int numRows, String[][] dataset) {
		this.numRows = numRows;
		this.dataset = dataset;
	}
	
	public String[][] orderByCol(int colNum) {
		String[] valuesToOrder = new String[numRows];
		
		for(int i = 0; i < numRows; i++) {
			valuesToOrder[i] = dataset[i][colNum];
		}
		sortAsc(valuesToOrder);
		return dataset;
	}
	
	public void sortAsc(String[] vals) { //works for first few values but not for all...stops working after 3
		 String[] tempArray;
	     String temp;

	     for(int i=0; i<vals.length; i++){
            	for(int j = i+1; j<vals.length-1; j++){
                if(Double.parseDouble(vals[i]) > Double.parseDouble(vals[j])){
                    tempArray = dataset[i];
                    temp = vals[i];
                    dataset[i] = dataset[j];
                    dataset[j] = tempArray;
                    vals[j] = temp;
                }
            }
        }
	}
	
	public void shuffleData(String[][] data) {
		List<String[]> listToShuffle= new ArrayList<String[]>();
		listToShuffle = Arrays.asList(data);
		Collections.shuffle(listToShuffle);
    }
	
	public String[][] generateTrainingSet(String[][] data) {
		int setSize = (int)(data.length/3) * 2; //using 2/3 data for training set
		String[][] trainingSet = new String[setSize][];
		shuffleData(data);
		
		for(int i = 0; i < setSize; i++) {
			trainingSet[i] = data[i];
		}
		return trainingSet;
	}
	
	public String[][] generateTestSet(String[][] data){
		int setSize = (int)(data.length/3); //using 1/3 data for test set
		String[][] testSet = new String[setSize][];
		shuffleData(data);
		
		for(int i = 0; i < setSize; i++) {
			testSet[i] = data[i];
		}
		return testSet;
	}
}
