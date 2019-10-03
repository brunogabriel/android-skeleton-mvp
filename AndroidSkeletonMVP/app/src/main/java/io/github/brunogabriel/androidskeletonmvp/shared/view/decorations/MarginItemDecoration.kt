package io.github.brunogabriel.androidskeletonmvp.shared.view.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by brunosantos on 2019-10-03.
 */
class MarginItemDecoration(
    private val margin: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {
    private val spaceSize = margin / spanCount

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        with(outRect) {
            left = margin - (column * margin) / spaceSize
            right = (column + 1) * spaceSize
            if (position < spanCount) {
                top = margin
            }
            bottom = margin
        }
    }
}