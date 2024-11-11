package com.fetchrewards.assessment.model.datasource.network

import com.fetchrewards.assessment.model.datasource.FetchRewardsDataSource
import com.fetchrewards.assessment.model.datasource.network.api.FetchRewardsApi
import kotlinx.coroutines.flow.flow

/**
 * Talks to API to get the data and converts that into a flow and returns it to caller
 */
class FetchRewardsNetworkDataSource(
    private val fetchRewardsApi: FetchRewardsApi
): FetchRewardsDataSource {
    override suspend fun fetchRewards() = flow {
            emit(fetchRewardsApi.fetchRewards())
    }
}