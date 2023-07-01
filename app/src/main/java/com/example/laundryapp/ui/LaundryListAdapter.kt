package com.example.laundryapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laundryapp.R
import com.example.laundryapp.model.LaundryModell

class LaundryListAdapter(
    private val onItemClickListener: (LaundryModell) -> Unit
) : ListAdapter<LaundryModell, LaundryListAdapter.LaundryViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaundryViewHolder {
        return LaundryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LaundryViewHolder, position: Int) {
        val LaundryModell =getItem(position)
        holder.bind(LaundryModell)
        holder.itemView.setOnClickListener {
            onItemClickListener(LaundryModell)
        }
    }

    class LaundryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        private val paketTextView: TextView = itemView.findViewById(R.id.paketTextView)
        private val weightTextView: TextView = itemView.findViewById(R.id.weightTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(LaundryModell: LaundryModell?){
            nameTextView.text = LaundryModell?.name
            addressTextView.text = LaundryModell?.address
            paketTextView.text = LaundryModell?.paket
            weightTextView.text = LaundryModell?.weight.toString()
            priceTextView.text = LaundryModell?.price.toString()

        }
        companion object {
            fun create(parent: ViewGroup): LaundryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_laundry, parent, false)
                return LaundryViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<LaundryModell>() {
            override fun areItemsTheSame(oldItem: LaundryModell, newItem: LaundryModell): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: LaundryModell,
                newItem: LaundryModell
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

private fun LaundryListAdapter.LaundryViewHolder.bind(laundryModell: LaundryModell?) {
    TODO("Not yet implemented")
}
