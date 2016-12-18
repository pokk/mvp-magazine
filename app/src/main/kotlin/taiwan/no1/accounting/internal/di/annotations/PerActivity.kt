package taiwan.no1.accounting.internal.di.annotations

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the activity to be
 * memorized in the correct component.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/06
 */

@Scope
@Retention
annotation class PerActivity