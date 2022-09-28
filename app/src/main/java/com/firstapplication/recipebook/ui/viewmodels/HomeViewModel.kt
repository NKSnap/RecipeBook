package com.firstapplication.recipebook.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.firstapplication.recipebook.data.interfaces.RecipeRepository
import com.firstapplication.recipebook.data.models.RecipeEntity
import com.firstapplication.recipebook.extensions.migrateFromRecipeEntityToRecipeModel
import com.firstapplication.recipebook.extensions.migrateFromRecipeModelToRecipeEntity
import com.firstapplication.recipebook.ui.models.RecipeModel
import kotlinx.coroutines.*

class HomeViewModel(application: Application, private val repository: RecipeRepository) :
    AndroidViewModel(application) {

    fun deleteRecipeFromDB(recipeModel: RecipeModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteRecipe(recipeModel.migrateFromRecipeModelToRecipeEntity())
    }

    private val _recipesList = MutableLiveData<List<RecipeModel>>()
    val recipesList: LiveData<List<RecipeModel>>
        get() = _recipesList

    private val recipeListObserver = Observer<List<RecipeEntity>> { entitiesList ->
        setReadRecipes(entitiesList = entitiesList)
    }

    private val recipeModelsListObserver = Observer<List<RecipeModel>> { models ->
        _recipesList.value = models
    }

    private val recipeModels = MutableLiveData<List<RecipeModel>>()

    private fun setReadRecipes(entitiesList: List<RecipeEntity>) =
        viewModelScope.launch {
            recipeModels.value = getMigratedToRecipeModelList(entitiesList = entitiesList)
        }

    private suspend fun getMigratedToRecipeModelList(entitiesList: List<RecipeEntity>): List<RecipeModel> {
        val list = mutableListOf<RecipeModel>()

        val coroutineResult = viewModelScope.async {
            entitiesList.forEach { item ->
                list.add(item.migrateFromRecipeEntityToRecipeModel())
            }
        }

        coroutineResult.await()

        return list
    }

    init {
        repository.allRecipes.observeForever(recipeListObserver)
        recipeModels.observeForever(recipeModelsListObserver)
    }

    override fun onCleared() {
        repository.allRecipes.removeObserver(recipeListObserver)
        recipeModels.removeObserver(recipeModelsListObserver)
        super.onCleared()
    }

}