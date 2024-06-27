package com.enukesoftware.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.enukesoftware.myapplication.databinding.FragmentSelectFlavorBinding
import com.enukesoftware.myapplication.viewmodels.OrderViewModel


class SelectFlavor : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private lateinit var binding : FragmentSelectFlavorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_select_flavor, container, false)
            binding = FragmentSelectFlavorBinding.inflate(inflater,container,false)

            binding.apply {
                viewModel = sharedViewModel
                flavorFragment = this@SelectFlavor
            }
        return binding.root
    }

        fun goToNextScreen(){
            sharedViewModel. printLog()

            findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
        }
}