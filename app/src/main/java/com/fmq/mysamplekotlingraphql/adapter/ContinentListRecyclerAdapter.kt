package com.fmq.mysamplekotlingraphql.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fmq.mysamplekotlingraphql.R
import com.fmq.mysamplekotlingraphql.databinding.ItemRowContinentBinding
import com.fmq.mysamplekotlingraphql.delegate.OnItemClickListener
import com.fmq.mysamplekotlingraphql.domain.ContinentDetails

class ContinentListRecyclerAdapter (val context: Context) : RecyclerView.Adapter<ContinentListRecyclerAdapter.MyViewHolder>() {

    private lateinit var mRecyclerBinding: ItemRowContinentBinding
    private var mItemList: MutableList<ContinentDetails>? = null
    private var mListener : OnItemClickListener? = null
    lateinit var mContext : Context

    init {
        this.mItemList = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mRecyclerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_row_continent,
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

    fun setItemList(dataList: List<ContinentDetails>?) {
        mItemList = dataList?.toMutableList()
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

    inner class MyViewHolder(private var itemDetailBinding: ItemRowContinentBinding) :
        RecyclerView.ViewHolder(itemDetailBinding.root) {

        fun bind(continents: ContinentDetails, position: Int) {

            itemDetailBinding.txtName.text = continents.name
            itemDetailBinding.txtCode.text = continents.code

            itemDetailBinding.root.setOnClickListener {
                mListener?.onClick(continents)
            }

        }
    }
}