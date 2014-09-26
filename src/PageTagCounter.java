import java.util.List;

public class PageTagCounter {

	public static void main(String[] args) {
		
		String xmlFilePath = "test.xml";
		
		StaXParser read = new StaXParser();
	    List<Page> readPage = read.readPage(xmlFilePath);
	    int counter = 0;
	    for (Page page : readPage) {
	    	counter ++;
	    	System.out.println(counter + ". " + page);
	    }
	    System.out.println("done");
	}

}
