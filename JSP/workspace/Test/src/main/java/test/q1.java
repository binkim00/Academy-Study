package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class q1 {

	public static void main(String[] args) {
		
		String url = "http://quotes.toscrape.com/";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Elements elements = doc.select(".quote");
		
		for(Element element : elements) {
			String quote = element.getElementsByClass("text").text();
			String author = element.getElementsByClass("author").text();
			
			System.out.println("격언 : " + quote);
			System.out.println("작가 : " + author);
			System.out.println("---------------------------------");
		}
		
	}
	
}
