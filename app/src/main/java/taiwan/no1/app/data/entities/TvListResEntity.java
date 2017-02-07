package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * @author Jieyi
 * @since 2/4/17
 */

public class TvListResEntity extends ListResEntity {
    private List<TvBriefEntity> results;

    public List<TvBriefEntity> getResults() { return results;}

    public void setResults(List<TvBriefEntity> results) { this.results = results;}
}
