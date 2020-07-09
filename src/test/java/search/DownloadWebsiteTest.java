package search;

import domain.Website;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by s.nathan on 09/07/2020.
 */
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
    }

    @Test
    public void testCall() throws Exception{
        downloadWebsite.setWebsite(getWebsite());
        Website website = downloadWebsite.call();
        assertNotNull(website);
        assertTrue(" Invalid result ", website.getUrl().equalsIgnoreCase("https://www.nhs.uk"));
        assertTrue(" Wrong result ", website.getJavaScriptLibraries().size() > 0);
    }

    private Website getWebsite() {
        Website website = new Website("https://www.nhs.uk");
        return website;
    }
}
