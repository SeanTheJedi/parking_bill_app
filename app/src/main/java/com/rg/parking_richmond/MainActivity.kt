package com.rg.parking_richmond

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.rg.parking_richmond.databinding.ActivityMainBinding
import java.util.ArrayList
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    var purchaseHistory: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setSupportActionBar(this.binding.options)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        binding.btnPay.setOnClickListener(this)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_history-> {
                var intent = Intent(this, HistoryActivity::class.java)
                intent.putExtra("PURCHASE_HISTORY", ArrayList(purchaseHistory))
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
       when(v?.id) {
           R.id.btn_pay -> {
               generateReceipt()
           }
       }
    }

    private fun generateReceipt(){

        // retrieving id of checked radio btn
        val selectedRadioButtonID = this.binding.rgroupPkLot.checkedRadioButtonId

        binding.tvRcptTitle.text = "RECEIPT"
        val hoursUI = binding.etHours.text.toString()
        val hours = hoursUI.toInt()
        val lcPlate = binding.etLcPlate.text.toString()
        var price = 0.0
        var rcptInfo = ""
        var parkingLot = ""

        // making calculations based on parking Lot chosen
        when(selectedRadioButtonID) {
            R.id.rbtn_kipling_lot -> {
                parkingLot = "Kipling Parking Lot"
                price = hours * 2.5
                rcptInfo += "$parkingLot" +
                            "\nLicense Plate: $lcPlate" +
                            "\nHours: $hoursUI"
            }

            R.id.rbtn_sherway_lot -> {
                parkingLot = "Sherway Parking Lot"
                price = hours * 3.0
                rcptInfo += "$parkingLot" +
                            "\nLicense Plate: $lcPlate" +
                            "\nHours: $hoursUI"
            }
        }

        // displaying receipt information
        var priceDisplay = "%,.2f".format(Locale.ENGLISH, price)
        this.binding.tvRcptInfo.text = rcptInfo
        this.binding.ltRcptFooter.isVisible = true
        this.binding.tvRcptAmt.text = "Total Paid: $$priceDisplay"

        // adding purchase to purchase list
        purchaseHistory.add("$lcPlate; $parkingLot; $hoursUI hours; $$priceDisplay")

        // clearing input fields
        binding.rgroupPkLot.clearCheck()
        binding.etHours.setText("")
        binding.etLcPlate.setText("")
    }



}