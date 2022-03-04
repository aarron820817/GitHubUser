package com.aarron.gitsample.contract

import com.aarron.gitsample.bean.UserBean


interface MainContract {
    interface IMainView : BaseContract.IBaseView {
        fun closeLoading()
        fun showData(datas :ArrayList<UserBean>)

    }

    interface IMainPresenter : BaseContract.IBasePresenter {
        fun getUsers(id: String, page_size: Int)
        fun showData(datas :ArrayList<UserBean>)
    }

    interface IMainModel : BaseContract.IBaseModel {

        fun getUsers(id: String, page_size: Int)
    }
}