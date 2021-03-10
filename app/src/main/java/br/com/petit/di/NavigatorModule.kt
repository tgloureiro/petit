package br.com.petit.di

import br.com.petit.navigator.MainRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.tiagoloureiro.navigator.NavigatorBloc
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigatorModule {
    @Provides
    @Singleton
    fun provideNavigator():
            NavigatorBloc {
        return NavigatorBloc(
            //TODO: Wrong, should use Assisted Factory
            MainRoute
        )
    }


}