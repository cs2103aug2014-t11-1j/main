package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import com.ModelTask;
import com.Task;
import com.TaskConverter;
import com.util.MyLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//@author A0110567L
/**
 * this storage will load from phantomStorage.txt file or write to text file
 */
public class Storage {
	private ObservableList<ModelTask> list;
	private File inputFile;

	// constructor
	public Storage(String inputFileName) throws IOException {

		MyLogger.log(Level.INFO, "initializing storage");
		initialize(inputFileName);

	}

	private void initialize(String inputFileName) throws IOException {

		inputFile = new File(inputFileName);
		inputFile.createNewFile();
		initializeObservableList();

	}

	// writes existing contents of file to observableList if any
	private void initializeObservableList() throws FileNotFoundException {

		list = FXCollections.observableArrayList();
		Scanner sc = new Scanner(inputFile);
		copyContentsOfTextFile(sc);
		sc.close();
	}

	private void copyContentsOfTextFile(Scanner sc) {
		TaskConverter taskConverter = TaskConverter.getInstance();
		int index = 1;
		while (sc.hasNext()) {
			String str = sc.nextLine();
			Task newTask = new Task(str, true);
			list.add(taskConverter.convert(newTask, index));
			index++;
		}
	}

	public ObservableList<ModelTask> getListFromFile() {
		return list;
	}

	// writes contents of observableList to text file
	public void save(ObservableList<ModelTask> list) throws IOException {

		FileWriter fileWriter = new FileWriter(inputFile, false);
		BufferedWriter buffer = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(buffer);
		for (int i = 0; i < list.size(); i++) {
			printWriter.println(list.get(i).toString());
		}

		printWriter.close();
		buffer.close();
		fileWriter.close();
	}

}
