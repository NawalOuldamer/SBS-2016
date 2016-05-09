/**
 * 
 */
package postprocess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * @author ould
 *
 */
public class UserTags {

	/**
	 * @param args
	 */
	static org.jdom2.Document document;
	static Element racine;
	public static void main(String[] args) throws IOException {
		FileWriter f_u = new FileWriter("user_tags.txt");
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File("./files/sbs16suggestion.topics.xml"));
		}
		catch(Exception e){}

		racine = document.getRootElement();
		Element  topic = racine.getChild("topic");
		Element catalogue = topic.getChild("catalogue");
		System.out.println(catalogue.getChildText("work").length());
		Element works = catalogue.getChild("work");
		List<Element> tags = works.getCh;
		
		Iterator<Element> i_s = subject.iterator();		
		while(i_s.hasNext())
		{
			Element courant = i_s.next();
			
		}

	}
	

}
