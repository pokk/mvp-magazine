package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Popular movie list result from a json file.
 *
 * @author Jieyi
 * @version 0.0.1
 * @since 12/28/16
 */

public class MovieListResEntity {
    private int page;
    private int total_pages;
    private int total_results;
    private List<MovieBriefEntity> results;

    public int getPage() { return page;}

    public void setPage(int page) { this.page = page;}

    public int getTotal_results() { return total_results;}

    public void setTotal_results(int total_results) { this.total_results = total_results;}

    public int getTotal_pages() { return total_pages;}

    public void setTotal_pages(int total_pages) { this.total_pages = total_pages;}

    public List<MovieBriefEntity> getMovieEntities() { return results;}

    public void setResults(List<MovieBriefEntity> results) { this.results = results;}

    @Override
    public String toString() {
        return "PopularResEntity{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", results=" + results +
                '}';
    }
}
