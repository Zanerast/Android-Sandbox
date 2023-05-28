package com.astrick.businesscard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.core.ui.theme.BaseComposeTheme
import com.astrick.core.ui.theme.DarkBlue100
import com.astrick.core.ui.theme.FontSize
import com.astrick.core.ui.theme.Green100
import com.astrick.core.ui.theme.Green30
import com.astrick.core.ui.theme.Grey100
import com.astrick.core.ui.theme.Spacing

@Composable
fun BusinessCard() {
    Surface(
        color = Color.Green30,
        modifier = Modifier.fillMaxSize()
    ) {
        IconAndName()
        ContactDetails()
    }
}

@Composable
fun IconAndName() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_logo),
            contentDescription = stringResource(id = R.string.cd_android_icon),
            modifier = Modifier
                .size(130.dp)
                .background(Color.DarkBlue100)
        )
        Text(
            text = stringResource(R.string.my_name),
            fontSize = FontSize.Large,
            color = Color.Grey100,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = Spacing.Atomic)
        )
        Text(
            text = stringResource(id = R.string.job),
            fontSize = FontSize.Small,
            fontWeight = FontWeight.Bold,
            color = Color.Green100,
            modifier = Modifier.padding(top = Spacing.Small)
        )
    }
}

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewBusinessCard() {
    BaseComposeTheme {
        BusinessCard()
    }
}
