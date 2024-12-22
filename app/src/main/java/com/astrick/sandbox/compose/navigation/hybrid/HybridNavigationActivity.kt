package com.astrick.sandbox.compose.navigation.hybrid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.astrick.sandbox.app.databinding.ActivityHybridNavBinding

/**
 * [AppCompatActivity] used to demonstrate hybrid navigation between XML & Compose.
 *
 * Note: To launch this go to 'run' drop down menu, then 'Edit configurations'
 * Click the plus symbol in the top left to create a new configuration
 * then click Android App
 * Under 'Launch Options' header find the 'Launch' drop down and change to 'Specified Activity'
 * give it a name and hit apply.
 */
class HybridNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHybridNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHybridNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
