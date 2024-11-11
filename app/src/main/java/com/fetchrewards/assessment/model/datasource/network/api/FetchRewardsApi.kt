package com.fetchrewards.assessment.model.datasource.network.api

import com.fetchrewards.assessment.model.data.FetchReward
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * Holds the actual logic to get the data from the network, parse it and converts into a POJO
 */
class FetchRewardsApi {
    suspend fun fetchRewards() = withContext(Dispatchers.IO) {
        val url = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json")
        val connection = url.openConnection() as HttpsURLConnection
        val response = connection.inputStream.bufferedReader().readText()
        val responseJSON = JSONArray(response)
        val rewardsList = ArrayList<FetchReward>()
        for(i in 0..<responseJSON.length()) {
            val itemJSON = responseJSON.getJSONObject(i)
            rewardsList.add(
             FetchReward(itemJSON.getInt("id"), itemJSON.getInt("listId"), itemJSON.getString("name"))
            )
        }
        rewardsList
    }
}