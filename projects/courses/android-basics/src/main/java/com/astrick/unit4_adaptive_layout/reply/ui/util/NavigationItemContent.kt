package com.astrick.unit4_adaptive_layout.reply.ui.util

import androidx.compose.ui.graphics.vector.ImageVector
import com.astrick.unit4_adaptive_layout.reply.data.MailboxType

data class NavigationItemContent(
    val mailboxType: MailboxType,
    val icon: ImageVector,
    val text: String
)
