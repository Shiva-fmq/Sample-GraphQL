package com.fmq.mysamplekotlingraphql.adapter

import FindCountriesOfAContinentQuery
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fmq.mysamplekotlingraphql.R
import com.fmq.mysamplekotlingraphql.databinding.ItemRowCountryListBinding
import com.fmq.mysamplekotlingraphql.delegate.OnItemClickListener

class CountriesListRecyclerAdapter(val context: Context) : RecyclerView.Adapter<CountriesListRecyclerAdapter.MyViewHolder>() {

    private lateinit var mRecyclerBinding: ItemRowCountryListBinding
    private var mItemList: MutableList<FindCountriesOfAContinentQuery.Country>? = null
    private var mListener : OnItemClickListener? = null
    lateinit var mContext : Context

    init {
        this.mItemList = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mRecyclerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_row_country_list,
            parent, false
        )
        mContext = parent.context
        return MyViewHolder(mRecyclerBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mItemList!![position], position)
    }

    override fun getItemCount(): Int {
        return mItemList!!.size
    }

    fun setListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    fun setItemList(dataList: List<FindCountriesOfAContinentQuery.Country>) {
        mItemList = dataList.toMutableList()
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun clearAllItems() {
        mItemList!!.clear()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private var itemDetailBinding: ItemRowCountryListBinding) :
        RecyclerView.ViewHolder(itemDetailBinding.root) {

        fun bind(country: FindCountriesOfAContinentQuery.Country, position: Int) {

            itemDetailBinding.txtName.text = country.name
            itemDetailBinding.txtPhone.text = "Phone : ${country.phone}"
            itemDetailBinding.txtCurrency.text = "Currency : ${country.currency}"


        }
    }
}