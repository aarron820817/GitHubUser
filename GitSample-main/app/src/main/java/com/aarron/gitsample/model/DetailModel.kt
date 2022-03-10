package com.aarron.gitsample.model

import com.aarron.gitsample.contract.DetailContract
import com.aarron.gitsample.contract.MainContract
import com.aarron.gitsample.retrofit.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailModel(var presenter: DetailContract.DetailContractPresenter) : DetailContract.DetailContractModel {
    override fun getUsers(id: String) {
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = RetrofitManager.mInstance.myAPIService

                var res = apiService.getDetailUser(id).execute()
                presenter.showData(res.body())

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}