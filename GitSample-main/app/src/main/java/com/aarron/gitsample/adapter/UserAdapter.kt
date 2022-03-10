package com.aarron.gitsample.adapter


import android.content.Context
import android.content.Intent
import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.holder.UserHolder_
import com.aarron.gitsample.ui.DetailActivity
import com.airbnb.epoxy.TypedEpoxyController


class UserAdapter(context: Context) : TypedEpoxyController<ArrayList<UserBean>>() {

     var mContext: Context = context

     override fun buildModels(data: ArrayList<UserBean>?) {
        data?.forEach() {
            UserHolder_()
                .id(it.id)
                .avatar_url(it.avatar_url)
                .login(it.login)
                .admin(it.site_admin)
                .onClickListener { it ->
                    val intent = Intent()
                    intent.setClass(mContext,DetailActivity::class.java)
                    intent.putExtra("login", it)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent)
                }
                .addTo(this)
        }
    }
}


