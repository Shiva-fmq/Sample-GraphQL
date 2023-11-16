package com.fmq.mysamplekotlingraphql.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fmq.mysamplekotlingraphql.R
import com.fmq.mysamplekotlingraphql.adapter.ContinentListRecyclerAdapter
import com.fmq.mysamplekotlingraphql.databinding.ActivityMainBinding
import com.fmq.mysamplekotlingraphql.domain.ContinentDetails
import com.fmq.mysamplekotlingraphql.utils.SimpleDividerItemDecorator
import com.fmq.mysamplekotlingraphql.viewmodel.GraphQLViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import rx.Observable

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val graphQLViewModel: GraphQLViewModel by viewModels()

    private lateinit var mRecyclerAdapter : ContinentListRecyclerAdapter
    var progress : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerAdapter()

     //   setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.swipeRefresh.setOnRefreshListener {
            mRecyclerAdapter.clearAllItems()
            getList()
        }

        binding.fab.setOnClickListener {
            navigateToSecond()
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setRecyclerAdapter() {

        mRecyclerAdapter = ContinentListRecyclerAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mRecyclerAdapter
        binding.recyclerView.addItemDecoration(SimpleDividerItemDecorator(this))
     //   mRecyclerAdapter.setListener(this)

    }

    private fun getList() {

        binding.swipeRefresh.isRefreshing = false

        progress = ProgressDialog(this)
        progress?.setMessage("Please Wait....")
        progress?.show()

        graphQLViewModel.data.observe(this) {
            Log.e("Tag", Gson().toJson(it))
            progress?.dismiss()
            if(it!=null) {
                if(it.isNotEmpty()) {
                    it.let {
                        mRecyclerAdapter.setItemList(it)
                    }

                }
            }
        }

        graphQLViewModel.getContinentsList()
    }

    private fun navigateToSecond() {
        Intent(this,SecondActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onResume() {
        super.onResume()
        getList()
    }
}