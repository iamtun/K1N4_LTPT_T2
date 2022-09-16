package ex1.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileHelper {
	public static void export(String filePath, String json) {
		try(PrintWriter out = new PrintWriter(new FileOutputStream(filePath))){
			out.print(json);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
