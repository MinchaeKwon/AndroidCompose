package com.example.tiptimelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptimelayout.ui.theme.TipTimeLayoutTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeLayout()
                }
            }
        }
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
    TipTimeLayoutTheme {
        TipTimeLayout()
    }
}