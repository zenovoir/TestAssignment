package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CountryDetails
import javax.inject.Inject

class CountriesAdapter @Inject constructor(
    var countriesList: List<CountryDetails>,
    var onCountrySelected: ((CountryDetails) -> Unit)? = null
) :
    RecyclerView.Adapter<CountriesAdapter.ViewModel>(), Filterable {
    var filterableCountriesList: ArrayList<CountryDetails>

    init {
        filterableCountriesList = countriesList as ArrayList<CountryDetails>
    }

    class ViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countryName: TextView = itemView.findViewById(R.id.mCountryName)
        var mCountryFlag: TextView = itemView.findViewById(R.id.mCountryFlag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.countries_list_item_view, parent, false)
        return ViewModel(view)
    }

    override fun getItemCount(): Int {
        return filterableCountriesList.size
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        val country = filterableCountriesList[position]
        val countryFlag = getCountryFlag(country.code)
        holder.mCountryFlag.text = countryFlag
        holder.countryName.text = country.name
        filterableCountriesList[position].flag = countryFlag
        holder.itemView.setOnClickListener {
            onCountrySelected?.invoke(country)
        }
    }

    private fun getCountryFlag(countryCode: String): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
        val flag =
            (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))

        return flag
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterableCountriesList = countriesList as ArrayList<CountryDetails>
                } else {
                    val resultList = ArrayList<CountryDetails>()
                    for (row in filterableCountriesList) {
                        if (row.name.lowercase().contains(constraint.toString().lowercase())) {
                            resultList.add(row)
                        }
                    }
                    filterableCountriesList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterableCountriesList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterableCountriesList = results?.values as ArrayList<CountryDetails>
                notifyDataSetChanged()
            }
        }
    }
}