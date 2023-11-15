package com.fmq.mysamplekotlingraphql.view

import GetContinentsQuery
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.fmq.mysamplekotlingraphql.R
import com.fmq.mysamplekotlingraphql.adapter.ContinentListRecyclerAdapter
import com.fmq.mysamplekotlingraphql.adapter.CountriesListRecyclerAdapter
import com.fmq.mysamplekotlingraphql.databinding.FragmentSecondBinding
import com.fmq.mysamplekotlingraphql.utils.ApolloConfig
import com.fmq.mysamplekotlingraphql.utils.AppConstants
import com.fmq.mysamplekotlingraphql.utils.SimpleDividerItemDecorator
import com.fmq.mysamplekotlingraphql.viewmodel.MainViewModel
import com.fmq.mysamplekotlingraphql.viewmodel.common.kodeinViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

  //  private val viewModel: MainViewModel by kodeinViewModel()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var mReceivedData : GetContinentsQuery.Continent? = null
    private lateinit var mRecyclerAdapter : CountriesListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setRecyclerAdapter()
        arguments?.let {
            if(arguments?.getString("data")!=null) {
                mReceivedData = Gson().fromJson(arguments?.getString("data"),GetContinentsQuery.Continent::class.java)
                Log.e("Received",Gson().toJson(mReceivedData))
            }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getContinents(mReceivedData?.code?:"-")
    }

    private fun setRecyclerAdapter() {

        mRecyclerAdapter = CountriesListRecyclerAdapter(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mRecyclerAdapter
        binding.recyclerView.addItemDecoration(SimpleDividerItemDecorator(requireContext()))

    }

    private fun getContinents(code: String) {

       /* viewModel.countryData.observe(viewLifecycleOwner) {
            Log.e("Response : ",Gson().toJson(it))
            if(it!=null) {
                it.continent?.let {country ->
                    if(country.countries.isNotEmpty()) {
                        mRecyclerAdapter.setItemList(country.countries)
                    }
                }
            }

        }
        viewModel.getCountriesList(code)*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}