package com.example.firebasestorage.screens.contact

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebasestorage.LocationActivity
import com.example.firebasestorage.navigation.ROUT_LOGIN
import com.example.firebasestorage.navigation.ROUT_UPLOAD

@Composable
fun ContactScreen(navController: NavHostController){
    val mContext= LocalContext.current

    val message = remember {
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        //TopAppBar
        TopAppBar(title = { Text(text = "ContactPage") },
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
                    mContext.startActivity(Intent(mContext, LocationActivity::class.java))
                }) {
                    Icon(imageVector = Icons.Filled.LocationOn , contentDescription = "Location")
                }
            },
            backgroundColor = Color.Green)

    Column(
        modifier = Modifier
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(
            text = "Send SMS to WhatsApp",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        TextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            placeholder = { Text(text = "Enter your whatsapp phone number")},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))


        TextField(
            value = message.value,
            onValueChange = { message.value = it },
            placeholder = { Text(text = "Enter your message") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                mContext.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            java.lang.String.format(
                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumber.value,
                                message.value
                            )
                        )
                    )
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), colors = ButtonDefaults.buttonColors(Color.Green)
        ) {
            Text(text = "Send Message on WhatsApp", fontSize = 10.sp)
        }
       

    }
}
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview(){
    ContactScreen(rememberNavController())
}