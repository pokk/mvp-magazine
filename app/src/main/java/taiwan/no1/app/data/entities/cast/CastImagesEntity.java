package taiwan.no1.app.data.entities.cast;

import java.util.List;

import taiwan.no1.app.data.entities.ImageProfileEntity;

/**
 * A movie cast's images data class.
 * 
 * @author Jieyi
 * @since 1/1/17
 */

public class CastImagesEntity {
    private List<ImageProfileEntity> profiles;

    //region Getter and Setter
    public List<ImageProfileEntity> getProfiles() { return profiles;}

    public void setProfiles(List<ImageProfileEntity> profiles) { this.profiles = profiles;}
    //endregion

    @Override
    public String toString() {
        return "CastImagesEntity{" +
                "profiles=" + profiles +
                '}';
    }
}
