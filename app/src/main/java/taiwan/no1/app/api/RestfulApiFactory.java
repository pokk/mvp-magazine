package taiwan.no1.app.api;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.api.config.FakeConfig;
import taiwan.no1.app.api.config.IApiConfig;

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
    public IApiConfig createFakeConfig() {
        return new FakeConfig();
    }
}
