package com.astrick.compose.navigation.hybrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.astrick.compose.R
import com.astrick.compose.databinding.FragmentXmlBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class XmlFragment : Fragment() {
    
    private var _binding: FragmentXmlBinding? = null
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentXmlBinding.inflate(inflater, container, false)
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
