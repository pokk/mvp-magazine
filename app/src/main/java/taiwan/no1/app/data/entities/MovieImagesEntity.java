package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/31/16
 */

public class MovieImagesEntity {
    private List<ImageInfoEntity> backdrops;
    private List<ImageInfoEntity> posters;

    public List<ImageInfoEntity> getBackdrops() { return backdrops;}

    public void setBackdrops(List<ImageInfoEntity> backdrops) { this.backdrops = backdrops;}

    public List<ImageInfoEntity> getPosters() { return posters;}

    public void setPosters(List<ImageInfoEntity> posters) { this.posters = posters;}

    @Override
    public String toString() {
        return "MovieImageEntity{" +
                "backdrops=" + backdrops +
                ", posters=" + posters +
                '}';
    }
}
