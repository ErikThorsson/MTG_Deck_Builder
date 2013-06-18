package automatedDatabase;
import java.io.*;
import java.util.Scanner;

public class replaceWord {
	public static void main(String args[]) throws FileNotFoundException {
	Scanner scan = new Scanner(new File("/Users/eorndahl/Desktop/fixME.txt")); 

	String in = "";
	while(scan.hasNextLine()) in += scan.nextLine() + "\n";

	String[] lines = in.toString().split("::");
	for(int i = 0; i < lines.length; i++) {
	lines[i] = lines[i] + "::";
	if(lines[i].equals("black::"))
		lines[i] = "blue::";
	else if(lines[i].equals("blue::"))
		lines[i] = "black::";
	}
	in = "";
	
	for(int i = 0; i < lines.length; i++)
	in += lines[i];
	
	PrintWriter write = new PrintWriter(new File("/Users/eorndahl/Desktop/fixed.txt"));
	write.print(in);
	write.flush();
	write.close();
}
}
