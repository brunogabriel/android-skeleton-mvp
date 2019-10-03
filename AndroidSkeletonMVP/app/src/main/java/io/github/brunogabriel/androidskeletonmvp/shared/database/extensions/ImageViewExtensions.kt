package io.github.brunogabriel.androidskeletonmvp.shared.database.extensions

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
 * Created by brunosantos on 2019-10-03.
 */

fun ImageView.loadImage(url: String) {
    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(this, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Picasso.get().load(url).into(this@loadImage)
            }
        })
}