package search;


import domain.Website;
import exception.WebsiteAccessException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import java.io.IOException;
import java.util.Set;

/**
 * Created by s.nathan on 09/07/2020.
 */
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchEngine.class, Jsoup.class, Connection.class, Document.class})
public class SearchEngineTest {
    SearchEngine searchEngine= null;

    @Mock
    Jsoup jsoup;
    @Mock
    Connection connection;
    @Mock
    Document document;

    @Before
    public void setup() {
        searchEngine = new SearchEngine();
        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(anyString())).thenReturn(connection);
        when(connection.userAgent(anyString())).thenReturn(connection);
    }

    @Test
    public void testGetResult() throws IOException{
        String searchURLWithTerm = "https://www.bing.com/search?q=corona";
        when(connection.get()).thenReturn(document);
        when(document.select(anyString())).thenReturn(getElements());

        Set<Website> websites = searchEngine.getResult(searchURLWithTerm);
        assertNotNull(websites);
        assertTrue(" Invalid result ", websites.size() > 0);
    }

    @Test(expected = WebsiteAccessException.class)
    public void testGetResult_Exception() throws IOException{
        String searchURLWithTerm = "https://www.bing.com/search?q=corona";
        when(connection.get()).thenThrow(new IOException());
        when(document.select(anyString())).thenReturn(getElements());
        Set<Website> websites = null;
        try {
            websites = searchEngine.getResult(searchURLWithTerm);
        }catch(WebsiteAccessException e) {
            assertNull(" Unexpected result ", websites);
            throw e;
        }
    }

    //TODO - Would like to add more cases in future. Due to time constraint, i couldn't add more now.
/*    @Test
    public void testGetResult_EmptyResult() throws IOException{

    }*/
    
    private Elements getElements() {
        Elements elements = new Elements();
        Element element1 = new Element(Tag.valueOf("Site1"), "https://www.nhs.uk");
        elements.add(element1);
        Element element2 = new Element(Tag.valueOf("Site2"), "https://www.gov.uk");
        elements.add(element2);
        return elements;
    }
}
