package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

public class DetectLineBreak {
	public static void main(String[] args) {
		String s = "Test \n\nThis is a fucking test";
		Scanner scan = new Scanner(s);
		String fixSpacing = "";
		BufferedReader br = new BufferedReader(new StringReader(s));
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if(line.equals(""))
					System.out.println("BREAK");
				else
					System.out.println("NOPE");
				//System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		while(scan.hasNext()!= false) {
//			String line = scan.nextLine();
//			//System.out.println(line);
//			if(line.contains("\r\n|[\n\r\u2028\u2029\u0085]"))
//				System.out.println("BREAK");
//			else
//				System.out.println("NOPE");
//		}
	}
}
