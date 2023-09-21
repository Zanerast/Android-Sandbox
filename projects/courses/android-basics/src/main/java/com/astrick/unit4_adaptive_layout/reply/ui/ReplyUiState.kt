package com.astrick.unit4_adaptive_layout.reply.ui

import com.astrick.unit4_adaptive_layout.reply.data.Email
import com.astrick.unit4_adaptive_layout.reply.data.MailboxType
import com.astrick.unit4_adaptive_layout.reply.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    val isShowingHomepage: Boolean = true
) {
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}
