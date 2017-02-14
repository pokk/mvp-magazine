package taiwan.no1.app.data.entities.movie;

/**
 * Movie's date data class.
 * 
 * @author Jieyi
 * @since 12/31/16
 */

public class MovieDatesEntity {
    private String maximum;
    private String minimum;

    //region Getter and Setter
    public String getMaximum() { return maximum;}

    public void setMaximum(String maximum) { this.maximum = maximum;}

    public String getMinimum() { return minimum;}

    public void setMinimum(String minimum) { this.minimum = minimum;}
    //endregion

    @Override
    public String toString() {
        return "MovieDatesEntity{" +
                "maximum='" + maximum + '\'' +
                ", minimum='" + minimum + '\'' +
                '}';
    }
}
