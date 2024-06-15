package com.example.androidplayground

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ImageAdapter(private val imageUrlList: List<String>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrlList[position]

        // This is a simple, non-optimal way to load images on the UI thread.
        // Do NOT use in production, use an image loading library like Coil, Picasso or Glide instead.
        //GlobalScope.launch(Dispatchers.IO) {
        //    val input = URL(imageUrl).openStream()
        //    val bitmap = BitmapFactory.decodeStream(input)

        //    withContext(Dispatchers.Main) {
        //        holder.imageView.setImageBitmap(bitmap)
        //    }
        //}
        holder.imageView.load(imageUrl)
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }
}