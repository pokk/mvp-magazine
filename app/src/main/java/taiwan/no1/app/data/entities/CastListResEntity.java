package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * A list of popular casts list data class.
 *
 * @author Jieyi
 * @since 2/2/17
 */

public class CastListResEntity extends ListResEntity {
    // A brief introduction of each of cast data class.
    private List<CastBriefEntity> results;

    //region Getter and Setter 
    public List<CastBriefEntity> getResults() { return results;}

    public void setResults(List<CastBriefEntity> results) { this.results = results;}
    //endregion

    @Override
    public String toString() {
        return "CastListResEntity{" +
                "results=" + results +
                '}';
    }
}
