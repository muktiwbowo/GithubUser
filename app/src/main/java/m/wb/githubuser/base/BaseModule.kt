package m.wb.githubuser.base

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.wb.githubuser.service.API
import m.wb.githubuser.service.instance

@Module
@InstallIn(SingletonComponent::class)
class BaseModule {
    @Provides
    fun provideRetrofit(): API = instance()
}