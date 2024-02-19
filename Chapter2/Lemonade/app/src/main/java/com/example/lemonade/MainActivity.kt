package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var step by remember { mutableStateOf(1) } // 현재 단계
    var cnt by remember { mutableStateOf(0) } // 레몬 압착 횟수

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (step) {
                1 -> {
                    LemonImageAndText(
                        textResource = R.string.lemon_select,
                        drawableResource = R.drawable.lemon_tree,
                        contentResource = R.string.lemon_tree_content,
                        onImageClick = {
                            step = 2
                            cnt = (2..4).random() // 2 ~ 4 사이의 랜덤 숫자로 압착 횟수 설정
                        }
                    )
                }

                2 -> {
                    LemonImageAndText(
                        textResource = R.string.lemon_sqeeze,
                        drawableResource = R.drawable.lemon_squeeze,
                        contentResource = R.string.lemon_content,
                        onImageClick = {
                            cnt--

                            if (cnt == 0) {
                                step = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonImageAndText(
                        textResource = R.string.lemon_drink,
                        drawableResource = R.drawable.lemon_drink,
                        contentResource = R.string.glass_lemon_content,
                        onImageClick = {
                            step = 4
                        }
                    )
                }

                4 -> {
                    LemonImageAndText(
                        textResource = R.string.lemon_again,
                        drawableResource = R.drawable.lemon_restart,
                        contentResource = R.string.empty_lemon,
                        onImageClick = {
                            step = 1
                        }
                    )
                }
            }
        }
    }

}

// 레모네이드 상태에 따라 버튼 이미지와 텍스트를 다르게 보여줌
@Composable
fun LemonImageAndText(
    textResource: Int,
    drawableResource: Int,
    contentResource: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(drawableResource),
                contentDescription = stringResource(contentResource), // 이미지 설명
                modifier = Modifier
                    // 이미지 크기 설정
                    .width(128.dp)
                    .height(160.dp)
                    .padding(24.dp) // 패딩 설정
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // 버튼과 텍스트 사이에 16dp 간격 추가

        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    LemonadeTheme  {
        LemonApp()
    }
}