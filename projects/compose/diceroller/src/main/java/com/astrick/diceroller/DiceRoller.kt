package com.astrick.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.astrick.core.ui.theme.BaseComposeTheme
import com.astrick.core.ui.theme.Spacing

@Composable
fun DiceRoller() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
private fun DiceWithButtonAndImage(modifier: Modifier) {
    var result by remember { mutableIntStateOf(1) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = getDiceImageResource(result)),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(Spacing.Normal))
        Button(onClick = { result = (1..6).random() }) {
            Text(text = stringResource(id = R.string.roll))
        }
        
    }
}

private fun getDiceImageResource(result: Int): Int {
    return when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DiceRollerPreview() {
    BaseComposeTheme {
        DiceRoller()
    }
}