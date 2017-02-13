package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * A brief {@link TvBriefEntity} or {@link MovieBriefEntity} or {@link CastBriefEntity} information from the searching
 * list data class.
 *
 * @author jieyi
 * @since 2017/02/13
 */

public class SearchListResEntity<L> extends ListResEntity {
    private List<L> results;

    //region Getter and Setter
    public List<L> getResults() {
        return results;
    }

    public void setResults(List<L> results) { this.results = results;}
    //endregion

    @Override
    public String toString() {
        return "SearchListResEntity{" + "results=" + results + '}';
    }
}
