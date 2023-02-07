package com.example.guidedaechelin.base

import android.annotation.SuppressLint
import android.content.Entity
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.databinding.ViewDataBindingKtx
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerviewAdapter<T : Any, VDB : ViewDataBinding>(
    @LayoutRes private val itemLayoutRes: Int
) : ListAdapter<T, BaseRecyclerviewAdapter<T, VDB>.BaseViewHolder>(BaseItemCallback<T>()) {

    abstract fun action(data: T, binding: VDB)

    inner class BaseViewHolder(private val binding: VDB) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            action(item, binding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }

        return holder.bind(getItem(position))
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

}

class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}

