package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * The tv detail information.
 * @author weian
 * @since 2017/1/11
 */

public class TvDetailEntity {
    private String backdrop_path;
    private String first_air_date;
    private String homepage;
    private int id;
    private boolean in_production;
    private String last_air_date;
    private String name;
    private int number_of_episodes;
    private int number_of_seasons;
    private String original_language;
    private String original_name;
    private String overview;
    private double popularity;
    private String poster_path;
    private String status;
    private String type;
    private double vote_average;
    private int vote_count;
    private CommonEntity.ResultsListEntity<FilmVideosEntity> videos;
    private FilmImagesEntity images;
    private SearchListResEntity<MovieBriefEntity> similar;
    private FilmCastsEntity credits;
    private List<CreatedByBean> created_by;
    private List<Integer> episode_run_time;
    private List<CommonEntity.BaseBean> genres;
    private List<String> languages;
    private List<CommonEntity.BaseBean> networks;
    private List<String> origin_country;
    private List<CommonEntity.BaseBean> production_companies;
    private List<TvSeasonBriefEntity> seasons;

    //region Getter and Setter
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public FilmImagesEntity getImages() {
        return images;
    }

    public void setImages(FilmImagesEntity images) {
        this.images = images;
    }

    public SearchListResEntity<MovieBriefEntity> getSimilar() {
        return similar;
    }

    public void setSimilar(SearchListResEntity<MovieBriefEntity> similar) {
        this.similar = similar;
    }

    public FilmCastsEntity getCredits() {
        return credits;
    }

    public void setCredits(FilmCastsEntity credits) {
        this.credits = credits;
    }

    public List<CreatedByBean> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<CreatedByBean> created_by) {
        this.created_by = created_by;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public List<CommonEntity.BaseBean> getGenres() {
        return genres;
    }

    public void setGenres(List<CommonEntity.BaseBean> genres) {
        this.genres = genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<CommonEntity.BaseBean> getNetworks() {
        return networks;
    }

    public void setNetworks(List<CommonEntity.BaseBean> networks) {
        this.networks = networks;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<CommonEntity.BaseBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<CommonEntity.BaseBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<TvSeasonBriefEntity> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<TvSeasonBriefEntity> seasons) {
        this.seasons = seasons;
    }
    //endregion

    public static class CreatedByBean {
        private int id;
        private String name;
        private String profile_path;

        //region Getter and Setter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
        //endregion

        @Override
        public String toString() {
            return "CreatedByBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", profile_path='" + profile_path + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TVDetailEntity{" +
                "backdrop_path='" + backdrop_path + '\'' +
                ", first_air_date='" + first_air_date + '\'' +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", in_production=" + in_production +
                ", last_air_date='" + last_air_date + '\'' +
                ", name='" + name + '\'' +
                ", number_of_episodes=" + number_of_episodes +
                ", number_of_seasons=" + number_of_seasons +
                ", original_language='" + original_language + '\'' +
                ", original_name='" + original_name + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + poster_path + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", videos=" + videos +
                ", images=" + images +
                ", similar=" + similar +
                ", credits=" + credits +
                ", created_by=" + created_by +
                ", episode_run_time=" + episode_run_time +
                ", genres=" + genres +
                ", languages=" + languages +
                ", networks=" + networks +
                ", origin_country=" + origin_country +
                ", production_companies=" + production_companies +
                ", seasons=" + seasons +
                '}';
    }
}
