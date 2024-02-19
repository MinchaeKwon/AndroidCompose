package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessApp()
                }
            }
        }
    }
}

@Composable
fun BusinessApp() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MainInfo(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 100.dp)
        )

        SubInfo(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        )
    }
}

@Composable
fun MainInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        )

        Text(
            text = stringResource(R.string.card_name),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.card_title),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SubInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        IconText(icon = Icons.Filled.Phone, content = stringResource(R.string.card_phone))
        IconText(icon = Icons.Filled.MailOutline, content = stringResource(R.string.card_email))
        IconText(icon = Icons.Filled.Share, content = stringResource(R.string.card_share))
    }
}

@Composable
fun IconText(icon: ImageVector, content: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier.padding(end = 16.dp))
        Text(text = content,)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    BusinessCardTheme {
        BusinessApp()
    }
}