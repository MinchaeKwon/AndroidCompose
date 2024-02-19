package com.example.quadrant

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quadrant.ui.theme.QuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrantApp()
                }
            }
        }
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    QuadrantTheme {
        ComposeQuadrantApp()
    }
}