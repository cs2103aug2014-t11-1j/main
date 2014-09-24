package storage;

/** 
 * @author: zhang yongkai
 * this storage will load from txt file or write to text file
 * to use, Storage  = new TaskManager("text.txt");  = Storage.load();
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Storage {

    private ObservableList <ModelTask> list;
    private File inputFile;

        
    //constructor
    public Storage (String inputFileName) throws IOException {
        initialize(inputFileName);
    }

    
    private void initialize(String inputFileName) throws IOException {

        inputFile = new File(inputFileName);
        inputFile.createNewFile();
        
        initializeObservableList();
 
    }

    //writes existing contents of file to observableList if any
    private void initializeObservableList() throws FileNotFoundException {

        list = FXCollections.observableArrayList();
        Scanner sc = new Scanner(inputFile);
        copyContentsOfTextFile(sc);
        sc.close();
    }

    private void copyContentsOfTextFile(Scanner sc) {
    	TaskConverter taskConverter = new TaskConverter();
    	
		while(sc.hasNext()){
			int index = 1;
			String str = sc.nextLine();
			Task newTask = new Task(str,true);
			list.add (ModelTask taskConverter.convert(newTask,index));  //need to convert ot modeltask
			index++;
		}	
	}
    
     
  
    //writes contents of observableList to text file
    private void save() throws IOException {

        FileWriter fileWriter = new FileWriter(inputFile, false);
        BufferedWriter buffer = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(buffer);
        ModelTaskToSaveStringConverter converter;
        for (int i = 0; i < list.size(); i++) {
        	converter = new ModelTaskToSaveStringConverter(list.get(i));
            printWriter.println(converter.getSaveString());
        }

        printWriter.close();
        buffer.close();
        fileWriter.close();
    }

}
    