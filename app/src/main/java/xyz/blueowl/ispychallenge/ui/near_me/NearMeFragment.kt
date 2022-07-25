package xyz.blueowl.ispychallenge.ui.near_me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import xyz.blueowl.ispychallenge.databinding.FragmentNearMeBinding
import xyz.blueowl.ispychallenge.extensions.requireISpyApplication
import xyz.blueowl.ispychallenge.ui.data_browser.DataBrowserFragmentDirections
import xyz.blueowl.ispychallenge.ui.data_browser.DataBrowserViewModel
import xyz.blueowl.ispychallenge.ui.data_browser.shared.DataBrowserNavState
import xyz.blueowl.ispychallenge.ui.data_browser.shared.UniversalListAdapter
import xyz.blueowl.ispychallenge.ui.safeCollectFlow

class NearMeFragment : Fragment() {

    private var _binding: FragmentNearMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = NearMeViewModelFactory(requireISpyApplication().dataRepository)
        val homeViewModel = ViewModelProvider(this, factory)[NearMeViewModel::class.java]

        _binding = FragmentNearMeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = UniversalListAdapter()
        binding.recyclerViewNearMe.adapter = adapter

        safeCollectFlow(homeViewModel.navigationFlow) { navState ->
            val action = when(navState) {
                is DataBrowserNavState.NearMeNavState -> {
                    NearMeFragmentDirections.actionNavigationNearMeToNavigationNearMeDetails(navState.challengeImage)
                }
                else -> throw IllegalArgumentException("Cannot support navState: ${navState::class.java}")
            }
            findNavController().navigate(action)
        }

        safeCollectFlow(homeViewModel.adapterItems) {
            adapter.submitList(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}