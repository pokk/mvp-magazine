package taiwan.no1.app.internal.di.annotations

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the activity to be
 * memorized in the correct component.
 *
 * @author  Jieyi
 * @since   2016/12/06
 */

@Scope
@Retention
annotation class PerActivity