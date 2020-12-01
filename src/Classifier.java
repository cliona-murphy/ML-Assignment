import java.util.*;
import java.io.*;

public class Classifier {
	
	public static void main(String args[]) {
		FileReader fr = new FileReader();
		String[][] dataset;
		String[][] datasetWClass;
		fr.readData();
		//dataset is the set of data with the values for the class removed (so it is only numerical data)
		dataset = fr.convertTo2DArray();
		datasetWClass = fr.convertTo2DArrayWithClasses();
		System.out.print(Arrays.deepToString(dataset));
		
		CreateNode infogain = new CreateNode(fr.getNumCols() , fr.getNumRows(), datasetWClass); //information gain has no functionality yet
		
		PrepareData prepData = new PrepareData(fr.getNumCols(), fr.getNumRows(), dataset);
		dataset = prepData.orderByCol(0); //not fully working
		System.out.println("\nData in dataset ordered by column 0 = ");
		System.out.print("\n"+Arrays.deepToString(dataset));	
		
		prepData.shuffleData(dataset); //seems to work
		System.out.print("\n"+Arrays.deepToString(dataset));
		
		String[][] trainingSet = prepData.generateTrainingSet(dataset); //working
		System.out.print("\n"+Arrays.deepToString(trainingSet));
		
		String[][] testSet = prepData.generateTestSet(dataset); //working
		System.out.print("\n"+Arrays.deepToString(testSet));
	}
}
