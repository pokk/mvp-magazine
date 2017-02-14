package taiwan.no1.app.data.entities.movie;


import taiwan.no1.app.data.entities.ListResEntity;

/**
 * A brief {@link MovieBriefEntity} information with extra date info from the searching list data class.
 *
 * @author Jieyi
 * @see ListResEntity
 * @since 12/31/16
 */

public class MovieListWithDateResEntity extends ListResEntity<MovieBriefEntity> {
    private MovieDatesEntity dates;

    //region Getter and Setter
    public MovieDatesEntity getDates() { return dates;}

    public void setDates(MovieDatesEntity dates) { this.dates = dates;}
    //endregion

    @Override
    public String toString() {
        return "MovieListWithDateResEntity{" +
                super.toString() +
                "dates=" + dates +
                '}';
    }
}
