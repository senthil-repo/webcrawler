package search;

import domain.Website;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class DownloadWebsiteTest {
    DownloadWebsite downloadWebsite= null;

    @Before
    public void setup() throws Exception{
        downloadWebsite = new DownloadWebsite();
    }

    @Test
    public void testCall() throws Exception{
        Website website = downloadWebsite.call();
        assertTrue(website.getJavaScriptLibraries().size() > 0);
    }
}
