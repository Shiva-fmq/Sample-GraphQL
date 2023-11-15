package com.fmq.mysamplekotlingraphql.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.fmq.mysamplekotlingraphql.R
import com.fmq.mysamplekotlingraphql.adapter.ContinentListRecyclerAdapter
import com.fmq.mysamplekotlingraphql.utils.SimpleDividerItemDecorator
import com.fmq.mysamplekotlingraphql.databinding.FragmentFirstBinding
import com.fmq.mysamplekotlingraphql.delegate.OnItemClickListener
import com.fmq.mysamplekotlingraphql.utils.ApolloConfig
import com.fmq.mysamplekotlingraphql.utils.AppConstants
import com.fmq.mysamplekotlingraphql.viewmodel.GraphQLViewModel
import com.fmq.mysamplekotlingraphql.viewmodel.MainViewModel
import com.fmq.mysamplekotlingraphql.viewmodel.common.kodeinViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
  //  private val viewModel: MainViewModel by kodeinViewModel()
    private val graphQLViewModel: GraphQLViewModel by viewModels()

    private lateinit var mRecyclerAdapter : ContinentListRecyclerAdapter
    var progress : ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setRecyclerAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener {
            mRecyclerAdapter.clearAllItems()
            getList()
        }
    }

    private fun setRecyclerAdapter() {

        mRecyclerAdapter = ContinentListRecyclerAdapter(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mRecyclerAdapter
        binding.recyclerView.addItemDecoration(SimpleDividerItemDecorator(requireContext()))
        mRecyclerAdapter.setListener(this)

    }

    private fun getList() {

        binding.swipeRefresh.isRefreshing = false

        progress = ProgressDialog(requireContext())
        progress?.setMessage("Please Wait....")
        progress?.show()

       /* viewModel.data.observe(viewLifecycleOwner) {
            Log.e("Response", Gson().toJson(it))
            progress?.dismiss()
            if(it!=null) {
                if(it.continents.isNotEmpty()) {
                    mRecyclerAdapter.setItemList(it.continents)
                }
            }
        }*/


       // viewModel.getContinentList()

        graphQLViewModel.data.observe(viewLifecycleOwner) {
            Log.e("Tag", Gson().toJson(it))
        }

        graphQLViewModel.getContinentsList()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(data: Any) {
        Log.e("Clicked",Gson().toJson(data))

        val bundle = bundleOf("data" to Gson().toJson(data))
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

    }

    override fun onResume() {
        super.onResume()
        getList()
    }
}