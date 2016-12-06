package com.playone.mobile.internal.components

import com.playone.mobile.internal.modules.ActivityModule
import com.playone.mobile.internal.modules.UseCaseModule
import dagger.Component
import taiwan.no1.accounting.internal.di.annotations.PerActivity
import taiwan.no1.accounting.internal.di.components.AppComponent
import taiwan.no1.accounting.ui.fragments.MainFragment

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, UseCaseModule::class))
interface UseCaseComponent: ActivityComponent {
    fun inject(mainFragment: MainFragment)
}