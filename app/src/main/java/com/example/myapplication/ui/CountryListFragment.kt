package com.example.myapplication.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CountryDetails
import com.example.myapplication.mvvm.CountryViewModel
import com.example.myapplication.ui.adapter.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryListFragment : Fragment(), TextWatcher {

    private lateinit var navController: NavController
    private val viewModel by viewModels<CountryViewModel>()
    private var countriesListView: RecyclerView? = null
    private var searchCountry: EditText? = null
    lateinit var countryAdapter: CountriesAdapter
    lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        navController = Navigation.findNavController(view)

        countriesListView = view.findViewById(R.id.countries_list_view);
        searchCountry = view.findViewById(R.id.mEditSearchCountry)
        loadingDialog = LoadingDialog(context)
        searchCountry?.addTextChangedListener(this)

        lifecycleScope.launch {
            loadingDialog.showProgressDialog("Loading....")
            viewModel.getCountriesList()
        }

        observeData();
    }

    private fun observeData() {
        viewModel.countryList.observe(viewLifecycleOwner, Observer {
            loadingDialog.hideProgressDialog()
            setAdapter(it)
        })
    }

    private fun setAdapter(countries: List<CountryDetails>) {
        countryAdapter = CountriesAdapter(countries) {
            val bundle = Bundle()
            bundle.putSerializable("Country", it)
            navController.navigate(
                R.id.action_countryListFragment_to_countryDetailsFragment,
                bundle
            )
        }
        this.countriesListView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.countriesListView?.adapter = countryAdapter
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        countryAdapter.filter.filter(s)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        countryAdapter.filter.filter(s)
    }

    override fun afterTextChanged(s: Editable?) {
    }
}