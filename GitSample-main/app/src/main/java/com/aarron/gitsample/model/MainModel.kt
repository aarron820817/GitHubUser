package com.aarron.gitsample.model

import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.contract.MainContract
import com.aarron.gitsample.retrofit.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class MainModel(var presenter: MainContract.IMainPresenter) : MainContract.IMainModel {

    override fun getUsers(id: String, page_size: Int) {
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = RetrofitManager.mInstance.myAPIService
                var result: ArrayList<UserBean>?
                if (id.isNullOrEmpty()) {
                   var res =apiService.getUsersDefault(page_size).execute()
                   result = res.body() as ArrayList<UserBean>?

                    presenter.showData(result!!)
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            presenter.closeLoading()
        }

    }
}