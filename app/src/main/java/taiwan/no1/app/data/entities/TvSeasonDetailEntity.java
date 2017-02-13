package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Detail season data of a tv information data class inherit {@link TvSeasonBriefEntity}.
 *
 * @author weian
 * @since 2017/1/21
 */

public class TvSeasonDetailEntity extends TvSeasonBriefEntity {
    private String _id;
    private String name;
    private String overview;
    private test.ImagesBean images;
    private test.VideosBean videos;
    private test.CreditsBean credits;
    private List<TvEpisodeDetailEntity> episodes;

    //region Getter and Setter
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public test.ImagesBean getImages() {
        return images;
    }

    public void setImages(test.ImagesBean images) {
        this.images = images;
    }

    public test.VideosBean getVideos() {
        return videos;
    }

    public void setVideos(test.VideosBean videos) {
        this.videos = videos;
    }

    public test.CreditsBean getCredits() {
        return credits;
    }

    public void setCredits(test.CreditsBean credits) {
        this.credits = credits;
    }

    public List<TvEpisodeDetailEntity> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<TvEpisodeDetailEntity> episodes) {
        this.episodes = episodes;
    }
    //endregion

    @Override
    public String toString() {
        return "TvSeasonDetailEntity{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", images=" + images +
                ", videos=" + videos +
                ", credits=" + credits +
                ", episodes=" + episodes +
                '}';
    }
}
