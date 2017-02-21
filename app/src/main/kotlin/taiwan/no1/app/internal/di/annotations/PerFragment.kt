package taiwan.no1.app.internal.di.annotations

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the fragment to be
 * memorized in the correct component.
 *
 * @author  Jieyi
 * @since   12/20/16
 */

@Scope
@Retention
annotation class PerFragment