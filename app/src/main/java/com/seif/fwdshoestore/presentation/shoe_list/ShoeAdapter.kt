package com.seif.fwdshoestore.presentation.shoe_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seif.booksislandapp.utils.MyDiffUtil
import com.seif.fwdshoestore.R
import com.seif.fwdshoestore.databinding.ItemShoeBinding
import com.seif.fwdshoestore.domain.model.Shoe
import com.squareup.picasso.Picasso

class ShoeAdapter : RecyclerView.Adapter<ShoeAdapter.MyViewHolder>() {
    private var products: List<Shoe> = emptyList()

    class MyViewHolder(private val binding: ItemShoeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: Shoe) {
            binding.tvShoeName.text = shoe.name
            binding.tvPrice.text = itemView.context.getString(R.string.egypt_currency, shoe.price)
            Picasso.get().load(R.drawable.shoe).into(binding.ivShoe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemShoeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    fun addShoeList(newProducts: List<Shoe>) {
        val diffUtilCallBack = MyDiffUtil(this.products, newProducts)
        val result = DiffUtil.calculateDiff(diffUtilCallBack)
        this.products = newProducts
        result.dispatchUpdatesTo(this)
    }
}