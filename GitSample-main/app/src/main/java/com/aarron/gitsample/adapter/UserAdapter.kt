package com.aarron.gitsample.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.holder.UserHolder_


class UserAdapter : TypedEpoxyController<ArrayList<UserBean>>() {
    override fun buildModels(data: ArrayList<UserBean>?) {
//        TODO("Not yet implemented")
        data?.forEach() {
            UserHolder_()
                .id(it.id)
                .avatar_url(it.avatar_url)
                .login(it.login)
                .addTo(this)
        }
    }
}