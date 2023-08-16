package com.astrick.lemonade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.core.ui.theme.BaseComposeTheme
import com.astrick.core.ui.theme.FontSize
import com.astrick.core.ui.theme.LightGreen60
import com.astrick.core.ui.theme.Spacing

@Composable
fun LemonadeApp() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        
        var uiState by remember { mutableStateOf<LemonadeUiState>(LemonadeUiState.LemonTree) }
        
        Surface(
            color = Color.LightGreen60,
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .size(220.dp)
        ) {
            LemonadeButton(uiState) {
                uiState = it
            }
        }
        
        Spacer(modifier = Modifier.size(Spacing.Large))
        
        Text(
            text = stringResource(id = uiState.textResId),
            fontWeight = FontWeight.Normal,
            fontSize = FontSize.Normal,
            color = Color.Black,
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewLemonade() {
    BaseComposeTheme {
        LemonadeApp()
    }
}
