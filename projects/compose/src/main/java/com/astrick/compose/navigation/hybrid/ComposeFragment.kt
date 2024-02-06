package com.astrick.compose.navigation.hybrid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.astrick.compose.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ComposeFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeScreen() { nav ->
                    findNavController().navigate(nav)
                }
            }
        }
    }
    
    
}

@Composable
private fun ComposeScreen(
    onNavigate: (id: Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Compose Screen")
        Button(onClick = { onNavigate(R.id.action_ComposeFragment_to_XmlFragment) }) {
            Text(text = "Go to XML fragment")
        }
    }
}
