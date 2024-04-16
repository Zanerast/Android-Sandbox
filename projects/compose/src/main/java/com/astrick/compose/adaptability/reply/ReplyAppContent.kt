package com.astrick.compose.adaptability.reply

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.astrick.compose.R
import com.astrick.compose.adaptability.reply.data.Email
import com.astrick.compose.adaptability.reply.data.MailboxType

@Composable
fun ReplyAppContent(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyUiState: ReplyUiState,
    onTabPressed: ((MailboxType) -> Unit),
    onEmailCardPressed: (Email) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == ReplyNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                ReplyNavigationRail(
                    currentTab = replyUiState.currentMailbox,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .testTag(navigationRailContentDescription)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == ReplyContentType.LIST_AND_DETAIL) {
                    ReplyListAndDetailContent(
                        replyUiState = replyUiState,
                        onEmailCardPressed = onEmailCardPressed,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    ReplyListOnlyContent(
                        replyUiState = replyUiState,
                        onEmailCardPressed = onEmailCardPressed,
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                horizontal = 8.dp
                            )
                    )
                }
                AnimatedVisibility(
                    visible = navigationType == ReplyNavigationType.BOTTOM_NAVIGATION
                ) {
                    ReplyBottomNavigationBar(
                        currentTab = replyUiState.currentMailbox,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(stringResource(id = R.string.navigation_bottom))
                    )
                }
            }
        }
    }
}

@Composable
private fun ReplyBottomNavigationBar(
    currentTab: MailboxType,
    onTabPressed: ((MailboxType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.mailboxType,
                onClick = { onTabPressed(navItem.mailboxType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}
@Composable
private fun ReplyNavigationRail(
    currentTab: MailboxType,
    onTabPressed: ((MailboxType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.mailboxType,
                onClick = { onTabPressed(navItem.mailboxType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}
