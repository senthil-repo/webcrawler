package search;

import domain.Website;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertTrue;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class ExtractLibrariesTest {
    ExtractLibraries extractLibraries = null;

    @Before
    public void setup() throws Exception{
        extractLibraries= new ExtractLibraries();
    }

    @Test
    public void testCall() throws Exception{
        Set<String> jsLibraries = extractLibraries.getJSLibraries(getWebsites());
        assertTrue(" Unexpected size ", jsLibraries.size() > 0);
    }

    private Set<Website> getWebsites() {
        Set<Website> websites = new HashSet<>();
        websites.add(new Website("https://www.nhs.uk"));
        websites.add(new Website("https://www.gov.uk"));
        return websites;
    }
}
