package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class Website {
    private String url;
    private Set<String> javaScriptLibraries = new HashSet<>();

    public Website(String name) {
        this.url = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<String> getJavaScriptLibraries() {
        return javaScriptLibraries;
    }

    public void setJavaScriptLibraries(Set<String> javaScriptLibraries) {
        this.javaScriptLibraries = javaScriptLibraries;
    }
}
