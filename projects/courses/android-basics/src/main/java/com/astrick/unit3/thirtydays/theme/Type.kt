package com.astrick.unit3.thirtydays.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.astrick.androidbasicscompose.R

val CabinRegular = Font(
    R.font.cabin_regular,
    weight = FontWeight.Normal
)

val CabinBold = Font(
    R.font.cabin_bold,
    weight = FontWeight.Bold
)

val CabinFontFamily = FontFamily(CabinRegular, CabinBold)

val ThirtyDaysTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = CabinFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    
    displayLarge = TextStyle(
        fontFamily = CabinFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = CabinFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = CabinFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
)
