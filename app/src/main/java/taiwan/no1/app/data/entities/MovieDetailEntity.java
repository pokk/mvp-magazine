package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * The movie detail information.
 *
 * @author Jieyi
 * @since 12/29/16
 */

public class MovieDetailEntity {
    private boolean adult;
    private String backdrop_path;
    private BelongsToCollectionBean belongs_to_collection;
    private int budget;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    private CommonEntity.ResultsListEntity<FilmVideosEntity> videos;
    private FilmImagesEntity images;
    private SearchListResEntity<MovieBriefEntity> similar;
    private FilmCastsEntity casts;
    private List<CommonEntity.BaseBean> genres;
    private List<CommonEntity.BaseBean> production_companies;
    private List<CommonEntity.CountriesBean> production_countries;
    private List<CommonEntity.LanguagesBean> spoken_languages;

    //region Getter and Setter
    public boolean isAdult() { return adult;}

    public void setAdult(boolean adult) { this.adult = adult;}

    public String getBackdrop_path() { return backdrop_path;}

    public void setBackdrop_path(String backdrop_path) { this.backdrop_path = backdrop_path;}

    public BelongsToCollectionBean getBelongs_to_collection() { return belongs_to_collection;}

    public void setBelongs_to_collection(
            BelongsToCollectionBean belongs_to_collection) { this.belongs_to_collection = belongs_to_collection;}

    public int getBudget() { return budget;}

    public void setBudget(int budget) { this.budget = budget;}

    public String getHomepage() { return homepage;}

    public void setHomepage(String homepage) { this.homepage = homepage;}

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public String getImdb_id() { return imdb_id;}

    public void setImdb_id(String imdb_id) { this.imdb_id = imdb_id;}

    public String getOriginal_language() { return original_language;}

    public void setOriginal_language(String original_language) { this.original_language = original_language;}

    public String getOriginal_title() { return original_title;}

    public void setOriginal_title(String original_title) { this.original_title = original_title;}

    public String getOverview() { return overview;}

    public void setOverview(String overview) { this.overview = overview;}

    public double getPopularity() { return popularity;}

    public void setPopularity(double popularity) { this.popularity = popularity;}

    public String getPoster_path() { return poster_path;}

    public void setPoster_path(String poster_path) { this.poster_path = poster_path;}

    public String getRelease_date() { return release_date;}

    public void setRelease_date(String release_date) { this.release_date = release_date;}

    public int getRevenue() { return revenue;}

    public void setRevenue(int revenue) { this.revenue = revenue;}

    public int getRuntime() { return runtime;}

    public void setRuntime(int runtime) { this.runtime = runtime;}

    public String getStatus() { return status;}

    public void setStatus(String status) { this.status = status;}

    public String getTagline() { return tagline;}

    public void setTagline(String tagline) { this.tagline = tagline;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public boolean isVideo() { return video;}

    public void setVideo(boolean video) { this.video = video;}

    public double getVote_average() { return vote_average;}

    public void setVote_average(double vote_average) { this.vote_average = vote_average;}

    public int getVote_count() { return vote_count;}

    public void setVote_count(int vote_count) { this.vote_count = vote_count;}

    public CommonEntity.ResultsListEntity<FilmVideosEntity> getVideos() {
        return this.videos;
    }

    public void setVideos(CommonEntity.ResultsListEntity<FilmVideosEntity> videos) {
        this.videos = videos;
    }

    public FilmImagesEntity getImages() {
        return this.images;
    }

    public void setImages(FilmImagesEntity images) {
        this.images = images;
    }

    public SearchListResEntity<MovieBriefEntity> getSimilar() {
        return this.similar;
    }

    public void setSimilar(SearchListResEntity<MovieBriefEntity> similar) {
        this.similar = similar;
    }

    public FilmCastsEntity getCasts() {
        return this.casts;
    }

    public void setCasts(FilmCastsEntity casts) {
        this.casts = casts;
    }

    public List<CommonEntity.BaseBean> getGenres() {
        return genres;
    }

    public void setGenres(List<CommonEntity.BaseBean> genres) {
        this.genres = genres;
    }

    public List<CommonEntity.BaseBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<CommonEntity.BaseBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<CommonEntity.CountriesBean> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<CommonEntity.CountriesBean> production_countries) {
        this.production_countries = production_countries;
    }

    public List<CommonEntity.LanguagesBean> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<CommonEntity.LanguagesBean> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }
    //endregion

    public static class BelongsToCollectionBean {
        private int id;
        private String name;
        private String poster_path;
        private String backdrop_path;

        //region Getter and Setter
        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getPoster_path() { return poster_path;}

        public void setPoster_path(String poster_path) { this.poster_path = poster_path;}

        public String getBackdrop_path() { return backdrop_path;}

        public void setBackdrop_path(String backdrop_path) { this.backdrop_path = backdrop_path;}
        //endregion

        @Override
        public String toString() {
            return "BelongsToCollectionBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", poster_path='" + poster_path + '\'' +
                    ", backdrop_path='" + backdrop_path + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MovieDetailEntity{" +
                "adult=" + adult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", belongs_to_collection=" + belongs_to_collection +
                ", budget=" + budget +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdb_id='" + imdb_id + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", videos=" + videos +
                ", images=" + images +
                ", similar=" + similar +
                ", casts=" + casts +
                ", genres=" + genres +
                ", production_companies=" + production_companies +
                ", production_countries=" + production_countries +
                ", spoken_languages=" + spoken_languages +
                '}';
    }
}
