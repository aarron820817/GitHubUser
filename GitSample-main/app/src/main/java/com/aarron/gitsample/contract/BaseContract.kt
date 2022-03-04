package com.aarron.gitsample.contract


interface BaseContract {

    interface IBaseView {
        fun showDialog(isClose: Boolean, title: String, msg: String)
        fun showLoading()

    }

    interface IBasePresenter {
        fun closeLoading()
    }

    interface IBaseModel {


    }
}