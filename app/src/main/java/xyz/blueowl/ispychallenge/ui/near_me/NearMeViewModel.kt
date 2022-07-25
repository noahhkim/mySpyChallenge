package xyz.blueowl.ispychallenge.ui.near_me

import android.location.Location
import xyz.blueowl.ispychallenge.data.repository.DataRepository
import xyz.blueowl.ispychallenge.ui.data_browser.adapter_items.NearMeAdapterItem
import xyz.blueowl.ispychallenge.ui.data_browser.shared.BaseDataBrowserViewModel
import xyz.blueowl.ispychallenge.ui.data_browser.shared.DataBrowserNavState

class NearMeViewModel(
    dataRepository: DataRepository
) : BaseDataBrowserViewModel() {

    init {
        val mockLocation = Location("").apply {
            latitude = 37.7904504
            longitude = -122.4033477
        }
        val challenges = dataRepository.getAllChallenges()
            .map { challenge ->
                val locationToDevice = Location("")
                    .apply {
                        latitude = challenge.latitude
                        longitude = challenge.longitude
                    }
                    .run { distanceTo(mockLocation) }
                NearMeAdapterItem(
                    challengeId = challenge.id,
                    wins = challenge.totalWins(),
                    ratings = challenge.getAverageRating().formatDouble(),
                    distance = locationToDevice.formatFloat(),
                    body = challenge.hint,
                    author = dataRepository.getUserByUserId(challenge.userId)?.username.orEmpty(),
                    onClick = { onUserClick(challenge.photoImageName) }
                )
            }.sortedByDescending { item ->
                item.ratings
            }

        _adapterItems.tryEmit(challenges)
    }

    private val onUserClick: (userId: String) -> Unit = {
        _navigationFlow.tryEmit(DataBrowserNavState.NearMeNavState(it))
    }
}

fun Float.formatFloat() =
    String.format("%.2f", this)

fun Double.formatDouble() =
    String.format("%.2f", this)