package com.astrick.compose.adaptability.reply

import androidx.compose.ui.graphics.vector.ImageVector
import com.astrick.compose.adaptability.reply.data.MailboxType

data class NavigationItemContent(
    val mailboxType: MailboxType,
    val icon: ImageVector,
    val text: String
)
