package com.astrick.sandbox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.astrick.sandbox.compose.animation.examples.SwipeToDeleteExample

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SwipeToDeleteExample()
        }
    }

}
