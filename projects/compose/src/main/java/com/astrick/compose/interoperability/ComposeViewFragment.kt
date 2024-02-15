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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ComposeViewFragment : Fragment() {

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
            setViewCompositionStrategy(ViewCompositionStrategy.Default)
            /*
                developer.android.com/reference/kotlin/androidx/compose/ui/platform/ViewCompositionStrategy
                medium.com/androiddevelopers/viewcompositionstrategy-demystefied-276427152f34

                DisposeOnDetachedFromWindowOrReleasedFromPool (Default):
                - Same as DisposeOnDetachedFromWindow, but with extras
                - Item in a pooling container such as RecyclerView.

                DisposeOnLifecycleDestroyed
                - Use when it is part of a fragments view
                - When compose shares a 1-1 relationship with a known LifecycleOwner
                - Useful for when Compose is used in fragments as part of ViewPager
                - If using Default, the Composition would be disposed prematurely,
                resulting in potential state loss (for example, scroll state).

                DisposeOnViewTreeLifecycleDestroyed
                - Same as DisposeOnLifecycleDestroyed, but lifecycle is not yet known
                - Will be disposed when the Lifecycle owned by the LifecycleOwner returned
                by ViewTreeLifecycleOwner.get of the next window the View is attached to is destroyed.
                - Disposed when the ViewTreeLifecyleOwner of the next window the View is attached to is destroyed.
                - Use when it shares a 1–1 relationship with their closest ViewTreeLifecycleOwner,
                such as a Fragment View.
                - Good for AbstractComposeView

                DisposeOnDetachedFromWindow
                - Has been superseded by DisposeOnDetachedFromWindowOrReleasedFromPool
                - Will be disposed when detaches from window
                    - When the view is going off screen and is no longer visible to the user
                - Examples are:
                    - View is removed from the View hierarchy via ViewGroup.removeView* APIs
                    - View is part of a transition
                    - Containing activity is being destroyed, after onStop but before onDestroy
                - Use when it’s the sole element in the View hierarchy, or in the context
                of a mixed View/Compose screen (not in Fragment)

                Other:
                - What if a ComposeView is an item in a RecyclerView that is within a Fragment?
                    - The immediate ancestor will dictate which strategy to apply — so
                    since the ComposeView is an item in a RecyclerView, you would use
                    DisposeOnDetachedFromWindowOrReleasedFromPool, otherwise,
                    use DisposeOnLifecycleDestroyed.
             */

            setContent {
                Column {
                    Text(
                        text = "Hey",
                        modifier = Modifier.padding(12.dp)
                    )
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
