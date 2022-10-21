package com.rio.project12.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rio.project12.R

/**
 * parent View of all the fragments
 */
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}