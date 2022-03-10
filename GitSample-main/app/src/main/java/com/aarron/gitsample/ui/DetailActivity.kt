package com.aarron.gitsample.ui

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aarron.gitsample.R
import com.aarron.gitsample.bean.UserDetail
import com.aarron.gitsample.contract.DetailContract
import com.aarron.gitsample.persenter.DetailPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity(),DetailContract.DetailContractView {
    lateinit var img_user: ImageView
    lateinit var tv_username: TextView
    lateinit var tv_person_name: TextView
    lateinit var tv_location: TextView
    lateinit var tv_link: TextView
    lateinit var login: String
    lateinit var img_cancel: ImageView
    lateinit var detailPresenter: DetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        login = if (savedInstanceState != null) {
            savedInstanceState.getString("login")!!
        } else {
            intent.getStringExtra("login")!!
        }
        if (login.isEmpty()) {
            finish()
        }
        init()
    }

    private fun init() {
        detailPresenter = DetailPresenter(this)
        detailPresenter.getUsers(login)
        img_user = findViewById(R.id.img_user)
        tv_username = findViewById(R.id.tv_username)
        tv_person_name = findViewById(R.id.tv_person_name)
        tv_location = findViewById(R.id.tv_location)
        tv_link = findViewById(R.id.tv_link)
        img_cancel = findViewById(R.id.img_cancel)

        img_cancel.setOnClickListener{
            finish()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("login", login)
    }

    override fun showData(data: UserDetail) {
        runOnUiThread(Runnable {
            Glide
                .with(this)
                .load(data.avatar_url)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(img_user)
            tv_username.text = data.name
            tv_person_name.text = data.login
            tv_location.text = data.location
            tv_link.text = data.blog
        })
    }

}