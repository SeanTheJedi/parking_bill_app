package com.rg.parking_richmond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rg.parking_richmond.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    var purchaseHistory: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        var currentIntent = this@HistoryActivity.intent

        // checking if current intent has data
        if (currentIntent != null) {
            val receivedData = currentIntent.getStringArrayListExtra("PURCHASE_HISTORY")
            if (receivedData != null) {
                purchaseHistory = receivedData.toMutableList()
            }
        }

        // creating an adapter
        var adapter:HistoryAdaptor = HistoryAdaptor(purchaseHistory)

        // associating that adaptor to the recyclerview
        binding.rvHistory.adapter = adapter

        // assigning a layout manager
        binding.rvHistory.layoutManager = LinearLayoutManager(this)

        // adding a line between each item in the list
        binding.rvHistory.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )


    }
}