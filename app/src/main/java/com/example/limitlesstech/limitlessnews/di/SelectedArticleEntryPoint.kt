package com.example.limitlesstech.limitlessnews.di

import com.example.limitlesstech.limitlessnews.presentation.common.manager.SelectedArticleManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SelectedArticleEntryPoint {

    fun selectedArticleManager(): SelectedArticleManager
}