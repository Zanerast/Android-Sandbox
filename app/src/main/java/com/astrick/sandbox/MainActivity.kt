package com.astrick.sandbox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.astrick.compose.animation.AnimatedVisibilitySample

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        setContent {
            AnimatedVisibilitySample()
        }
    }
    
}

