package com.enukesoftware.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.enukesoftware.myapplication.databinding.FragmentSummaryBinding
import com.enukesoftware.myapplication.viewmodels.OrderViewModel

class SummaryFragment : Fragment() {

    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private lateinit var binding: FragmentSummaryBinding
//    private lateinit var textView: TextView
    private val sharedViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel.printLog()

        binding = FragmentSummaryBinding.inflate(inflater, container, false)



        binding.apply{
            this.viewModel=sharedViewModel
//            summeryFragmnt = this@SummaryFragment
        summerysendButton.setOnClickListener{

//    sharedViewModel.setQty(6)
    sendOrder()
//    quantity
}

        }
        return binding.root
    }


    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    fun sendOrder() {
        sharedViewModel.printLog()
            Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
//        binding = null
    }
}