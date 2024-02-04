package com.astrick.compose.navigation

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.astrick.compose.R

private const val DESTINATION_HOME = "home"
private const val DESTINATION_NAME = "route_name"

@Composable
fun NavWithArguments() {
    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = DESTINATION_HOME) {
        composable(DESTINATION_HOME) {
            HomeScreen(navController)
        }
        composable(
            route = "$DESTINATION_NAME/{name}/{imageId}?comment={comment}",
            arguments = listOf(
                // strings do not require declaration as it is the default
                navArgument("imageId") { type = NavType.IntType },
                navArgument("comment") { nullable = true }
            )
        ) {
            val args = it.arguments
            val name = args?.getString("name") ?: "Default"
            val imageId = args?.getInt("imageId") ?: R.drawable.affirmations1
            val comment = args?.getString("comment")
            NameScreen(imageId, name, comment = comment)
        }
    }
    
}

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        
        var selectedImage by remember { mutableIntStateOf(R.drawable.affirmations1) }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            SelectableImage(
                image = R.drawable.affirmations1,
                isSelected = selectedImage == R.drawable.affirmations1
            ) {
                selectedImage = R.drawable.affirmations1
            }
            SelectableImage(
                image = R.drawable.affirmations2,
                isSelected = selectedImage == R.drawable.affirmations2
            ) {
                selectedImage = R.drawable.affirmations2
            }
            SelectableImage(
                image = R.drawable.affirmations3,
                isSelected = selectedImage == R.drawable.affirmations3
            ) {
                selectedImage = R.drawable.affirmations3
            }
        }
        
        // Name
        val (name, onNameChange) = remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = {
                Text(text = "Name")
            }
        )
        
        val (comment, onCommentChange) = remember { mutableStateOf("") }
        TextField(
            value = comment,
            onValueChange = onCommentChange,
            label = {
                Text(text = "comment")
            }
        )
        
        val context = LocalContext.current
        Button(
            onClick = {
                if (name.isEmpty()) {
                    Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate(
                        "$DESTINATION_NAME/$name/$selectedImage?comment=$comment"
                    )
                }
            }
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
private fun SelectableImage(
    @DrawableRes image: Int,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
            .clickable {
                onClicked()
            }
            .border(
                1.dp,
                if (isSelected) Color.Yellow else Color.Transparent
            )
    )
}

@Composable
fun NameScreen(
    @DrawableRes imageId: Int,
    name: String,
    modifier: Modifier = Modifier,
    comment: String? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Image(painter = painterResource(id = imageId), contentDescription = null)
        Text(
            text = "Hello, $name",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        
        if (!comment.isNullOrEmpty()) {
            Text(
                text = "This is your comment: $comment",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
