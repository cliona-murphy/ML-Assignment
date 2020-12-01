import java.util.*;
import java.io.*;

public class Classifier {
	
	public static void main(String args[]) {
		FileReader fr = new FileReader();
		String[][] dataset; 								//dataset with class attribute removed
		String[][] datasetWClass;							//dataset with class attribute included (needed for entropy calculations)
		fr.readData();										//reads data in
		
		dataset = fr.convertTo2DArray();
		datasetWClass = fr.convertTo2DArrayWithClasses();
		System.out.print(Arrays.deepToString(dataset));
		
		CreateNode nextNode = new CreateNode(fr.getNumRows(), datasetWClass); 
		
		PrepareData prepData = new PrepareData(fr.getNumRows(), dataset);
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
}
