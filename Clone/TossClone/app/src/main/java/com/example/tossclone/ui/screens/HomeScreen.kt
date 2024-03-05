package com.example.tossclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tossclone.R
import androidx.compose.runtime.getValue
import com.example.tossclone.ui.theme.TossCloneTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        TossBankView()
    }
}

@Composable
fun TossBankView() {
    Card(
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = stringResource(id = R.string.toss_bank),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        )

//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(
//            onClick = { /*TODO*/ },
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowForwardIos,
//                tint = Color.LightGray,
//                contentDescription = null
//            )
//        }
    }
}

@Composable
fun TossListView() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TossCloneTheme {
        HomeScreen()
    }
}

