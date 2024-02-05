package com.example.androidcompose

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.compose.material3.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.text.NumberFormat
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

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

//                    CompostDescription()

//                    DiceWithButtonAndImage(modifier = Modifier
//                        .fillMaxSize()
//                        .wrapContentSize(Alignment.Center))

//                    LemonApp()

                    TipTimeLayout()
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
                        text = stringResource(R.string.lemonade_appname),
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

// UI 상태를 상위 TipTimeLayout()으로 호이스팅 함 -> TipTimeLayout()이 상태(amountInput) 소유자
@Composable
fun TipTimeLayout() {
    // 상태를 TipTimeLayout 함수로 호이스팅 함

    /* mutableStateOf로 만들어져 변경 가능한 상태이며 리컴포지션이 예약됨
    remember를 사용하므로 변경사항은 리컴포지션이 끝나도 초기화 되지 않고 유지된다 */
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    var roundUp by remember { mutableStateOf(false) }

    val tip = calculateTip(amount, tipPercent, roundUp)


    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )

        // 상태 호이스팅을 했기 때문에 해당 함수 부분도 업데이트 해줌
        // EditNumberField가 스테이트리스가 됨
        EditNumberField(
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions.Default.copy( //  Next 작업 버튼 : 사용자가 현재 입력을 완료했고 다음 텍스트 상자로 이동하려고 함을 나타냄
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = amountInput,
            onValueChange = { amountInput = it }, // 입력값이 it에 들어옴
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        // 팁 퍼센트 입력 필드
        EditNumberField(
            label = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions.Default.copy( // Done 작업 버튼 : 사용자가 입력을 완료했음을 나타냄
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = tipInput,
            onValueChange = { tipInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        // 팁 반올림 스위치
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // 팁 금액 표시
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(150.dp))
    }
}

// 15% 팁 금액을 계산
private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount

    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    return NumberFormat.getCurrencyInstance().format(tip)
}

// TextField를 만들 때 재사용 가능
// (String 값을 입력으로 사용하고 반환 값이 없는 함수로 정의)
@Composable
fun EditNumberField(
    @StringRes label: Int, // 라벨 추가
    keyboardOptions: KeyboardOptions, // 입력 필드에 따라 키보드 옵션을 다르게 설정하기 위해 변수로 받음
    // 상태 호이스팅을 위해 추가
    value: String,
    onValueChange: (String) -> Unit, // TextField 컴포저블에 전달된 onValueChange 콜백으로 사용
    modifier: Modifier = Modifier
) {
    TextField(
        value = value, // 입력창에 표시되는 값
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(stringResource(label)) }, // 타입 구분을 위해 변수로 얻은 id를 넣음
        keyboardOptions = keyboardOptions, // 키보드 타입 설정
        modifier = modifier
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))

        Switch(
            // Switch 컴포저블을 화면 끝에 맞춤
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp, // 스위치 선택 여부
            onCheckedChange = onRoundUpChanged, // 스위치 값이 변경될 때 호출되는 콜백
        )
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
//        BusinessApp()

//        DiceWithButtonAndImage(modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center))

//        LemonApp()

        TipTimeLayout()
    }
}

