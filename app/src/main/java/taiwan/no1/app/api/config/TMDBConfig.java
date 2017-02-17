package taiwan.no1.app.api.config;

import android.support.annotation.NonNull;

/**
 * TmDb provides movies, tvs, casts information api configuration.
 *
 * @author Jieyi
 * @since 12/10/16
 */

public class TMDBConfig implements IApiConfig {
    // Image prefix base http url.
    public final static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    // All basic http api url of TmDB. 
    private final static String BASE_URL = "https://api.themoviedb.org/3/";

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public String getApiBaseUrl() {
        return BASE_URL;
    }
}
