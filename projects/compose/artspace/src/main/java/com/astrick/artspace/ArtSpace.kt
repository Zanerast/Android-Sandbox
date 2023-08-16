package com.astrick.artspace

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.artspace.data.ArtWork
import com.astrick.artspace.data.artList
import com.astrick.core.ui.theme.BaseComposeTheme
import com.astrick.core.ui.theme.BlueDark100
import com.astrick.core.ui.theme.BlueLight100
import com.astrick.core.ui.theme.FontSize
import com.astrick.core.ui.theme.Grey10

@Composable
fun ArtSpace() {
    var position by remember {
        mutableIntStateOf(0)
    }
    val art = artList[position]
    
    Column(
        Modifier.background(Color.Grey10)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        CenterPiece(
            artworkId = art.artId,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 28.dp)
                .height(IntrinsicSize.Min)
                .size(380.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        AuthorDetails(
            authorDetails = art.authorDetails,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp),
        )
        NavButtons(
            onClickPrevious = {
                if (position == 0) {
                    position = artList.size.minus(1)
                } else {
                    position--
                }
            },
            onClickNext = {
                if (position == artList.size.minus(1)) {
                    position = 0
                } else {
                    position++
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
    }
}

@Composable
fun CenterPiece(
    @DrawableRes artworkId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = artworkId),
            contentDescription = "",
            modifier = Modifier.padding(28.dp).fillMaxSize()
        )
    }
}

@Composable
fun AuthorDetails(
    authorDetails: ArtWork.AuthorDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color.BlueLight100,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(15.dp)
            .defaultMinSize(minWidth = 160.dp)
    
    ) {
        Text(
            text = stringResource(id = authorDetails.titleId),
            fontSize = FontSize.Normal
        )
        Row {
            Text(
                text = stringResource(id = authorDetails.authorId),
                fontWeight = FontWeight.Bold,
                fontSize = FontSize.Tiny
            )
            Text(
                text = " (${authorDetails.year})",
                fontSize = FontSize.Tiny
            )
        }
    }
}

@Composable
fun NavButtons(
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        // Previous
        Button(
            onClick = { onClickPrevious() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.BlueDark100)
        ) {
            Text(
                text = stringResource(id = R.string.previous),
                color = Color.White
            )
        }
        // Next
        Button(
            onClick = { onClickNext() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.BlueDark100)
        ) {
            Text(
                text = stringResource(id = R.string.next),
                color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewArtSpace() {
    BaseComposeTheme {
        ArtSpace()
    }
}
