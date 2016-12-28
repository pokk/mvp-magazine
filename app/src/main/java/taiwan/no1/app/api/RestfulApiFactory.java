package taiwan.no1.app.api;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.api.config.IApiConfig;
import taiwan.no1.app.api.config.MovieDBConfig;

/**
 * Factory that creates different implementations of {@link IApiConfig}.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/10/16
 */

@Singleton
public class RestfulApiFactory {

    @Inject
    RestfulApiFactory() {
    }

    @NonNull
    public IApiConfig createMovieDBConfig() {
        return new MovieDBConfig();
    }
}
