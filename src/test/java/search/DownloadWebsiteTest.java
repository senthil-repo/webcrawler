package search;

import domain.Website;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
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

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by s.nathan on 09/07/2020.
 */
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchEngine.class, Jsoup.class, Connection.class, Document.class})
public class DownloadWebsiteTest {
    DownloadWebsite downloadWebsite= null;
    @Mock
    Jsoup jsoup;
    @Mock
    Connection connection;
    @Mock
    Document document;

    @Before
    public void setup() throws Exception{
        downloadWebsite = new DownloadWebsite();
        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(anyString())).thenReturn(connection);
        when(connection.timeout(anyInt())).thenReturn(connection);
        when(connection.userAgent(anyString())).thenReturn(connection);
    }

    @Test
    public void testCall() throws IOException{
        when(connection.get()).thenReturn(document);
        when(Jsoup.parse(anyString())).thenReturn(document);
        when(document.html()).thenReturn("DUMMY");
        when(document.select(anyString())).thenReturn(getElements());
        downloadWebsite.setWebsite(getWebsite());
        Website website = downloadWebsite.call();
        assertNotNull(website);
        assertTrue(" Invalid result ", website.getUrl().equalsIgnoreCase("https://www.nhs.uk"));
        assertTrue(" Wrong result ", website.getJavaScriptLibraries().size() > 0);
    }

    @Test
    public void testCall_NoResult() throws IOException{
        when(connection.get()).thenReturn(document);
        when(Jsoup.parse(anyString())).thenReturn(document);
        downloadWebsite.setWebsite(getWebsite());
        //Didn't set some mock values, which will force the call to fail
        Website website = downloadWebsite.call();
        assertTrue(" Invalid result ", website.getUrl().equalsIgnoreCase("https://www.nhs.uk"));
        assertTrue(" Wrong result ", website.getJavaScriptLibraries().size() == 0);
    }

    //TODO - Would like to add more cases in future. Due to time constraint, i couldn't add more now.
/*    @Test
    public void testCall_Exception() {

    }*/

    private Website getWebsite() {
        Website website = new Website("https://www.nhs.uk");
        return website;
    }

    private Elements getElements() {
        Elements elements = new Elements();
        Attributes attributes = new Attributes();
        attributes.add("src", "jquery.js");
        Element element1 = new Element(Tag.valueOf("Site1"), "https://www.nhs.uk", attributes);
        elements.add(element1);
        return elements;
    }
}
