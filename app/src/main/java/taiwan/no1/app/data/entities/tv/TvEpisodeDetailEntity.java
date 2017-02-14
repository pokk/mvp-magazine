package taiwan.no1.app.data.entities.tv;

import java.util.List;

import taiwan.no1.app.data.entities.CommonEntity;
import taiwan.no1.app.data.entities.FilmCastsEntity;
import taiwan.no1.app.data.entities.FilmVideosEntity;
import taiwan.no1.app.data.entities.ImageProfileEntity;

/**
 * The episode detail from a season of tv information data class.
 *
 * @author weian
 * @since 2017/1/21
 */

public class TvEpisodeDetailEntity {
    private String air_date;
    private int episode_number;
    private String name;
    private String overview;
    private int id;
    private String production_code;
    private int season_number;
    private String still_path;
    private double vote_average;
    private int vote_count;
    private ImagesBean images;
    private CommonEntity.ResultsListEntity<FilmVideosEntity> videos;
    private FilmCastsEntity credits;
    private List<FilmCastsEntity.CrewBean> crew;
    private List<FilmCastsEntity.CastBean> guest_stars;

    //region Getter and Setter
    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduction_code() {
        return production_code;
    }

    public void setProduction_code(String production_code) {
        this.production_code = production_code;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public String getStill_path() {
        return still_path;
    }

    public void setStill_path(String still_path) {
        this.still_path = still_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public CommonEntity.ResultsListEntity<FilmVideosEntity> getVideos() {
        return videos;
    }

    public void setVideos(CommonEntity.ResultsListEntity<FilmVideosEntity> videos) {
        this.videos = videos;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public FilmCastsEntity getCredits() {
        return credits;
    }

    public void setCredits(FilmCastsEntity credits) {
        this.credits = credits;
    }

    public List<FilmCastsEntity.CrewBean> getCrew() {
        return crew;
    }

    public void setCrew(List<FilmCastsEntity.CrewBean> crew) {
        this.crew = crew;
    }

    public List<FilmCastsEntity.CastBean> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(List<FilmCastsEntity.CastBean> guest_stars) {
        this.guest_stars = guest_stars;
    }
    //endregion

    public static class ImagesBean {
        private List<ImageProfileEntity> stills;

        //region Getter and Setter
        public List<ImageProfileEntity> getStills() {
            return stills;
        }

        public void setStills(List<ImageProfileEntity> stills) {
            this.stills = stills;
        }
        //endregion

        @Override
        public String toString() {
            return "ImagesBean{" +
                    "stills=" + stills +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TvEpisodeDetailEntity{" +
                "air_date='" + air_date + '\'' +
                ", episode_number=" + episode_number +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", id=" + id +
                ", production_code='" + production_code + '\'' +
                ", season_number=" + season_number +
                ", still_path='" + still_path + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", images=" + images +
                ", videos=" + videos +
                ", credits=" + credits +
                ", crew=" + crew +
                ", guest_stars=" + guest_stars +
                '}';
    }
}
