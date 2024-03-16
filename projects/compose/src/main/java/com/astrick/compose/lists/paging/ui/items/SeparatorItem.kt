package com.astrick.compose.lists.paging.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.compose.lists.paging.ui.UiModel

@Composable
fun SeparatorItem(separatorItem: UiModel.SeparatorItem) {
    Text(separatorItem.description,
         style = MaterialTheme.typography.h6,
         color = Color.White,
         modifier = Modifier
             .fillMaxWidth()
             .background(Color.Gray)
             .padding(8.dp)
    
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSeparator() {
    SeparatorItem(UiModel.SeparatorItem("60.000+ stars"))
}
