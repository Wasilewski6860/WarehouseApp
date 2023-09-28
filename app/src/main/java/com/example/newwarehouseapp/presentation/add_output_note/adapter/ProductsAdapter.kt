package com.example.newwarehouseapp.presentation.add_output_note.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newwarehouseapp.databinding.ProductItemBinding
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse

class ProductsAdapter(
    private val context: Context,
    private val actionListener: ProductsActionListener
) : ListAdapter<ProductWithProductOnWarehouse, ProductsAdapter.ProductWithProductOnWarehouseViewHolder>(DiffCallBack), View.OnClickListener {

    class ProductWithProductOnWarehouseViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductWithProductOnWarehouseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)

        binding.productCard.setOnClickListener(this)
        return ProductWithProductOnWarehouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductWithProductOnWarehouseViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            actionListener.onClickItem(item)
        }
        with(holder.binding) {
            productNameProductItem.text = "Name: "+item.name
            productDescriptionProductItem.text = "Description: "+item.description
            productCountProductItem.text = "Count: "+item.productOnWarehouse.count.toString()
            productPriceProductItem.text = "Price: "+item.price.toString()
        }
    }

    override fun onClick(v: View?) {
        var tag = v?.tag
    }

    interface ProductsActionListener {
        fun onClickItem(productWithProductOnWarehouse: ProductWithProductOnWarehouse)
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<ProductWithProductOnWarehouse>() {

            override fun areItemsTheSame(oldItem: ProductWithProductOnWarehouse, newItem: ProductWithProductOnWarehouse): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ProductWithProductOnWarehouse, newItem: ProductWithProductOnWarehouse): Boolean {
                return oldItem == newItem
            }
        }
    }

}