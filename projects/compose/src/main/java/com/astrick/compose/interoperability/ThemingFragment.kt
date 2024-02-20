package com.astrick.compose.interoperability

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.astrick.compose.databinding.FragmentComposeViewBinding
import com.google.android.material.composethemeadapter3.Mdc3Theme

// Gradle requires:

// Material Design
// implementation("com.google.android.material:compose-theme-adapter:1.2.1")
// To use: MdcTheme{}

// Material Design 3
// implementation("com.google.android.material:compose-theme-adapter-3:1.1.1")
// To use: Mdc3Theme {}

// AppCompat
// implementation("com.google.accompanist:accompanist-themeadapter-appcompat:0.34.0")
// To use: AppCompatTheme {}

class ThemingFragment : Fragment() {

    private var _binding: FragmentComposeViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComposeViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle))

            setContent {
                // This approach is no longer recommend as it causes runtime performance issues.
                // For now it is better to have 2 sources of truth for theming, 1 for XML and 1 for compose
                // https://medium.com/androiddevelopers/an-update-on-jetpack-compose-accompanist-libraries-august-2023-ac4cbbf059f1
                // https://developer.android.com/jetpack/compose/designsystems/views-to-compose
                Column {
                    // This sets the font family to cabin_bold which is determined in the themes.xml
                    // and set in the manifest
                    Mdc3Theme() {
                        Text(
                            text = "Hey",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Text(
                        text = "Hey again",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
