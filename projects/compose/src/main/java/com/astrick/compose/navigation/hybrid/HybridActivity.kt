package com.astrick.compose.navigation.hybrid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.astrick.compose.databinding.ActivityHybridBinding

/**
 * To launch this go to 'run' drop down menu, then 'Edit configurations'
 * Click the plus symbol in the top left to create a new configuration
 * then click Android App
 * Under 'Launch Options' header find the 'Launch' drop down and change to 'Specified Activity'
 * give it a name and hit apply.
 * You now have a configuration set up to launch this.
 *
 * Also remember the activity needs to be declared in the manifest to be able to launch
 */
class HybridActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHybridBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityHybridBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    
}
