package com.fetchrewards.assessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetchrewards.assessment.model.data.FetchReward
import com.fetchrewards.assessment.model.usecase.FetchRewardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FetchRewardsViewModel(
    private val fetchRewardsUseCase: FetchRewardsUseCase
): ViewModel() {
    private val _rewards = MutableStateFlow<List<FetchReward>>(emptyList())
    val rewards: StateFlow<List<FetchReward>> = _rewards
    init {
        viewModelScope.launch {
            try {
                fetchRewardsUseCase.invoke().collect {
                    _rewards.value = it
                }
            } catch (e: Exception) {
                _rewards.value = emptyList()
            }
        }
    }
}