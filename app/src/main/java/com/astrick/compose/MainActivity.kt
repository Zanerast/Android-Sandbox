package com.astrick.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.astrick.unit4_adaptive_layout.reply.ui.ReplyApp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        setContent {
//            RallyApp()
//            LunchTrayApp()
            val window = calculateWindowSizeClass(activity = this)
            ReplyApp(window.widthSizeClass)
        }
    }
    
}

