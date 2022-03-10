package com.aarron.gitsample.contract

import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.bean.UserDetail

interface DetailContract {
    interface DetailContractView {
        fun showData(data :UserDetail)
    }

    interface DetailContractPresenter  {
        fun getUsers(id: String)
        fun showData(data : UserDetail)
    }

    interface DetailContractModel  {
        fun getUsers(id: String)
    }
}