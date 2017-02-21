package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * A movie's or tv's backdrops and posters list data class.
 *
 * @author Jieyi
 * @since 12/31/16
 */

public class FilmImagesEntity {
    private List<ImageProfileEntity> backdrops;
    private List<ImageProfileEntity> posters;

    //region Getter and Setter
    public List<ImageProfileEntity> getBackdrops() { return backdrops;}

    public void setBackdrops(List<ImageProfileEntity> backdrops) { this.backdrops = backdrops;}

    public List<ImageProfileEntity> getPosters() { return posters;}

    public void setPosters(List<ImageProfileEntity> posters) { this.posters = posters;}
    //endregion

    @Override
    public String toString() {
        return "FilmImagesEntity{" +
                "backdrops=" + backdrops +
                ", posters=" + posters +
                '}';
    }
}
