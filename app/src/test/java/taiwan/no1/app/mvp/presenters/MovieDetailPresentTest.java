package taiwan.no1.app.mvp.presenters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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
 * @since 2/17/17
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MovieDetailPresentTest {
    // Real object.
    @Rule public DaggerMockRule<NetComponent> mockRule = new DaggerMockRule<>(NetComponent.class,
                                                                              new NetModule(RuntimeEnvironment.application));


    @InjectFromComponent private CloudDataStore cloudDataStore;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void init() throws Exception {
    }
}