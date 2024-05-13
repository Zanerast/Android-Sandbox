package com.astrick.compose.animation.samples

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.astrick.compose.R
import com.astrick.core.ui.theme.BlueDark100
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun GestureAnimationDemo(
    modifier: Modifier = Modifier
) {
    
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
        var size = IntSize(0,0)
        var isClicked by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .onSizeChanged { size = it }
                .background(Color.Red)
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            val position = awaitPointerEventScope {
                                val pos = awaitFirstDown().position
                                isClicked = true
                                pos
                            }
                            launch { offset.animateTo(position, spring()) }
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.cupcake),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
                    .offset {
                        val xAdjustment = if (isClicked) (size.width / 2) else 0
                        val yAdjustment = if (isClicked) (size.height / 2) else 0
                        val x = offset.value.x.toInt() - xAdjustment
                        val y = offset.value.y.toInt() - yAdjustment
                        IntOffset(x, y)
                    }
                    .padding(8.dp)
            )
            
            Text(
                text = stringResource(R.string.click_to_move_cupcake),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
            
        }
        
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    
        var dismissed by remember { mutableStateOf(false) }
        val context = LocalContext.current
        
        if (dismissed)
            Toast.makeText(context, "Dismissed", Toast.LENGTH_SHORT).show()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Blue)
        ) {
            this@Column.AnimatedVisibility(visible = !dismissed) {
                Text(
                    text = stringResource(id = R.string.swipe_to_dismiss),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .border(2.dp, BlueDark100, RoundedCornerShape(5.dp))
                        .swipeToDelete {
                            dismissed = true
                        }
                )
            }
        }
    }
    
}

fun Modifier.swipeToDelete(onDismissed: () -> Unit): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            val velocityTracker = VelocityTracker()
            while (true) {
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                launch { offsetX.stop() }
                awaitPointerEventScope {
                    horizontalDrag(pointerId) {change ->
                        launch {
                            offsetX.snapTo(offsetX.value + change.positionChange().x)
                        }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                    }
                }
                
                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue > 0.75 * size.width) {
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    } else {
                        offsetX.animateTo(0f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
                    }
                }
            }
        }
    }.offset {
        IntOffset(offsetX.value.toInt(), 0)
    }
}
