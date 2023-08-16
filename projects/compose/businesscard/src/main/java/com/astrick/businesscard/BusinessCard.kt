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
import com.astrick.core.ui.theme.BlueDark100
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
                .background(Color.BlueDark100)
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

@Composable
fun ContactDetails() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.padding(bottom = Spacing.Large)
    ) {
        DetailRow(
            iconResId = R.drawable.ic_phone_24,
            stringRedId = R.string.phone_number
        )
        DetailRow(
            iconResId = R.drawable.ic_email_24,
            stringRedId = R.string.email
        )
    }
}

@Composable
private fun DetailRow(
    @DrawableRes iconResId: Int,
    @StringRes stringRedId: Int
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(Spacing.Small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = stringResource(id = stringRedId),
            tint = Color.Green100,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = stringResource(id = stringRedId),
            fontSize = FontSize.Normal,
            fontWeight = FontWeight.W400,
            modifier = Modifier
                .padding(start = 22.dp)
                .align(Alignment.CenterVertically)
                .weight(3f)
        )
        Spacer(Modifier.weight(1f))
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
