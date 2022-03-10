package com.aarron.gitsample.model

import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.contract.MainContract
import com.aarron.gitsample.retrofit.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainModel(var presenter: MainContract.IMainPresenter) : MainContract.IMainModel {

    override fun getUsers(id: String, page_size: Int, result: ArrayList<UserBean>) {
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = RetrofitManager.mInstance.myAPIService
                if(id.isEmpty()){
                    var res = apiService.getUsers(page_size, 20).execute()
                    result.addAll(res.body().toList())
                    presenter.showData(result)
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            presenter.closeLoading()
        }

    }


}