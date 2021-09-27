package com.mayouf.catstechnicaltest.presentation.cats

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayouf.catstechnicaltest.databinding.CatsRowBinding
import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.utils.loadImageFull

internal class CatsAdapter(
    private val cats: MutableList<Cats>
) :
    RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val holderCatsBinding =
            CatsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatsViewHolder(holderCatsBinding)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.onBind(cats[position])
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    inner class CatsViewHolder(private val itemBinding: CatsRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(cats: Cats) {
            Log.i("Binding data", cats.id)
            itemBinding.ivCats.loadImageFull(cats.url)
        }
    }

}
