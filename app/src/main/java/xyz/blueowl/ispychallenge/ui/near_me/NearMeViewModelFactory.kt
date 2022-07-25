package xyz.blueowl.ispychallenge.ui.near_me

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.blueowl.ispychallenge.data.repository.DataRepository
import xyz.blueowl.ispychallenge.ui.data_browser.DataBrowserViewModel
import xyz.blueowl.ispychallenge.ui.new_challenge.NewChallengeViewModel

class NearMeViewModelFactory(
    private val dataRepository: DataRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DataBrowserViewModel::class.java) -> {
                return DataBrowserViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(NearMeViewModel::class.java) -> {
                return NearMeViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(NewChallengeViewModel::class.java) -> {
                return NewChallengeViewModel() as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}