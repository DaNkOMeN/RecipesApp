package com.android.example.collectrecipelistapplication.fragments.undoughtPurchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.collectrecipelistapplication.R
import com.android.example.collectrecipelistapplication.databinding.FragmentUnboughtPurchasesBinding
import com.android.example.collectrecipelistapplication.fragments.recipeList.RecipeListViewModel

class UnboughtPurchaseFragment : Fragment() {

    private lateinit var mRecipeListModel: RecipeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentUnboughtPurchasesBinding  = DataBindingUtil.inflate(inflater, R.layout.fragment_unbought_purchases, container, false)
        val unboughtPuchasesAdapter = UnboughtPurchaseAdapter()

        val unboughtPurchasesList = binding.unboughtPurchasesList
        unboughtPurchasesList.adapter = unboughtPuchasesAdapter

        mRecipeListModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        mRecipeListModel.allUnboughtPurchases.observe(viewLifecycleOwner, Observer {
            it?.let {
                unboughtPuchasesAdapter.submitList(it)
            }
        })

        return binding.root
    }

}