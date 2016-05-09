/**
 * 
 */
package postprocess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ould
 *
 */
public class Profiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		getStatsProfile();
	}
	
	
	
	
	public static void getStatsProfile(){
		try (BufferedReader br = new BufferedReader(new FileReader("./files/profiles")))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {

			 
				String [] vect = sCurrentLine.split("\t");
				System.out.println(vect.length);
				if(vect.length==7)
					System.out.println(vect[7]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
