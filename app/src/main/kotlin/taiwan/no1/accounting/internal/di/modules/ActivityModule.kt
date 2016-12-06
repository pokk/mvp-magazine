package com.playone.mobile.internal.modules

import android.app.Activity
import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.internal.di.annotations.PerActivity

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */
@Module
class ActivityModule(var activity: Activity) {
    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    fun activity(): Activity = this.activity
}