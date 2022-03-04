package com.aarron.gitsample

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aarron.gitsample.adapter.UserAdapter
import com.aarron.gitsample.bean.UserBean
import com.aarron.gitsample.contract.MainContract
import com.aarron.gitsample.persenter.MainPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainContract.IMainView {
    lateinit var persenter: MainContract.IMainPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var dialog : AlertDialog
    private lateinit var progressDialog : ProgressDialog
    var controller = UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        init()
    }

    private fun init() {
        persenter = MainPresenter(this)
        showLoading()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        var controller = UserAdapter()
        recyclerView.adapter = controller.adapter

        persenter.getUsers("", 20)
    }

    override fun closeLoading() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun showData(datas: ArrayList<UserBean>) {
        GlobalScope.launch(Dispatchers.Main) {
            if (!isDestroyed) {
                if(recyclerView.adapter!=null){
                    controller.setData(datas)
                }

            }
        }
    }

    override fun showDialog(isClose: Boolean, title: String, msg: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if (!isDestroyed) {
                    dialog = AlertDialog.Builder(this@MainActivity)
                    .setTitle(title)
                    .setMessage(msg)
                    .setPositiveButton(
                            "確定",
                            DialogInterface.OnClickListener { dialogInterface, i ->
                                if (isClose) {
                                    finish()
                                }
                            }).show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun showLoading() {
            try {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
                if (!isDestroyed) {
                    progressDialog.setTitle("Loading")
                    progressDialog.show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }
}