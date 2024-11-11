package com.fetchrewards.assessment.model.datasource

import com.fetchrewards.assessment.model.data.FetchReward
import kotlinx.coroutines.flow.Flow

interface FetchRewardsDataSource {
    suspend fun fetchRewards(): Flow<List<FetchReward>>
}