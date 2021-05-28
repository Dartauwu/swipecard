package com.darta.matchmaking.fragment.swipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darta.matchmaking.R
import com.squareup.picasso.Picasso

class CardStackAdapter(items: List<ItemModel>) :
    RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
    private var items: List<ItemModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.swipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var nama: TextView
        var usia: TextView
        var kota: TextView
        fun setData(data: ItemModel) {
            Picasso.get()
                .load(data.getImage())
                .fit()
                .centerCrop()
                .into(image)
            nama.setText(data.getNama())
            usia.setText(data.getUsia())
            kota.setText(data.getKota())
        }

        init {
            image = itemView.findViewById(R.id.item_image)
            nama = itemView.findViewById(R.id.item_name)
            usia = itemView.findViewById(R.id.item_age)
            kota = itemView.findViewById(R.id.item_city)
        }
    }

    fun getItems(): List<ItemModel> {
        return items
    }

    fun setItems(items: List<ItemModel>) {
        this.items = items
    }

    init {
        this.items = items
    }
}