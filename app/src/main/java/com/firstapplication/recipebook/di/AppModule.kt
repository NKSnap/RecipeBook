package com.firstapplication.recipebook.di

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.room.Room
import com.firstapplication.recipebook.data.interfaces.RecipeDao
import com.firstapplication.recipebook.data.local.RecipeBookDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRecipeBookDatabase(context: Context): RecipeBookDatabase {
        return Room.databaseBuilder(
            context,
            RecipeBookDatabase::class.java,
            RecipeBookDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    fun provideRecipeDao(recipeBookDatabase: RecipeBookDatabase): RecipeDao {
        return recipeBookDatabase.recipeDatabase
    }

    @Singleton
    @Provides
    fun provideInputMethodManager(context: Context): InputMethodManager {
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

}