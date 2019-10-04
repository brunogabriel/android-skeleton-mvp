package io.github.brunogabriel.androidskeletonmvp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.androidskeletonmvp.R
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import io.github.brunogabriel.androidskeletonmvp.shared.view.adapter.PhotoAdapter
import io.github.brunogabriel.androidskeletonmvp.shared.view.decorations.MarginItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.try_again_view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by brunosantos on 2019-10-03.
 */
class MainActivity : AppCompatActivity(), MainContract.View {
    override val presenter: MainContract.Presenter by inject {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        presenter.initialize()
    }

    override fun onDestroy() {
        presenter.onViewWillFinish()
        super.onDestroy()
    }

    override fun showLoading() {
        loading_view.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loading_view.visibility = View.GONE
    }

    override fun showPhotos(photos: List<Photo>) {
        photos_recycler_view.apply {
            visibility = View.VISIBLE
            adapter = PhotoAdapter(photos)
        }
    }

    override fun showError() {
        try_again_view.visibility = View.VISIBLE
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        photos_recycler_view.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(R.dimen.margin_span_size),
                (photos_recycler_view.layoutManager as GridLayoutManager).spanCount
            )
        )
        try_again_button.setOnClickListener {
            try_again_view.visibility = View.GONE
            presenter.onClickAtTryAgain()
        }
    }
}
