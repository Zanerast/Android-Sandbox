package com.astrick.compose.adaptability.reply

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.astrick.compose.R
import com.astrick.compose.adaptability.reply.data.Email
import com.astrick.compose.adaptability.reply.data.MailboxType

@Composable
fun ReplyDetailsScreen(
    replyUiState: ReplyUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(top = 24.dp)
        ) {
            item {
                if (isFullScreen) {
                    ReplyDetailsScreenTopBar(
                        onBackPressed,
                        replyUiState,
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )
                }
                ReplyEmailDetailsCard(
                    email = replyUiState.currentSelectedEmail,
                    mailboxType = replyUiState.currentMailbox,
                    modifier = if (isFullScreen){
                        Modifier.padding(horizontal = 24.dp)
                    } else {
                        Modifier.padding(end = 24.dp)
                    }
                )
            }
        }
    }
}

@Composable
private fun ReplyDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    replyUiState: ReplyUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                ),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        ) {
            Text(
                text = stringResource(replyUiState.currentSelectedEmail.subject),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReplyEmailDetailsCard(
    email: Email,
    mailboxType: MailboxType,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DetailsScreenHeader(
                email,
                Modifier.fillMaxWidth()
            )
            if (isFullScreen) {
                Spacer(modifier = Modifier.height(12.dp))
            } else {
                Text(
                    text = stringResource(email.subject),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 8.dp
                    ),
                )
            }
            Text(
                text = stringResource(email.body),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            DetailsScreenButtonBar(mailboxType, displayToast)
        }
    }
}

@Composable
private fun DetailsScreenButtonBar(
    mailboxType: MailboxType,
    displayToast: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (mailboxType) {
            MailboxType.Drafts ->
                ActionButton(
                    text = stringResource(id = R.string.continue_composing),
                    onButtonClicked = displayToast
                )
            
            MailboxType.Spam ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 20.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.move_to_inbox),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = stringResource(id = R.string.delete),
                        onButtonClicked = displayToast,
                        containIrreversibleAction = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            
            MailboxType.Sent, MailboxType.Inbox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 20.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp
                    ),
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.reply),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = stringResource(id = R.string.reply_all),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                }
        }
    }
}

@Composable
private fun DetailsScreenHeader(email: Email, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = stringResource(email.sender.firstName) + " "
                    + stringResource(email.sender.lastName),
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = 12.dp,
                    vertical = 4.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(email.sender.firstName),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(email.createdAt),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversibleAction: Boolean = false,
) {
    Box(modifier = modifier) {
        Button(
            onClick = { onButtonClicked(text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                if (containIrreversibleAction) {
                    MaterialTheme.colorScheme.onErrorContainer
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
        ) {
            Text(
                text = text,
                color =
                if (containIrreversibleAction) {
                    MaterialTheme.colorScheme.onError
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}
