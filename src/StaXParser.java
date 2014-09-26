import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaXParser {
  static final String PAGE = "page";
  static final String TITLE = "title";

  public List<Page> readPage(String pageFile) {
    List<Page> pages = new ArrayList<Page>();
    try {
      // First, create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = new FileInputStream(pageFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // read the XML document
      Page page = null;

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          // If we have an item element, we create a new item
          if (startElement.getName().getLocalPart() == (PAGE)) {
            page = new Page();
          }

          if (event.isStartElement()) {
            if (event.asStartElement().getName().getLocalPart()
                .equals(TITLE)) {
              event = eventReader.nextEvent();
              page.setTitle(event.asCharacters().getData());
              continue;
            }
          }
        }
        // If we reach the end of an item element, we add it to the list
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart() == (PAGE)) {
            pages.add(page);
          }
        }

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    return pages;
  }

} 