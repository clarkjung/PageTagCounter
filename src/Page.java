
public class Page {

  private String title;
  
  public String getTitle(){
	  return title;
  }
  
  public void setTitle(String title) {
	  this.title = title;
  }

  @Override
  public String toString() {
    return "Page [title=" + title + "]";
  }
} 