package com.fetchrewards.assessment.model

import com.fetchrewards.assessment.model.data.FetchReward
import com.fetchrewards.assessment.model.datasource.FetchRewardsDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Talks to datasource to get the data
 */
class FetchRewardsRepository(
    private val dataSource: FetchRewardsDataSource
) {
    suspend fun fetchRewards(): Flow<List<FetchReward>> {
        return dataSource.fetchRewards()
    }
}