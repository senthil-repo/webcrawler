package domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by s.nathan on 09/07/2020.
 */
public class Website {
    private String url;

    public Website(String name) {
        this.url = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
