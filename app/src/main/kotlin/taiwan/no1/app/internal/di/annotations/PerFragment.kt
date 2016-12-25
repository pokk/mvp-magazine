package taiwan.no1.app.internal.di.annotations

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the fragment to be
 * memorized in the correct component.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/20
 */

@Scope
@Retention
annotation class PerFragment