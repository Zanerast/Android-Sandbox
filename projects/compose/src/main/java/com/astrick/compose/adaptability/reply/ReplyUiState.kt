package com.astrick.compose.adaptability.reply

import com.astrick.compose.adaptability.reply.data.Email
import com.astrick.compose.adaptability.reply.data.MailboxType
import com.astrick.compose.adaptability.reply.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    val isShowingHomepage: Boolean = true
) {
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}
