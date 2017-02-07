package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Popular movie list result from a json file.
 *
 * @author Jieyi
 * @since 12/28/16
 */

public class MovieListResEntity extends ListResEntity {
    private List<MovieBriefEntity> results;

    public List<MovieBriefEntity> getMovieEntities() { return results;}

    public void setResults(List<MovieBriefEntity> results) { this.results = results;}

    @Override
    public String toString() {
        return "MovieListResEntity{" +
                "results=" + results +
                '}';
    }
}
