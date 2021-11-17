package com.krachkovsky.mycurrentip.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.krachkovsky.mycurrentip.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Click on the button to get IP
        get_ip_bnt.setOnClickListener {
            mainViewModel.getIP()
        }
        // Observer to write IP
        mainViewModel.resultData.observe(this, {
            ip_text.text = it.ip
        })
        // Observer to assign visibility for view
        mainViewModel.visibleView.observe(this, {
            if (it) {
                ip_text.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
            } else {
                ip_text.visibility = View.INVISIBLE
                progress_bar.visibility = View.VISIBLE
            }
        })
        // Observer to message if we have problem
        mainViewModel.trouble.observe(this, {
            if (it) {
                Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show()
            }
        })

    }

}