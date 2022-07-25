package xyz.blueowl.ispychallenge.ui.data_browser.adapter_items

import android.view.View
import com.squareup.picasso.Picasso
import xyz.blueowl.ispychallenge.R
import xyz.blueowl.ispychallenge.databinding.ItemImageTextChevronBinding
import xyz.blueowl.ispychallenge.databinding.ItemNearMeBinding
import xyz.blueowl.ispychallenge.ui.data_browser.shared.AdapterItem

data class NearMeAdapterItem(
    val challengeId: String,
    val wins: String,
    val ratings: String,
    val distance: String,
    val body: String,
    val author: String,
    val onClick: () -> Unit
) : AdapterItem() {

    override val id: Any = challengeId
    override val layoutResource: Int = R.layout.item_near_me

    override fun bind(view: View) {
        ItemNearMeBinding.bind(view).apply {
            tvWin.text = view.context.getString(R.string._s_wins, wins)
            tvRatings.text = view.context.getString(R.string._s_ratings, ratings)
            tvDistance.text = view.context.getString(R.string._s_distance, distance)
            tvBody.text = body
            tvAuthor.text = view.context.getString(R.string._s_author, author)

            view.setOnClickListener {
                onClick()
            }
        }
    }
}
