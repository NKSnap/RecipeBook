package com.firstapplication.recipebook.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.firstapplication.recipebook.App
import com.firstapplication.recipebook.R
import com.firstapplication.recipebook.databinding.FragmentHomeBinding
import com.firstapplication.recipebook.extensions.appComponent
import com.firstapplication.recipebook.ui.adapters.RecipeAdapter
import com.firstapplication.recipebook.ui.interfacies.OnRecipeItemClickListener
import com.firstapplication.recipebook.ui.models.RecipeModel
import com.firstapplication.recipebook.ui.viewmodels.HomeViewModel
import com.firstapplication.recipebook.ui.viewmodels.factories.OnlyRecipeRepositoryViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home), OnRecipeItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var onlyRecipeRepositoryViewModelFactory: OnlyRecipeRepositoryViewModelFactory.Factory

    private val viewModel: HomeViewModel by viewModels {
        onlyRecipeRepositoryViewModelFactory.create(activity?.application as App)
    }

    override fun onAttach(context: Context) {
        context.applicationContext.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.navSearch)
        }

        val adapter = RecipeAdapter(this)

        adapter.submitList(listOf(
            RecipeModel(
                id = 1,
                imageId = 1,
                title = "My first title",
                recipeInfo = "Information about my first recipe",
                cookingTime = 1.4,
                ingredientsCount = 10,
                ingredients = mapOf(),
                isSaved = false
            ),
            RecipeModel(
                id = 2,
                imageId = 1,
                title = "My first title",
                recipeInfo = "Information about my first recipe",
                cookingTime = 1.4,
                ingredientsCount = 10,
                ingredients = mapOf(),
                isSaved = false
            ),
            RecipeModel(
                id = 3,
                imageId = 1,
                title = "My first title",
                recipeInfo = "Information about my first recipe",
                cookingTime = 1.4,
                ingredientsCount = 10,
                ingredients = mapOf(),
                isSaved = false
            ),
            RecipeModel(
                id = 4,
                imageId = 1,
                title = "My first title",
                recipeInfo = "Information about my first recipe",
                cookingTime = 1.4,
                ingredientsCount = 10,
                ingredients = mapOf(),
                isSaved = false
            ),
            RecipeModel(
                id = 5,
                imageId = 1,
                title = "My first title",
                recipeInfo = "Information about my first recipe",
                cookingTime = 1.4,
                ingredientsCount = 10,
                ingredients = mapOf(),
                isSaved = false
            ),
            RecipeModel(
                id = 6,
                imageId = 1,
                title = "My first title",
                recipeInfo = "Information about my first recipe",
                cookingTime = 1.4,
                ingredientsCount = 10,
                ingredients = mapOf(),
                isSaved = false
            )
        ))

        binding.rwRecipes.adapter = adapter
    }

    override fun onItemClick(view: View, recipeModel: RecipeModel) = when(view.id) {
        R.id.btnMarker -> Toast.makeText(requireContext(), "changed", Toast.LENGTH_SHORT).show()
        else -> findNavController().navigate(R.id.navRecipeInfo)
    }
}