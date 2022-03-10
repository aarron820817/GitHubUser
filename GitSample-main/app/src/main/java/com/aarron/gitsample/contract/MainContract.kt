package com.aarron.gitsample.contract

import com.aarron.gitsample.bean.UserBean


interface MainContract {
    interface IMainView  {
        fun closeLoading()
        fun showData(datas :ArrayList<UserBean>)
        fun showProgressBar()
        fun closeProgressBar()
        fun showDialog(isClose: Boolean, title: String, msg: String)
        fun showLoading()
    }

    interface IMainPresenter  {
        fun getUsers(id: String, page_size: Int,result: ArrayList<UserBean>)
        fun showData(datas :ArrayList<UserBean>)
        fun closeLoading()

    }

    interface IMainModel{
        fun getUsers(id: String, page_size: Int, result: ArrayList<UserBean>)
    }
}