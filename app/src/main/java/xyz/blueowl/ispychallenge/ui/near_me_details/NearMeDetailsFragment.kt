package xyz.blueowl.ispychallenge.ui.near_me_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import xyz.blueowl.ispychallenge.databinding.FragmentNearMeBinding
import xyz.blueowl.ispychallenge.databinding.FragmentNearMeDetailsBinding
import xyz.blueowl.ispychallenge.extensions.requireISpyApplication
import xyz.blueowl.ispychallenge.ui.data_browser.DataBrowserFragmentDirections
import xyz.blueowl.ispychallenge.ui.data_browser.shared.DataBrowserNavState
import xyz.blueowl.ispychallenge.ui.data_browser.shared.UniversalListAdapter
import xyz.blueowl.ispychallenge.ui.near_me.NearMeViewModel
import xyz.blueowl.ispychallenge.ui.near_me.NearMeViewModelFactory
import xyz.blueowl.ispychallenge.ui.safeCollectFlow

class NearMeDetailsFragment : Fragment() {

    private var _binding: FragmentNearMeDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNearMeDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imageUrl = arguments?.getString("challengeImage").orEmpty()
        Picasso.get()
            .load(imageUrl)
            .into(binding.image)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}