package com.fetchrewards.assessment

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fetchrewards.assessment.model.FetchRewardsRepository
import com.fetchrewards.assessment.model.data.FetchReward
import com.fetchrewards.assessment.model.datasource.network.FetchRewardsNetworkDataSource
import com.fetchrewards.assessment.model.datasource.network.api.FetchRewardsApi
import com.fetchrewards.assessment.model.usecase.FetchRewardsUseCase
import com.fetchrewards.assessment.ui.theme.FetchRewardsTheme
import com.fetchrewards.assessment.viewmodel.FetchRewardsViewModel

class FetchRewardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rewardsUseCase = FetchRewardsUseCase(FetchRewardsRepository(FetchRewardsNetworkDataSource(
            FetchRewardsApi()
        )))
        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FetchRewardsViewModel(rewardsUseCase) as T
            }
        })[FetchRewardsViewModel::class]
        enableEdgeToEdge()
        setContent {
            FetchRewardsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayListOfItems(
                        viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun DisplayListOfItems(
    viewModel: FetchRewardsViewModel,
    modifier: Modifier
) {
    val rewards by viewModel.rewards.collectAsState()
    LazyColumn(
        modifier = modifier
    ) {
        items(rewards) {
            DisplayItem(
                it
            )
        }
    }
}

@Composable
fun DisplayItem(
    reward: FetchReward
) {
    Column {
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = reward.name.orEmpty())
        Spacer(modifier = Modifier.padding(6.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchRewardsTheme {
        Greeting("Android")
    }
}