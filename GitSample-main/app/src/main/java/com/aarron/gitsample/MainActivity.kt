package com.aarron.gitsample

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
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
    lateinit var nestedScrollView: NestedScrollView
    private lateinit var progressDialog : ProgressDialog
    lateinit var result : ArrayList<UserBean>
    lateinit var progressBar: ProgressBar
    lateinit var controller : UserAdapter
    var page = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        init()
    }

    private fun init() {
        result = ArrayList()
        persenter = MainPresenter(this)
        showLoading()
        controller = UserAdapter(this.applicationContext)
        recyclerView = findViewById(R.id.recyclerView)
        nestedScrollView = findViewById(R.id.nestView)
        progressBar = findViewById(R.id.progressBar)
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v != null) {
                if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight){
                    page =  result.get(result.size-1).id
                    showProgressBar()
                    persenter.getUsers("",page,result)
                }
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = controller.adapter
        persenter.getUsers("",0,result)
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
                    closeProgressBar()
                    controller.setData(datas)
                    result = datas
                }
            }
        }
    }

    override fun showProgressBar() {
        progressBar.isVisible

    }

    override fun closeProgressBar() {
        progressBar.isGone
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


