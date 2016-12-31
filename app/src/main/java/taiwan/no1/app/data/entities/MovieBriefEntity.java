package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * @author Jieyi
 * @version 0.0.1
 * @since 12/31/16
 */

public class MovieBriefEntity {
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
    private List<Integer> genre_ids;

    public String getPoster_path() { return poster_path;}

    public void setPoster_path(String poster_path) { this.poster_path = poster_path;}

    public boolean isAdult() { return adult;}

    public void setAdult(boolean adult) { this.adult = adult;}

    public String getOverview() { return overview;}

    public void setOverview(String overview) { this.overview = overview;}

    public String getRelease_date() { return release_date;}

    public void setRelease_date(String release_date) { this.release_date = release_date;}

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public String getOriginal_title() { return original_title;}

    public void setOriginal_title(String original_title) { this.original_title = original_title;}

    public String getOriginal_language() { return original_language;}

    public void setOriginal_language(String original_language) { this.original_language = original_language;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public String getBackdrop_path() { return backdrop_path;}

    public void setBackdrop_path(String backdrop_path) { this.backdrop_path = backdrop_path;}

    public double getPopularity() { return popularity;}

    public void setPopularity(double popularity) { this.popularity = popularity;}

    public int getVote_count() { return vote_count;}

    public void setVote_count(int vote_count) { this.vote_count = vote_count;}

    public boolean isVideo() { return video;}

    public void setVideo(boolean video) { this.video = video;}

    public double getVote_average() { return vote_average;}

    public void setVote_average(double vote_average) { this.vote_average = vote_average;}

    public List<Integer> getGenre_ids() { return genre_ids;}

    public void setGenre_ids(List<Integer> genre_ids) { this.genre_ids = genre_ids;}

    @Override
    public String toString() {
        return "MovieEntity{" +
                "poster_path='" + poster_path + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", id=" + id +
                ", original_title='" + original_title + '\'' +
                ", original_language='" + original_language + '\'' +
                ", title='" + title + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", popularity=" + popularity +
                ", vote_count=" + vote_count +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", genre_ids=" + genre_ids +
                '}';
    }
}