package taiwan.no1.app.data.entities;

import java.util.List;

import taiwan.no1.app.data.entities.cast.CastBriefEntity;
import taiwan.no1.app.data.entities.movie.MovieBriefEntity;
import taiwan.no1.app.data.entities.tv.TvBriefEntity;

/**
 * A brief {@link TvBriefEntity} or {@link MovieBriefEntity} or {@link CastBriefEntity} information from the searching
 * list data class.
 *
 * @author jieyi
 * @since 2017/02/13
 */

public class ListResEntity<L> extends BaseListResEntity {
    private List<L> results;

    //region Getter and Setter
    public List<L> getResults() {
        return results;
    }

    public void setResults(List<L> results) { this.results = results;}
    //endregion

    @Override
    public String toString() {
        return "SearchListResEntity{" + super.toString() + "results=" + results + '}';
    }
}
