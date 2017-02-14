package taiwan.no1.app.data.entities;

/**
 * The response list with extra information data class.
 * 
 * @author Jieyi
 * @since 2/4/17
 */

public class BaseListResEntity {
    private int page;
    private int total_results;
    private int total_pages;

    //region Getter and Setter
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
    //endregion

    @Override
    public String toString() {
        return "BaseListResEntity{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                '}';
    }
}
