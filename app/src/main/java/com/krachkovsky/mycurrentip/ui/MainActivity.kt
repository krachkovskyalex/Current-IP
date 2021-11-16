package com.krachkovsky.mycurrentip.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.krachkovsky.mycurrentip.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.resultData.observe(this, {
            ip_text.text = it.ip
        })
        mainViewModel.visibleView.observe(this, {
            if (it) {
                ip_text.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
            } else {
                ip_text.visibility = View.INVISIBLE
                progress_bar.visibility = View.VISIBLE
            }
        })
        mainViewModel.trouble.observe(this, {
            if (it) {
                Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show()
            }
        })

        get_ip_bnt.setOnClickListener {
            mainViewModel.getIP()
        }
    }
}