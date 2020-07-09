package search;

import domain.Constants;
import domain.Website;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class DownloadWebsite {
    private Website website;

    public Website call() {
        Set<String> jsLibraries = null;
        try {
            //TODO - In future, the timeout settings needs to be changed and configured, controlled in a better way
            Connection connection = Jsoup.connect(website.getUrl()).timeout(10000).userAgent(Constants.USER_AGENT);
            Document document = connection.get();
            jsLibraries = Jsoup.parse(document.html())
                    .select("script")
                    .stream()
                    .map(element -> element.attr("src"))
                    .filter(src -> StringUtils.isNotBlank(src))
                    .map(s -> StringUtils.substringAfterLast(s, Constants.SLASH))
                    .collect(Collectors.toSet());
            website.setJavaScriptLibraries(jsLibraries);
        } catch(Exception e) {
            //TODO - This must be changed, implemented in future. At present, added this to ignore the following certification exceptions thrown by the websites
            //SSLHandshakeException, SunCertPathBuilderException, ValidatorException
            //TODO - Need to fix this by adding the certifcates in the classpath
            //TODO - Also, need to handle gracefully any unexpected exceptions from the websites
        }
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
