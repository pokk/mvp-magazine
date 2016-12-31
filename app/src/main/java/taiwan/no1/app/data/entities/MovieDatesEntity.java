package taiwan.no1.app.data.entities;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/31/16
 */

public class MovieDatesEntity {
    private String maximum;
    private String minimum;

    public String getMaximum() { return maximum;}

    public void setMaximum(String maximum) { this.maximum = maximum;}

    public String getMinimum() { return minimum;}

    public void setMinimum(String minimum) { this.minimum = minimum;}

    @Override
    public String toString() {
        return "MovieDatesEntity{" +
                "maximum='" + maximum + '\'' +
                ", minimum='" + minimum + '\'' +
                '}';
    }
}
