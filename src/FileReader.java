import java.io.*;
import java.util.*;

public class FileReader {
	
	String[] alldata = new String[11];
	String[][] dataset = new String[11][];
	String[][] datasetWithClasses = new String[11][];
	int classIndex = 3;
	
	public void readData() {
		int i = 0;
		{
		//read in file
		try {
			File myObj = new File("C:\\Users\\Clíona's PC\\Documents\\Final Year\\Machine Learning\\MLData.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        alldata[i] = data;
		        i++;
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		}
	}
	public String[][] convertTo2DArray() {
		for(int i = 0; i<11; i++) {
			String rowData = alldata[i];
			String[] temp1 = rowData.split("\t");
			String[] temp2 = new String[9];
			System.arraycopy(temp1, 0, temp2, 0, 3);
			System.arraycopy(temp1, 4, temp2, 3, 6);
			dataset[i] = temp2;			
		}
		return dataset;
		
	}
	public String[][] convertTo2DArrayWithClasses() {
		for(int i = 0; i<11; i++) {
			String rowData = alldata[i];
			String[] temp1 = rowData.split("\t");
			datasetWithClasses[i] = temp1;			
		}
		return dataset;
		
	}
}