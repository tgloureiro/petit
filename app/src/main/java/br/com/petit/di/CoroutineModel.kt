package br.com.petit.di

import android.content.Context
import br.com.petit.navigator.MainRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import tech.tiagoloureiro.navigator.NavigatorBloc
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutineModel {
    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(Dispatchers.IO)
}