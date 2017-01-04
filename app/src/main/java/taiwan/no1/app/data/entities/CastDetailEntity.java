package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Movie cast detail information.
 * 
 * @author Jieyi
 * @version 0.0.1
 * @since 12/29/16
 */

public class CastDetailEntity {
    private boolean adult;
    private String biography;
    private String birthday;
    private String deathday;
    private int gender;
    private String homepage;
    private int id;
    private String imdb_id;
    private String name;
    private String place_of_birth;
    private double popularity;
    private String profile_path;
    private List<String> also_known_as;
    private CastImagesEntity images;
    private CreditsEntity combined_credits;

    public boolean isAdult() { return adult;}

    public void setAdult(boolean adult) { this.adult = adult;}

    public String getBiography() { return biography;}

    public void setBiography(String biography) { this.biography = biography;}

    public String getBirthday() { return birthday;}

    public void setBirthday(String birthday) { this.birthday = birthday;}

    public String getDeathday() { return deathday;}

    public void setDeathday(String deathday) { this.deathday = deathday;}

    public int getGender() { return gender;}

    public void setGender(int gender) { this.gender = gender;}

    public String getHomepage() { return homepage;}

    public void setHomepage(String homepage) { this.homepage = homepage;}

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public String getImdb_id() { return imdb_id;}

    public void setImdb_id(String imdb_id) { this.imdb_id = imdb_id;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getPlace_of_birth() { return place_of_birth;}

    public void setPlace_of_birth(String place_of_birth) { this.place_of_birth = place_of_birth;}

    public double getPopularity() { return popularity;}

    public void setPopularity(double popularity) { this.popularity = popularity;}

    public String getProfile_path() { return profile_path;}

    public void setProfile_path(String profile_path) { this.profile_path = profile_path;}

    public List<String> getAlso_known_as() { return also_known_as;}

    public void setAlso_known_as(List<String> also_known_as) { this.also_known_as = also_known_as;}

    public CastImagesEntity getImages() {
        return images;
    }

    public void setImages(CastImagesEntity images) {
        this.images = images;
    }

    public CreditsEntity getCombined_credits() {
        return combined_credits;
    }

    public void setCombined_credits(CreditsEntity combined_credits) {
        this.combined_credits = combined_credits;
    }

    @Override
    public String toString() {
        return "CastDetailEntity{" +
                "adult=" + adult +
                ", biography='" + biography + '\'' +
                ", birthday='" + birthday + '\'' +
                ", deathday='" + deathday + '\'' +
                ", gender=" + gender +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdb_id='" + imdb_id + '\'' +
                ", name='" + name + '\'' +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", popularity=" + popularity +
                ", profile_path='" + profile_path + '\'' +
                ", also_known_as=" + also_known_as +
                ", images=" + images +
                ", combined_credits=" + combined_credits +
                '}';
    }
}
