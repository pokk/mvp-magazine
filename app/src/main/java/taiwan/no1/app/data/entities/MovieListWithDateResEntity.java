package taiwan.no1.app.data.entities;

/**
 * @author Jieyi
 * @since 12/31/16
 */

public class MovieListWithDateResEntity extends MovieListResEntity {
    private MovieDatesEntity dates;

    //region Getter and Setter
    public MovieDatesEntity getDates() { return dates;}

    public void setDates(MovieDatesEntity dates) { this.dates = dates;}
    //endregion

    @Override
    public String toString() {
        return "MovieListWithDateResEntity{" +
                "dates=" + dates +
                '}';
    }
}
