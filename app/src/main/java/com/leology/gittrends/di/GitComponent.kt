package com.leology.gittrends.di

import com.leology.gittrends.viewmodel.GitViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitModule::class])
interface GitComponent {

    fun inject(gitViewModel: GitViewModel) // this is gonna access module inside a ViewModel
}