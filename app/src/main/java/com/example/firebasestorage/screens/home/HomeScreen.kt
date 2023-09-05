package com.example.firebasestorage.screens.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebasestorage.LocationActivity
import com.example.firebasestorage.navigation.ROUT_ABOUT
import com.example.firebasestorage.navigation.ROUT_LOGIN
import com.example.firebasestorage.navigation.ROUT_SIGNUP

@Composable
fun HomeScreen(navController:NavHostController) {
    val mContext= LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {

        //TopAppBar
        TopAppBar(title = { Text(text = "HomePage") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(ROUT_LOGIN)
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = "arrowback")
                }
            },
            actions = {
                IconButton(onClick = {
                    val shareIntent= Intent(Intent.ACTION_SEND)
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share"))


                }) {
                    Icon(imageVector = Icons.Filled.Share , contentDescription = "share")
                }
                IconButton(onClick = {
                    mContext.startActivity(Intent(mContext,LocationActivity::class.java))
                }) {
                    Icon(imageVector = Icons.Filled.LocationOn , contentDescription = "Location")
                }
            },
            backgroundColor = Color.Green)
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(ROUT_ABOUT)
        }) {
            Text(text = "About")
        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
        HomeScreen(rememberNavController())

}

