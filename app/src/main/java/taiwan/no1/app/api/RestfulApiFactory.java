package taiwan.no1.app.api;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.api.config.IApiConfig;
import taiwan.no1.app.api.config.TMDBConfig;

/**
 * Factory that creates different implementations of {@link IApiConfig}.
 *
 * @author Jieyi
 * @since 12/10/16
 */

@Singleton
public class RestfulApiFactory {

    @Inject
    RestfulApiFactory() {
    }

    /**
     * Create a new http service configuration.
     *
     * @return TmDb http service config.
     */
    @NonNull
    public IApiConfig createMovieDBConfig() {
        return new TMDBConfig();
    }
}
