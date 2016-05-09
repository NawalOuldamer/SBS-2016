package postprocess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Document {
	/**
	 * cette classe permet de contruire un nouveau document par extension en utilisant word2vec, 
	 * l'extension est basée sur le titre, les tags et les sujets du documents 
	 * @param args
	 * @throws IOException 
	 */


	public static FileWriter file_rating ;	
	public static void main(String[] args) throws IOException {

		file_rating = new FileWriter(args[0]);
		file_rating.write("id_doc rating totalvotes helpfulvotes \n");

		Scanner sc = new Scanner(new File(args[1]));
		while (sc.hasNextLine()) {
			String line = (String) sc.nextLine();
			String [] s = new File("/home/data/collection/inex/2015/DOWNLOADED/xml/"+line+"/").list(); 
			for (int i=0; i<s.length;i++) 
			{ 
				getDocumentInformation(s[i],line); 
			} 
		}
		sc.close();
		

		file_rating.close();
	}

	static org.jdom2.Document document;
	static Element racine;



	/**	 * 
	 * @param xml_doc le document xml a parser 
	 * @return list of terms du document (title, tags ainsi subject)
	 * @throws IOException 
	 */

	public static ArrayList<String> getDocumentInformation(String xml_doc, String line) throws IOException{		
		FileWriter f_rev = new FileWriter("./documents_reviews/"+xml_doc+".txt");	
		FileWriter f_tag = new FileWriter("./documents_tag_title_subject/"+xml_doc+".txt");	
		ArrayList<String> liste_terms = new ArrayList<String>();
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File("/home/data/collection/inex/2015/DOWNLOADED/xml/"+line+"/"+xml_doc));
		}
		catch(Exception e){}

		racine = document.getRootElement();
		// title
		liste_terms.add(racine.getChild("title").getText());
		// subjects
		Element  subjects = racine.getChild("subjects");
		List<Element> subject = subjects.getChildren();
		Iterator<Element> i_s = subject.iterator();		
		while(i_s.hasNext())
		{
			Element courant = i_s.next();
			liste_terms.add(courant.getText());
		}

		// tags 
		Element  tags = racine.getChild("tags");
		List<Element> tag = tags.getChildren();
		Iterator<Element> i_t = tag.iterator();		
		while(i_t.hasNext())
		{
			Element courant = i_t.next();
			liste_terms.add(courant.getText());
		}

		// users reviews			
		Element  reviews = racine.getChild("reviews");
		List<Element> review = reviews.getChildren();
		Iterator<Element> i_r = review.iterator();		
		while(i_r.hasNext())
		{
			Element courant = i_r.next();
			f_rev.write(courant.getChildText("summary")+ " "+ courant.getChildText("content") + " ");
			file_rating.write(xml_doc+" "+
					courant.getChildText("rating")+" "+ courant.getChildText("totalvotes")+" "+courant.getChildText("helpfulvotes")+"\n");;
		}

		/// editoral reviews		
		Element  edreviews = racine.getChild("editorialreviews");
		List<Element> edreview = edreviews.getChildren();
		Iterator<Element> ed_r = edreview.iterator();		
		while(ed_r.hasNext())
		{
			Element courant = ed_r.next();
			f_rev.write(courant.getChildText("content")+ " ");		
		}

		// sauvegarde document tags titile subject
		for (int i = 0; i < liste_terms.size(); i++) {
			f_tag.write(liste_terms.get(i)+" ");
		}


		f_tag.close();
		f_rev.close();
		return liste_terms;	

	}

	public static <T> List<T> removeDuplicates(List<T> list) {
		return list.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	}


	/**
	 * 
	 * @param liste_terms liste de term pour lequelles nous allons faire extension avec wordtovec
	 */
	public static void getTermExtended(ArrayList<String> liste_terms){

	}

}
