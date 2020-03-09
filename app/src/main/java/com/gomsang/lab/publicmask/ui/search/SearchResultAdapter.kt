package com.gomsang.lab.publicmask.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.gomsang.lab.publicmask.databinding.ItemSearchPlaceResultBinding
import com.gomsang.lab.publicmask.libs.OnRecyclerItemClickListener
import com.gomsang.lab.publicmask.libs.datas.Place

class SearchResultAdapter(val context: Context) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    private val places = mutableListOf<Place>()

    var onRecyclerItemClickListener: OnRecyclerItemClickListener<Place>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemSearchPlaceResultBinding.inflate(
                LayoutInflater.from(
                    context
                ), parent, false
            )
        )
    }

    fun replace(list: MutableList<Place>) {
        places.clear()
        places.addAll(list)

    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(places[position])
    }

    inner class SearchResultViewHolder(val binding: ItemSearchPlaceResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.data = place
            setFadeAnimation(binding.root)

            binding.root.setOnClickListener {
                onRecyclerItemClickListener?.let {
                    it.onClicked(places.indexOf(place), place);
                }
            }
        }

        private fun setFadeAnimation(view: View) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 500
            view.startAnimation(anim)
        }
    }
}