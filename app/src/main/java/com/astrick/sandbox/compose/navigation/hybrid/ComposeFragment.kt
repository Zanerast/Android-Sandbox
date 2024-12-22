package com.astrick.sandbox.compose.navigation.hybrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.astrick.sandbox.app.R

/**
 * A simple compose [Fragment] to use a destination for hybrid navigation.
 */
class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeScreen(
                    onNavigate = {
                        findNavController().navigate(it)
                    }
                )
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
