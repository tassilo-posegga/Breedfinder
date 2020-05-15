package eu.posegga.template.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import eu.posegga.template.R


class SpacesItemDecoration(
    private val columns: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val defaultMargin = view.resources.getDimension(R.dimen.default_margin).toInt()
        val smallMargin = view.resources.getDimension(R.dimen.small_margin).toInt()

        outRect.bottom = defaultMargin

        val index = parent.getChildLayoutPosition(view)
        // Add top margin only for the first items to avoid double space between items
        if (index < columns) {
            outRect.top = defaultMargin
        } else {
            outRect.top = 0
        }

        // avoid double margin in middle
        if (index % columns == 0) {
            outRect.left = defaultMargin
        } else {
            outRect.left = smallMargin
        }

        if (index % columns == columns - 1) {
            outRect.right = defaultMargin
        } else {
            outRect.right = smallMargin
        }
    }
}