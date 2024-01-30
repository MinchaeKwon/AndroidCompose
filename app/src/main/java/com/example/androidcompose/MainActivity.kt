package com.example.androidcompose

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcompose.ui.theme.AndroidComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GreetingImage(
//                        stringResource(R.string.happy_birthday_text),
//                        getString(R.string.signature_text)
//                    )

                    CompostDescription()
                }
            }
        }
    }
}

/**
 * Compose의 세 가지 기본 표준 레이아웃 요소 : Column(하위 요소 세로로 배치), Row(하위 요소 가로로 배치), Box
 * @Composable : 해당 함수가 데이터를 UI로 변환한다는 것을 Compose 컴파일러에게 알려줌
 * */
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    /**
     * Row를 사용하는 경우
     * verticalAlignment = Alignment.CenterVertically,
     * horizontalArrangement = Arrangement.Center
     * */
    Column (
        // 화면 가운데 정렬
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(end = 16.dp)
//                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.androidparty),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun CompostDescription(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.bg_compose_background),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.compose_tutorial_title),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = stringResource(id = R.string.compose_short_desc),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.compose_long_desc),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun TaskManager(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.ic_task_completed),
            contentDescription = null
        )

        Text(
            text = stringResource(R.string.task_completed),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.task_nice),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column(Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            ComposeInfo(
                bgColor = Color(0xFFEADDFF),
                title = stringResource(R.string.first_title),
                desc = stringResource(R.string.first_desc),
                modifier = Modifier.weight(1f)
            )

            ComposeInfo(
                bgColor = Color(0xFFD0BCFF),
                title = stringResource(R.string.second_title),
                desc = stringResource(R.string.second_desc),
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.weight(1f)) {
            ComposeInfo(
                bgColor = Color(0xFFB69DF8),
                title = stringResource(R.string.third_title),
                desc = stringResource(R.string.third_desc),
                modifier = Modifier.weight(1f)
            )

            ComposeInfo(
                bgColor = Color(0xFFF6EDFF),
                title = stringResource(R.string.fourth_title),
                desc = stringResource(R.string.fourth_desc),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposeInfo(
    bgColor: Color,
    title: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = desc,
            textAlign = TextAlign.Justify
        )
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
    AndroidComposeTheme {
//        GreetingImage(
//            stringResource(R.string.happy_birthday_text),
//            stringResource(R.string.signature_text)
//        )

//        CompostDescription()
//        TaskManager()
        BusinessApp()
    }
}

