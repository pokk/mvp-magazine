package taiwan.no1.app.data.entities.tv;

/**
 * Brief season data of a tv information data class.
 *
 * @author jieyi
 * @since 2/13/17
 */

public class TvSeasonBriefEntity {
    private int id;
    private int episode_count;
    private int season_number;
    private String air_date;
    private String poster_path;

    //region Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    //endregion

    @Override
    public String toString() {
        return "TvSeasonBriefEntity{" +
                "id=" + id +
                ", episode_count=" + episode_count +
                ", season_number=" + season_number +
                ", air_date='" + air_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                '}';
    }
}
