package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Created by weian on 2017/1/21.
 */

public class TVEpisodesEntity {

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
    private VideosBean videos;
    private ImagesBean images;
    private List<CrewBean> crew;
    private List<GuestStarsBean> guest_stars;

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

    public VideosBean getVideos() {
        return videos;
    }

    public void setVideos(VideosBean videos) {
        this.videos = videos;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public List<CrewBean> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewBean> crew) {
        this.crew = crew;
    }

    public List<GuestStarsBean> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(List<GuestStarsBean> guest_stars) {
        this.guest_stars = guest_stars;
    }

    public static class VideosBean {
        private List<?> results;

        public List<?> getResults() {
            return results;
        }

        public void setResults(List<?> results) {
            this.results = results;
        }
    }

    public static class ImagesBean {
        private List<StillsBean> stills;

        public List<StillsBean> getStills() {
            return stills;
        }

        public void setStills(List<StillsBean> stills) {
            this.stills = stills;
        }

        public static class StillsBean {

            private double aspect_ratio;
            private String file_path;
            private int height;
            private Object iso_639_1;
            private double vote_average;
            private int vote_count;
            private int width;

            public double getAspect_ratio() {
                return aspect_ratio;
            }

            public void setAspect_ratio(double aspect_ratio) {
                this.aspect_ratio = aspect_ratio;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public Object getIso_639_1() {
                return iso_639_1;
            }

            public void setIso_639_1(Object iso_639_1) {
                this.iso_639_1 = iso_639_1;
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

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }

    public static class CrewBean {

        private int id;
        private String credit_id;
        private String name;
        private String department;
        private String job;
        private String profile_path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }

    public static class GuestStarsBean {
        /**
         * id : 208907
         * name : Todd Lasance
         * credit_id : 5813d85dc3a3687a8d027608
         * character : Edward Clariss
         * order : 132
         * profile_path : /tW0FVdoUYqsNyFOlBgRf054xUZp.jpg
         */

        private int id;
        private String name;
        private String credit_id;
        private String character;
        private int order;
        private String profile_path;

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

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }
}
