package com.astrick.compose.interoperability

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.astrick.compose.databinding.ActivityComposeViewBinding

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
class ComposeViewActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityComposeViewBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityComposeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    
}
