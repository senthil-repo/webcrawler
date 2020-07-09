package search;

import domain.Constants;
import domain.Website;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class SearchEngine {
    private Set<Website> websites = new HashSet<Website>();

    public Set<Website> getResult() {
        String url = "https://www.bing.com/search?q=corona";
        try {
            Connection connection = Jsoup.connect(url).userAgent(Constants.USER_AGENT);
            Document document = connection.get();
            Elements elements = document.select("a[href]");
            System.out.println(" Number of websites found " + elements.size());

            elements.forEach(element -> websites.add(new Website(element.absUrl("href"))));
        }catch(IOException io) {
            //TODO
        }
        return websites;
    }
}

