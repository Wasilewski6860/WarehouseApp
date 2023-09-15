package com.example.newwarehouseapp.presentation.warehouse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newwarehouseapp.R
import com.example.newwarehouseapp.databinding.ProductItemBinding
import com.example.newwarehouseapp.domain.models.Product
import com.example.newwarehouseapp.domain.models.ProductWithProductOnWarehouse

class WarehouseAdapter(
    private val context: Context,
    private val actionListener: WarehouseActionListener
) : ListAdapter<ProductWithProductOnWarehouse, WarehouseAdapter.ProductWithProductOnWarehouseViewHolder>(DiffCallBack), View.OnClickListener {

    class ProductWithProductOnWarehouseViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductWithProductOnWarehouseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)

//        binding.root.setOnClickListener(this)
//        binding.favouriteBtnItem.setOnClickListener(this)
        binding.productCard.setOnClickListener(this)
//        binding.itemLayout.setOnClickListener(this)
        return ProductWithProductOnWarehouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductWithProductOnWarehouseViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            actionListener.onClickItem(item)
        }
        with(holder.binding) {
            productNameProductItem.text = item.name
            productDescriptionProductItem.text = item.description
            productCountProductItem.text = item.productOnWarehouse.count.toString()
            productPriceProductItem.text = item.price.toString()
        }
    }

    override fun onClick(v: View?) {
        var tag = v?.tag
//        var cat = v?.tag as ProductWithProductOnWarehouse
//        when (v.id) {
//            R.id.product_card ->{
//                actionListener.onClickItem(cat)
//            }
//            else -> actionListener.onClickItem(cat)
//        }
    }

    interface WarehouseActionListener {
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