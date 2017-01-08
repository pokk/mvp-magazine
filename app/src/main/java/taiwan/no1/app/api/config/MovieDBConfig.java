package taiwan.no1.app.api.config;

import android.support.annotation.NonNull;

/**
 * {@inheritDoc}
 *
 * @author Jieyi
 * @since 12/10/16
 */

public class MovieDBConfig implements IApiConfig {
    public final static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    private final static String BASE_URL = "https://api.themoviedb.org/3/";

    @NonNull
    @Override
    public String getApiBaseUrl() {
        return BASE_URL;
    }
}
