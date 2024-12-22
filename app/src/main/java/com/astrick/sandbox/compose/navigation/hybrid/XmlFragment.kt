package com.astrick.sandbox.compose.navigation.hybrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.astrick.sandbox.app.R
import com.astrick.sandbox.app.databinding.FragmentHybridNavBinding

/**
 * A simple XML [Fragment] to use a destination for hybrid navigation.
 */
class XmlFragment : Fragment() {

    private var _binding: FragmentHybridNavBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHybridNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToCompose.setOnClickListener {
            findNavController().navigate(R.id.action_XmlFragment_to_ComposeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
