package com.enukesoftware.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel:ViewModel() {

    private val _quantity=MutableLiveData<Int>()
    val orderQuantity:LiveData<Int> =_quantity

    private val _flavor = MutableLiveData<String>()
    val cupcakeFlavor:LiveData<String> = _flavor

    private  val _pickupDate = MutableLiveData<String>()
    val orderPickupDate :LiveData<String> =_pickupDate

    private  val _price = MutableLiveData<Double>()
    val orderPrice:LiveData<Double> = _price

    val priceString: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }


    val dateOptions = getPickupOptions()

    fun  setQty(orderQty:Int){
        _quantity.value=orderQty
        updatePrice()
    }

    fun setFlavor(desireFlavor:String){
    _flavor.value=desireFlavor
    }

    fun setDate(orderPickUpDat:String){
        _pickupDate.value=orderPickUpDat
        updatePrice()
    }
    
    //
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create a list of dates starting with the current date and the following 3 dates

        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return options
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _pickupDate.value = dateOptions[0]
        _price.value = 0.0
    }

    fun init() {
        resetOrder()
    }

    private fun updatePrice() {
        var calculatedPrice = (orderQuantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _pickupDate.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    fun printLog(){
        Log.i("orderqty",orderQuantity.value.toString())
        Log.i("flavor",cupcakeFlavor.value.toString())
        Log.i("pickupDate",orderPickupDate.value.toString())
        Log.i("price",orderPrice.value.toString())

    }
}