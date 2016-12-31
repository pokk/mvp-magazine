package taiwan.no1.app.data.entities;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/31/16
 */
// TODO: 12/31/16  
public class MovieListWithDateResEntity extends MovieListResEntity {
    private MovieDatesEntity dates;

    public MovieDatesEntity getDates() { return dates;}

    public void setDates(MovieDatesEntity dates) { this.dates = dates;}

    @Override
    public String toString() {
        return "MovieListWithDateResEntity{" +
                "dates=" + dates +
                '}';
    }
}
