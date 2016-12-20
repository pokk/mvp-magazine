package taiwan.no1.accounting.internal.di.components

import dagger.Component
import taiwan.no1.accounting.internal.di.annotations.PerFragment
import taiwan.no1.accounting.internal.di.modules.ActivityModule
import taiwan.no1.accounting.internal.di.modules.FragmentModule

/**
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2016/12/20
 */

@PerFragment
@Component(dependencies = arrayOf(ActivityModule::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent