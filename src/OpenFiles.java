import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class OpenFiles {

private Scanner read;

	
	public void createFile(){
		File file = new File("Data.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
	public static ArrayList readFromFile(String fileName){
		ArrayList<String> myList = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new File(fileName));
			while(sc.hasNextLine()){
				myList.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return myList;
	}
	public static void saveToFile(String fileName, ArrayList list){
		Path filePath = Paths.get(fileName);
		try {
			Files.write(filePath, list, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String format(ArrayList lis){
		ArrayList<String> words = readFromFile("Data.txt");
		String output = "";
		for(int i = 0; i<words.size();i++){
		    if(i != 0 && i%1==0){
		        output+="\n";
		        output+=words.get(i);
		}
		    else
		        output+=words.get(i);
		}
		return output;
	}
	public void closeFile(){
		read.close();
	}
	
	
}

