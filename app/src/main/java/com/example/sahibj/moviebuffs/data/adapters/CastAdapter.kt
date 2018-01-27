package com.example.sahibj.moviebuffs.data.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sahibj.moviebuffs.R
import com.example.sahibj.moviebuffs.databinding.CastItemBinding
import com.example.sahibj.moviebuffs.models.MovieCastResponse
import com.example.sahibj.moviebuffs.viewmodels.MovieCastViewModel

/**
 * Created by sahibjaspal on 1/27/18.
 */
class CastAdapter(viewModel: MovieCastViewModel) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private lateinit var binding: CastItemBinding
    val castList: List<MovieCastResponse.Cast> = viewModel.castList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cast_item,
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val castItem = castList[position]
        holder.castPersonName.text = castItem.name
        Glide.with(holder.castPersonImage.context)
                .load("http://image.tmdb.org/t/p/w780" + castItem.personImage)
                .into(holder.castPersonImage)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    inner class ViewHolder(binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var castPersonImage: ImageView = binding.castPersonImage
        var castPersonName: TextView = binding.castPersonName
    }
}