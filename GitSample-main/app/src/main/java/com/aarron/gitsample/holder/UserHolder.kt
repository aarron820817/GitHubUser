package com.aarron.gitsample.holder

import android.view.View
import android.view.ViewDebug
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.aarron.gitsample.R
import com.aarron.gitsample.bean.UserBean


@EpoxyModelClass(layout = R.layout.item_user)
abstract class UserHolder : EpoxyModelWithHolder<UserHolder.Holder>()  {
    @EpoxyAttribute
    var login = ""

    @EpoxyAttribute
    var avatar_url = ""

    @EpoxyAttribute
    var admin = false



    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickListener: (String) -> Unit = {}
    override fun bind(holder: Holder) {
        Glide
            .with(holder.itemView)
            .asBitmap()
            .load(avatar_url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.img_User)

        if(admin){
            Glide
                .with(holder.itemView)
                .asDrawable()
                .load(R.drawable.staff)
                .into(holder.img_Admin)
        }



        holder.tv_name.text = login

        holder.itemView.setOnClickListener { onClickListener(login) }

    }


    class Holder : EpoxyHolder() {

        lateinit var itemView: View
        lateinit var img_User: ImageView
        lateinit var tv_name: TextView
        lateinit var img_Admin : ImageView


        override fun bindView(itemView: View) {
            this.itemView = itemView
            img_User = itemView.findViewById(R.id.img_user)
            tv_name = itemView.findViewById(R.id.tv_name)
            img_Admin = itemView.findViewById(R.id.img_admin)

        }

    }

}