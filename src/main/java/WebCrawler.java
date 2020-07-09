import domain.Website;
import search.ExtractLibraries;
import search.SearchEngine;

import java.util.Scanner;
import java.util.Set;

/**
 * Main class that runs the whole program.
 * Created by s.nathan on 09/07/2020.
 */
public class WebCrawler {
    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" Please enter the search term: ");
        String searchTerm = scanner.nextLine();
        String searchEngineUrl = "https://www.bing.com/search"; //TODO - Ideally this should be passed as an input ??

        //get search results
        Set<Website> websites = getResultFromSearchEngine(searchEngineUrl, searchTerm);

        //get js libraries
        Set<String> mostUsedLibraries = getMostUsedLibraries(websites);

        //print the most used js libraries to the console
        System.out.println( " Top "+mostUsedLibraries.size() + " most used libraries in the websites are :");
        System.out.println(" %%%%%%%%%%%%%%%%%%%%%%%%%%% ");
        mostUsedLibraries.forEach(System.out::println);
        System.out.println(" %%%%%%%%%%%%%%%%%%%%%%%%%%% ");
    }

    private Set<Website> getResultFromSearchEngine(String searchEngineUrl, String searchTerm) {
        SearchEngine searchEngine = new SearchEngine();

        StringBuilder url = new StringBuilder(searchEngineUrl).append("?q=").append(searchTerm);
        return searchEngine.getResult(url.toString());
    }

    private Set<String> getMostUsedLibraries(Set<Website> websites) {
        ExtractLibraries extractLibraries = new ExtractLibraries();
        return extractLibraries.getJSLibraries(websites);
    }
}
