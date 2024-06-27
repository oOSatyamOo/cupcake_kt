package com.enukesoftware.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.enukesoftware.myapplication.databinding.FragmentPickupBinding
import com.enukesoftware.myapplication.viewmodels.OrderViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [PickupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickupFragment : Fragment() {

    // Binding object instance corresponding to the fragment_pickup.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private lateinit var binding: FragmentPickupBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPickupBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel=sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            pickupFragmnt = this@PickupFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val viewModel = sharedViewModel
            pickupFragmnt=this@PickupFragment
       }
    }

    /**
     * Navigate to the next screen to see the order summary.
     */
    fun goToNextScreen() {
//        Toast.makeText(activity, "Next", Toast.LENGTH_SHORT).show()
        sharedViewModel.printLog()
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()

    }
}