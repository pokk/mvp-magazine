package com.playone.mobile.internal.modules

import dagger.Module
import dagger.Provides
import taiwan.no1.accounting.domain.BaseCase
import taiwan.no1.accounting.domain.CreateFakeCase
import taiwan.no1.accounting.internal.di.annotations.PerActivity

/**
 * @author  Jieyi Wu
 * @version 0.0.1
 * @since   5/29/16
 */

@Module
class UseCaseModule(var id: String = "-1") {
    @Provides
    @PerActivity
    fun provideFakeCase(createFakeCase: CreateFakeCase): BaseCase<CreateFakeCase.Requests> = createFakeCase
}