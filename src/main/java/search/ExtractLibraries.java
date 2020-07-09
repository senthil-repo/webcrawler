package search;

import domain.Constants;
import domain.Website;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class ExtractLibraries {
    private final int threadPoolSize = 10;

    public Set<String> getJSLibraries(Set<Website> websites) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        List<DownloadWebsite> websiteLibraries = new ArrayList<>();
        List<Future<Website>> futures = null;

        websites.forEach(website -> {
            DownloadWebsite websiteLibrary = new DownloadWebsite();
            websiteLibrary.setWebsite(website);
            websiteLibraries.add(websiteLibrary);
        });

        try {
            futures = executorService.invokeAll(websiteLibraries);
            //get results
            return getResult(futures);
        } catch (InterruptedException e) {
            //TODO - This needs to be refactored in future to handle specifically, gracefully
            throw new RuntimeException(" Unexpected error happened, please try again ");
        } finally {
            executorService.shutdown();
        }
    }

    private Set<String> getResult(List<Future<Website>> futures) {
        final List<String> allJSLibraries = new ArrayList<>();

        for(Future<Website> future :  futures) {
            try {
                allJSLibraries.addAll(future.get().getJavaScriptLibraries());
            } catch (InterruptedException | ExecutionException e) { //TODO - This needs to be refactored in future to handle specifically, meaningfully
                throw new RuntimeException(" Unexpected error happened, please try again ");
            }
        }
        return getMostUsedLibraries(allJSLibraries);
    }

    /**
     * Group by the list based on number of repeated occurances of the elements (js string)
     * Sort the result in descending order and then pick the top 5
     * @param allJSLibraries
     * @return
     */
    private Set<String> getMostUsedLibraries(List<String> allJSLibraries) {
        Set<String> mostUsedLibraries = new HashSet<>();
        Map<String, Long> result = allJSLibraries.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(Constants.MOST_USED_LIBRARY_LIST_COUNT)
                .forEachOrdered(entry -> mostUsedLibraries.add(entry.getKey()));
        return mostUsedLibraries;
    }
}
