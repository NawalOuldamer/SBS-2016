/**
 * 
 */
package postprocess;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * @author ould
 *
 */
public class Queries {

	/**
	 * @param args
	 */
	

	static org.jdom2.Document document;
	static Element racine;
	public static void main(String[] args) {
		
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File("./files/sbs16suggestion.topics.xml"));
		}
		catch(Exception e){}

		racine = document.getRootElement();
		Element  subjects = racine.getChild("topic");
		List<Element> subject = subjects.getChildren();
		Iterator<Element> i_s = subject.iterator();		
		while(i_s.hasNext())
		{
			Element courant = i_s.next();
		}

	}

}
