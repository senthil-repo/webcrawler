package search;

import domain.Website;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class SearchEngineTest {
    SearchEngine searchEngine= null;

    @Before
    public void setup() {
        searchEngine = new SearchEngine();
    }

    @Test
    public void testGetResult() {
        Set<Website> websites = searchEngine.getResult();
        assertTrue(" Invalid result ", websites.size() > 0);
    }
}
