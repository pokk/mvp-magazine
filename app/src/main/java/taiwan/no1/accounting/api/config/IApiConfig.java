package taiwan.no1.accounting.api.config;

import android.support.annotation.NonNull;

/**
 * Interface of the setting of the difference http configurations.
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/10/16
 */

public interface IApiConfig {
    /**
     * Obtain the base http url.
     *
     * @return restful api base url information.
     */
    @NonNull
    String getApiBaseUrl();
}
