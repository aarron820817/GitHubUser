package com.aarron.gitsample.persenter

import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.contract.MainContract
import com.aarron.gitsample.model.MainModel


class MainPresenter(var view: MainContract.IMainView) : MainContract.IMainPresenter {

    var model: MainContract.IMainModel = MainModel(this)

    override fun getUsers(id: String, page_size: Int, result: ArrayList<UserBean>) {
        model.getUsers(id, page_size,result)    }

    override fun showData(datas: ArrayList<UserBean>) {
        view.showData(datas)
    }

    override fun closeLoading() {
//        TODO("Not yet implemented")
        view.closeLoading()
    }

}