package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceWithButtonAndImage(modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // 열 내에 있는 하위 요소가 너비에 따라 기기 화면의 중앙에 배치
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var result by remember { mutableStateOf(1) }

        // result 값에 따리 이미지 리소스 변경
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString() // 이미지 설명
        )

        // 이미지와 버튼 사이에 공간 16dp만큼의 공간 추가
        Spacer(modifier = Modifier.height(16.dp))

        // 1 ~ 6사이의 숫자 중에서 랜덤으로 수를 선택
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll)) // 버튼에 들어갈 텍스트 (버튼 이름)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    DiceRollerTheme {
        DiceWithButtonAndImage(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}