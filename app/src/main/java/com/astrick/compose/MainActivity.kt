package com.astrick.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.astrick.compose.animation.AnimatedVisibilitySample

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        setContent {
//            RallyApp()
//            LunchTrayApp()
//            val window = calculateWindowSizeClass(activity = this)
//            ReplyApp(window.widthSizeClass)
//            UnScramble()
            AnimatedVisibilitySample()
        }
    }
    
}

