package com.example.tossclone.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tossclone.R
import com.example.tossclone.data.Asset
import com.example.tossclone.ui.theme.Shapes
import com.example.tossclone.ui.theme.TossCloneTheme
import java.util.Calendar

@Composable
fun HomeScreen() {
    androidx.compose.material3.Scaffold(
        topBar = {
            HomeTopBar()
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = contentPadding.calculateTopPadding()
                )
        ) {
            Spacer(modifier = Modifier.height(contentPadding.calculateTopPadding()))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                TossBankView()
                AssetListView()
                CurrentMonth()
                MenuView()
                RecommendView()
                BottomButtonView()
                PersonalInfoView()
            }
        }
    }
}

@Composable
private fun HomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(R.drawable.logo_toss),
                colorFilter = ColorFilter.tint(Color.Gray),
                contentDescription = null,
                modifier = modifier
                    .size(30.dp)
            )

            Image(
                painter = painterResource(R.drawable.logo_toss_text),
                colorFilter = ColorFilter.tint(Color.Gray),
                contentDescription = null,
                modifier = modifier
                    .width(60.dp)
                    .height(20.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier.width(20.dp))

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun TossBankView(modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 18.dp,
                    vertical = 20.dp
                )
        ) {
            Text(
                text = stringResource(R.string.toss_bank),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
            )

            Image(
                painter = painterResource(R.drawable.ic_arrow_forward),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.LightGray),
                modifier = modifier
                    .size(16.dp)
            )
        }
    }
}

@Composable
private fun AssetListView(modifier: Modifier = Modifier) {

    val assets = listOf(
        Asset("토스뱅크 통장", 10000, true),
        Asset("입출금통장", 20000, true),
        Asset("저축 · 2개", 100000, false),
        Asset("포인트 · 머니 · 1개", 3000, false),
        Asset("투자 모아보기 · 1개", 500000, false),
    )

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            assets.forEach {
                AssetItem(
                    item = it,
                    modifier = modifier
                )

                Spacer(modifier.height(16.dp))
            }

            MoreView(R.string.asset_more, modifier)
        }
    }
}

@Composable
private fun AssetItem(item: Asset, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = modifier.size(40.dp)
        )

        Spacer(modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Text(
                text = item.name,
                fontSize = 12.sp,
                color = Color.Gray
            )

            Text(
                text = stringResource(R.string.money, "%,d".format(item.money)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier.weight(1f))

        if (item.isButton) {
            TextButton(stringResource(R.string.transfer))
        }
    }
}

@Composable
private fun MoreView(@StringRes text: Int, modifier: Modifier = Modifier) {
    Divider(
        color = Color(0xFFF2F3F5),
        thickness = 1.dp,
        modifier = modifier.fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(text),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )

        Image(
            painter = painterResource(R.drawable.ic_arrow_forward),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = modifier
                .size(16.dp)
        )
    }
}

@Composable
private fun CurrentMonth(modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = modifier
                    .size(40.dp)
            )

            Spacer(modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
            ) {
                Text(
                    text = stringResource(R.string.month_spend),
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = stringResource(R.string.money, "%,d".format(25000)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier.weight(1f))

            TextButton(stringResource(R.string.history))
        }
    }

}

@Composable
private fun MenuView(modifier: Modifier = Modifier) {
    val menuList = listOf(R.string.create_account, R.string.create_card, R.string.get_loan)

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp,
                )
        ) {
            menuList.forEachIndexed { index, resId ->
                Text(
                    text = stringResource(resId),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                if (index != menuList.size - 1) {
                    Divider(
                        color = Color(0xFFF2F3F5),
                        modifier = modifier
                            .width(1.dp)
                            .height(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendView(modifier: Modifier = Modifier) {
    val recommends = listOf(R.string.recommend_ask, R.string.recommend_hospital, R.string.recommend_compare)
    val today = Calendar.getInstance()

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.recommend_date, today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )

            Spacer(modifier.height(4.dp))

            Text(
                text = stringResource(R.string.recommend_title, "권민채"),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier.height(16.dp))

            recommends.forEach {
                RecommendItem(
                    content = stringResource(it),
                    modifier = modifier
                )

                Spacer(modifier.height(16.dp))
            }

            MoreView(R.string.recommend_more, modifier)
        }
    }
}

@Composable
private fun RecommendItem(content: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = modifier
                .size(40.dp)
        )

        Spacer(modifier.width(16.dp))

        Text(
            text = content,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )

        Spacer(modifier.weight(1f))

        Image(
            painter = painterResource(R.drawable.ic_arrow_forward),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.LightGray),
            modifier = modifier
                .size(16.dp)
        )
    }
}

@Composable
private fun BottomButtonView(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconTextButton(Icons.Filled.Settings, stringResource(R.string.screen_setting), modifier.weight(1f))
        Spacer(modifier.width(16.dp))
        IconTextButton(Icons.Filled.Add, stringResource(R.string.add_asset), modifier.weight(1f))
    }
}

@Composable
private fun PersonalInfoView(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.personal_info),
        fontSize = 12.sp,
        color = Color(0xFFB3BAC4),
        modifier = modifier.padding(top = 12.dp, bottom = 44.dp)
    )
}

@Composable
fun TextButton(
    content: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = Shapes.small,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F3F5)),
        modifier = modifier
    ) {
        Text(
            text = content,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF53565B),
            modifier = modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 14.dp
                )
        )
    }
}

@Composable
fun IconTextButton(
    icon: ImageVector,
    content: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EAED)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 24.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray),
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = content,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF505865)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TossCloneTheme {
        HomeScreen()
    }
}

