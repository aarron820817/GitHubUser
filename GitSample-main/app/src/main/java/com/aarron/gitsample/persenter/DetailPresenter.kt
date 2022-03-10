package com.aarron.gitsample.persenter

import com.aarron.gitsample.bean.UserDetail
import com.aarron.gitsample.contract.DetailContract
import com.aarron.gitsample.contract.MainContract
import com.aarron.gitsample.model.DetailModel
import com.aarron.gitsample.model.MainModel

class DetailPresenter(var view: DetailContract.DetailContractView) : DetailContract.DetailContractPresenter {
    var model: DetailContract.DetailContractModel = DetailModel(this)

    override fun getUsers(id: String) {
        model.getUsers(id)
    }

    override fun showData(data: UserDetail) {
        view.showData(data)
    }


}