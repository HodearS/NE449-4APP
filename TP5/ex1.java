
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ex1 {

	public static void main (String[] args) throws IOException {
		
		
		if(args.length != 2){
			System.out.println("Usage: <filename> <new filename>");
			System.exit(1);;
		}
		
		String fileName = args[0];
		String newFileName =args[1];
		
		FileInputStream file = new FileInputStream(fileName);
		FileOutputStream newFile = new FileOutputStream(newFileName);
		

		for (int buffer = file.read(); buffer!=-1; buffer = file.read()) {
			
			newFile.write(buffer);
		}
		
		file.close();
		newFile.close();
			
		
	}
	
}
