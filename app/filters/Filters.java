package filters;

import play.http.HttpFilters;
import play.mvc.EssentialFilter;

import javax.inject.Inject;
import java.util.List;

import static java.util.Arrays.asList;

public class Filters implements HttpFilters {

    private final RequestLoggingFilter logFilter;

    @Inject
    public Filters(RequestLoggingFilter logFilter) {
        this.logFilter = logFilter;
    }

    @Override
    public List<EssentialFilter> getFilters() {
        return asList(logFilter);
    }
}