package com.firstapplication.recipebook.di

import com.firstapplication.recipebook.ui.interfacies.OnEditTextFocusChangeListener
import com.firstapplication.recipebook.ui.listeners.OnEditTextFocusChangeListenerImpl
import dagger.Binds
import dagger.Module

@Module
interface ListenersBindModule {

    @Binds
    fun bindOnEditTextFocusChangeListenerImplToOnEditTextFocusChangeListener(
        onEditTextFocusChangeListenerImpl: OnEditTextFocusChangeListenerImpl
    ): OnEditTextFocusChangeListener

}