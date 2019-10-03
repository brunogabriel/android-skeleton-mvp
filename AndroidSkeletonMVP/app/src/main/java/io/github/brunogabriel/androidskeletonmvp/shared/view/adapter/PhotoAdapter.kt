package io.github.brunogabriel.androidskeletonmvp.shared.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.androidskeletonmvp.R
import io.github.brunogabriel.androidskeletonmvp.shared.database.extensions.inflate
import io.github.brunogabriel.androidskeletonmvp.shared.database.extensions.loadImage
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import kotlinx.android.synthetic.main.holder_photo.view.*

/**
 * Created by brunosantos on 2019-10-03.
 */
class PhotoAdapter(
    private val photos: List<Photo>
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(parent.inflate(R.layout.holder_photo))
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) = with(itemView) {
            title_text_view.text = photo.title
            photo_image_view.loadImage(photo.url)
        }
    }
}