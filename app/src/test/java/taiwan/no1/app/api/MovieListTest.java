package taiwan.no1.app.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import it.cosenonjaviste.daggermock.DaggerMockRule;
import it.cosenonjaviste.daggermock.InjectFromComponent;
import taiwan.no1.app.BuildConfig;
import taiwan.no1.app.data.source.CloudDataStore;
import taiwan.no1.app.internal.di.components.NetComponent;
import taiwan.no1.app.internal.di.modules.NetModule;

/**
 * @author Jieyi
 * @since 2/16/17
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MovieListTest {
    @Rule public DaggerMockRule<NetComponent> mockRule = new DaggerMockRule<>(NetComponent.class,
                                                                              new NetModule(RuntimeEnvironment.application));

    @InjectFromComponent private CloudDataStore cloudDataStore;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveMovieList() throws Exception {
        cloudDataStore.moviesEntities(CloudDataStore.Movies.NOW_PLAYING, 1);
        System.out.println(cloudDataStore);
    }
}
