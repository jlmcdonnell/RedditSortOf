package dev.mcd.redditsortof.data.environment

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EnvironmentModule {

    @Binds
    abstract fun environment(environmentImpl: EnvironmentImpl): Environment
}