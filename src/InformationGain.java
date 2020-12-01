import java.util.*;
import java.util.Map.Entry;
public class InformationGain {
	
	private int numColumns;
	private int numRows;
	private String[][] dataset = null;
	private ArrayList<String> classes = new ArrayList<String>();
	private double totalEntropy;
	int classIndex = 3;
	
	public InformationGain(int numColumns, int numRows, String[][] dataset) {
		this.numColumns = numColumns;
		this.numRows = numRows;
		this.dataset = dataset;
	}
	
	public TreeNode calculateThreshold(int colNum) {
		int i = 1;
		ArrayList<Double> thresholdArray = new ArrayList<Double>();
		double threshValue;
		double gain;
		String classType = "";
		
		while(i<numRows) {
			classType = dataset[i][classIndex];
			if(!classes.contains(classType)){                               //checking if that class is already in the classes ArrayList
                classes.add(classType);                                  //if it's not, add it
            }
            threshValue = Double.parseDouble(dataset[i][colNum]);                            //storing value of the attribute in colimn columnNum
            if(!thresholdArray.contains(threshValue)){                  //determining if this value is in array of threshold values
                thresholdArray.add(threshValue);                        //if it's not, add it
            }
            i++;  
		}
		totalEntropy = getTotalEntropy();
		HashMap<Double, Double> thresholdEntropy = new HashMap<Double, Double>(thresholdArray.size());
		for(Double d: thresholdArray) {
			gain = getEntropyAndGain(d, colNum);
		}
		
		double maxGain = -1000;            
		double bestThreshold = -1000;
	    double threshold;
        for (Entry<Double, Double> entry : thresholdEntropy.entrySet()){ //looping through hashtable
            threshold = entry.getKey();                                  //obtaining threshold from hashtable
            gain = entry.getValue();                                   //obtaining gain associated with threshold from hashtable
            if(gain > maxGain){                                         //determining if gain is greater than the default maxGain
                maxGain = gain;                                       //if it is, assigning the value of gain to maxGain
                bestThreshold = threshold;                             //assigning value of threshold to bestThreshold
            }
        }

        return new TreeNode(dataset[0][colNum], null, null, null, bestThreshold, maxGain); //returning node with these values

	}
	
	public double getTotalEntropy() {
		int[] numOccurences = new int[classes.size()];
		 for(int i: numOccurences){                                      //initialising array
	            numOccurences[i] = 0;
	        }

	        double entropy = 0;
	        int j = 0;
	        for(String s : classes){                                           //looping through list of classes
	            for(int i = 1; i < dataset.length; i++){                    //looping through data set
	                if(s.equals(dataset[i][classIndex])){                      //if class is the same as the class of the data in question
	                    numOccurences[j]++;                                  //increment the number of occurences of this class
	                }
	            }
	            int occurences = numOccurences[j];                           //store occurences in var occurences
	            int datasetSize = dataset.length-1;                          //set datasetSize to be size of the dataset
	            double fraction = (occurences/datasetSize);                     
	            entropy = (-1) * fraction * (Math.log(fraction) / Math.log(2));
	            j++;
	        }
	        return entropy;
	}
	public double getEntropyAndGain(Double threshold, int column){

		double greaterThanCount = 0;					//Total num of elements greater than the threshold
		double lowerThanCount = 0;					//Total num of elements less than or equal to the threshold
		ArrayList<String[]> lessThan = new ArrayList<String[]>();		//Stores the elements less than or equal to the threshold
		ArrayList<String[]> greaterThan = new ArrayList<String[]>();	//Stores the elements greater than the threshold	

		
		for(int i=0 ; i<numRows-1 ; i++){									//Sort the data into lists based on position about threshold
			
			if (Double.parseDouble(dataset[i+1][column]) <= threshold){
				lessThan.add(dataset[i+1]);
			}
			else{
				greaterThan.add(dataset[i+1]);
			}
		}
		float lessThanEntropy = 0;											
		float greaterThanEntropy = 0;
		
		/**** Less than or equal to threshold calculations ****/
		for(int i=0 ; i<classes.size() ; i++ ){						//looping over possible classes
			String currentClass = classes.get(i);						//store class being used currently
			int currenClassCount = 0;								//count number of occurrences 
			
			for(int j=0 ; j<lessThan.size(); j++){					//Loop over elements less than the threshold
				String[] s = lessThan.get(j);
				if (s[classIndex].equals(currentClass)){						//check if cases class equals current class being checked for
					currenClassCount++;								//increment if a match is found
				}
			}
			
			double fraction;
			double occurences = currenClassCount;
			lowerThanCount = lessThan.size();
			if (occurences > 0){									//If there are occurrences of that class then calculate the entropy
				fraction = occurences/lowerThanCount;
				lessThanEntropy += (-1) * fraction * (Math.log(fraction + Math.log(2)));
			}
			else{													//If no occurrences - no effect on entropy
				fraction = 0;
				lessThanEntropy -= 0;
			}
			
		}
		
		for(int i=0 ; i<classes.size() ; i++ ){						//looping over possible classes
			String currentClass = classes.get(i);						//store class being used currently
			int currentClassCount = 0;								//count number of occurrences
			for(int j=0 ; j<greaterThan.size(); j++){				//Loop over elements greater than the threshold
				String[] s = greaterThan.get(j);
				if (s[classIndex].equals(currentClass)){						//check if cases class equals current class being checked for
					currentClassCount++;								//increment if a match is found
				}
			}
			double fraction;
			double occurences = currentClassCount;
			greaterThanCount = greaterThan.size();
			if(occurences > 0){										//If there are occurrences of that class then calculate the entropy
				fraction = occurences/greaterThanCount;
				greaterThanEntropy +=  (-1) * fraction * (Math.log(fraction + Math.log(2)));
			}
			else{													//If no occurrences - no effect on entropy
				fraction = 0;
				greaterThanEntropy -= 0;
			}
			 
		}
		//Calculate the entropy - provides a measure of how well the selected threshold divides the remaining data
		double InfoGain = getInformationGain(lessThanEntropy, greaterThanEntropy, lowerThanCount, greaterThanCount);
	
		return InfoGain;
	
	}
	private double getInformationGain(float lowerThanEnt, float greaterThanEnt, double lowerThanTotal, double greaterThanTotal) {
		int numSamples = (dataset.length - 1);
		double gain = totalEntropy - ((lowerThanEnt*lowerThanTotal)/numSamples) - ((greaterThanEnt*greaterThanTotal)/numSamples); 
		return gain;														   
		
	}
}