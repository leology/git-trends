/* Modules create core logic for implementing.
 * Since they need api, so we passed
 */

package com.leology.gittrends.di

import com.leology.gittrends.repository.GitInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GitModule {

    val apiBaseURL = "https://ghapi.huchen.dev/"

    @Singleton
    @Provides
    fun getGitInterface(retrofit: Retrofit): GitInterface {
        return retrofit.create(GitInterface::class.java) // .java will return the Java class instance corresponding to KClass GitInterface
    }                                                    // This is needed coz retrofit is implemented in Java and hence its methods need JClass

    @Singleton // persist in memory
    @Provides // this makes the function injectable
    fun getGitRepoInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}