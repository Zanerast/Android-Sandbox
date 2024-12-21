package com.astrick.sandbox.integrations.paging.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.astrick.sandbox.integrations.paging.domain.GithubRepoDetails
import com.astrick.sandbox.integrations.paging.ui.components.GithubDescription
import com.astrick.sandbox.integrations.paging.ui.components.GithubForks
import com.astrick.sandbox.integrations.paging.ui.components.GithubStars
import com.astrick.sandbox.integrations.paging.ui.components.GithubTitle
import com.astrick.sandbox.integrations.paging.ui.components.LanguageUsed

@Composable
fun GithubSearchItem(githubRepoDetails: GithubRepoDetails) {
    ConstraintLayout(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
    ) {
        val starsGuide = createGuidelineFromStart(0.5f)
        val forksGuide = createGuidelineFromStart(0.75f)
        val (fullName, description, language, stars, forks) = createRefs()

        GithubTitle(
            text = githubRepoDetails.fullName,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(fullName) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        GithubDescription(
            description = githubRepoDetails.description,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(description) {
                    top.linkTo(fullName.bottom)
                    start.linkTo(parent.start)
                }
        )

        LanguageUsed(
            languageUsed = githubRepoDetails.language,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(language) {
                    start.linkTo(parent.start)
                    top.linkTo(description.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        GithubStars(
            stars = githubRepoDetails.stars,
            modifier = Modifier
                .constrainAs(stars) {
                    start.linkTo(starsGuide)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(description.bottom)
                }

        )

        GithubForks(
            forks = githubRepoDetails.forks,
            modifier = Modifier
                .constrainAs(forks) {
                    start.linkTo(forksGuide)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(description.bottom)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRepoItem() {
    val githubSearchItemModel = GithubRepoDetails(
        name = "repo",
        fullName = "author/repo",
        description = "An awesome library that you need to do awesome stuff",
        url = "https://example.com",
        stars = 20000,
        forks = 10,
        language = "Kotlin"
    )
    GithubSearchItem(githubRepoDetails = githubSearchItemModel)
}
