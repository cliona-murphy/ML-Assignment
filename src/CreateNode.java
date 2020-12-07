import java.util.*;
import java.util.Map.Entry;
public class CreateNode {
	
	private int numRows;
	private String[][] dataset = null;
	public ArrayList<String> classes = new ArrayList<String>();
	private double entropy;
	int classIndex = 3;
	
	public CreateNode(int numRows, String[][] dataset) {
		this.numRows = numRows;
		this.dataset = dataset;
	}
	
	public TreeNode createNextNode(String[][] dataset, int colNum) { 					//creates next node based on calculations of entropy and gain of a specific attribute
		ArrayList<Double> thresholdArray = new ArrayList<Double>(); //arraylist to store all possible threshold values
		String classType = "";
		double threshValue;
		double gain;
		int i = 0;
		
		while(i < numRows) {                                          //loop through dataset
			classType = dataset[i][classIndex];						//for 1st row assign value of class to classType
			
			if(!classes.contains(classType)){                               //checking if that class is already in the classes ArrayList
                classes.add(classType);                                  //if it's not, add it
            }
            threshValue = Double.parseDouble(dataset[i][colNum]);        //storing value of the attribute in colimn columnNum
            
            if(!thresholdArray.contains(threshValue)){                  //determining if this value is in array of threshold values
                thresholdArray.add(threshValue);                        //if it's not, add it
            }
            i++;  														//increment i
		}
		entropy = calculateEntropy();								//calculate entropy based on set of classes
		Map<Double, Double> thresholdEntropyMap = new HashMap<Double, Double>(thresholdArray.size());
		
		for(Double d: thresholdArray) {
			gain = getInformationGain(d, colNum);
			thresholdEntropyMap.put(d, gain);
		}
		
		double maxGain = -1;            
		double bestThreshold = -1;
	    double threshold;
        for (Entry<Double, Double> entry : thresholdEntropyMap.entrySet()){ //looping through map
            threshold = entry.getKey();                                  //obtaining threshold from hashtable
            gain = entry.getValue();                                   //obtaining gain associated with threshold from hashtable
            if(gain > maxGain){                                         //determining if gain is greater than the default maxGain
                maxGain = gain;                                       //if it is, assigning the value of gain to maxGain
                bestThreshold = threshold;                             //assigning value of threshold to bestThreshold
            }
        }

        return new TreeNode(null, null, null, bestThreshold, maxGain); //returning node with these values

	}
	
	public double calculateEntropy() {
		int[] numOccurences = new int[classes.size()];                  //array storing number of occurences of each class
		for(int i: numOccurences){                                      //initialising array
	        numOccurences[i] = 0;
	    }

        double entropy = 0;
        int j = 0;
        for(String s : classes){                                           //looping through list of classes
            for(int i = 0; i < dataset.length; i++){                    //looping through data set
                if(s.equals(dataset[i][classIndex])){                      //if class is the same as the class of the data in question
                    numOccurences[j]++;                                  //increment the number of occurences of this class
	            }
	        }
	        int datasetSize = dataset.length-1;                          //set datasetSize to be size of the dataset                    
	        entropy += (-1) * (numOccurences[j]/datasetSize) * (Math.log(numOccurences[j]/datasetSize) / Math.log(2));
	        j++;
        }
        return entropy;
	}
	public double getInformationGain(Double threshold, int column){

		double greaterThanCount = 0;					//Total num of elements greater than the threshold
		double lessThanCount = 0;					//Total num of elements less than or equal to the threshold
		ArrayList<String[]> lessThan = new ArrayList<String[]>();		//Stores the elements less than or equal to the threshold
		ArrayList<String[]> greaterThan = new ArrayList<String[]>();	//Stores the elements greater than the threshold	

		
		for(int i=0 ; i<numRows-1 ; i++){									//Sort the data into lists based on position about threshold
			if (Double.parseDouble(dataset[i][column]) <= threshold){
				lessThan.add(dataset[i]);
			}
			else{
				greaterThan.add(dataset[i]);
			}
		}
		double lessThanEntropy = 0;											
		double greaterThanEntropy = 0;
		
		for(int i=0 ; i<classes.size() ; i++ ){						//looping over possible classes
			String currentClass = classes.get(i);						//store class being used currently
			int currenClassCount = 0;								//count number of occurrences of this class
			
			for(int j=0 ; j<lessThan.size(); j++){					//Loop over elements less than the threshold
				String[] s = lessThan.get(j);
				if (s[classIndex].equals(currentClass)){			//check if data's class equals current class being checked for
					currenClassCount++;								//increment if a match is found
				}
			}
			
			lessThanCount = lessThan.size();
			if ((currenClassCount/lessThanCount) > 0){									//If there are occurrences of that class then calculate the entropy
				lessThanEntropy += (-1) * (currenClassCount/lessThanCount) * (Math.log(currenClassCount/lessThanCount) / Math.log(2));
			}
			else{													//no occurences = no effect on entropy
				lessThanEntropy -= 0;
			}
			
		}
		
		for(int i=0 ; i<classes.size() ; i++){						//looping over possible classes
			String currentClass = classes.get(i);						//store class being used currently
			int currentClassCount = 0;								//count number of occurrences
			for(int j=0 ; j<greaterThan.size(); j++){				//Loop over elements greater than the threshold
				String[] s = greaterThan.get(j);
				if (s[classIndex].equals(currentClass)){						//check if cases class equals current class being checked for
					currentClassCount++;								//increment if a match is found
				}
			}
			greaterThanCount = greaterThan.size();
			if(currentClassCount/greaterThanCount > 0){							
				greaterThanEntropy +=  (-1) * (currentClassCount/greaterThanCount) * (Math.log(currentClassCount/greaterThanCount)/ Math.log(2));
			}
			else{													// no occurences = no effect on entropy
				greaterThanEntropy -= 0;
			}
			 
		}
		int numSamples = (dataset.length - 1);
		double infoGain = entropy - ((lessThanEntropy*lessThanCount)/numSamples) - ((greaterThanEntropy*greaterThanCount)/numSamples); 
	
		return infoGain;	
	}														   
}
