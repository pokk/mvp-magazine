package taiwan.no1.app.api.config;

import android.support.annotation.NonNull;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/10/16
 */

public class FakeConfig implements IApiConfig {
    private final static String BASE_URL = "http://taiwan.no1.com";

    @NonNull
    @Override
    public String getApiBaseUrl() {
        return BASE_URL;
    }
}
