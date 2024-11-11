package com.fetchrewards.assessment.model.usecase

import com.fetchrewards.assessment.model.FetchRewardsRepository
import com.fetchrewards.assessment.model.data.FetchReward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Usecase to filter the elements with name as null
 * or empty and also sorts the data by ItemId and then ny name
 */
class FetchRewardsUseCase(
    private val fetchRewardsRepository: FetchRewardsRepository
) {
    suspend operator fun invoke(): Flow<List<FetchReward>> {
        return fetchRewardsRepository.fetchRewards().map { rewards ->
            rewards.filter { it.name.isNullOrEmpty().not() }
                .sortedWith(compareBy(FetchReward::listId, FetchReward::name))
        }
    }
}