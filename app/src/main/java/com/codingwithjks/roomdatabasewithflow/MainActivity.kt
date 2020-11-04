package com.codingwithjks.roomdatabasewithflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.roomdatabasewithflow.Adapter.UserAdapter
import com.codingwithjks.roomdatabasewithflow.Model.User
import com.codingwithjks.roomdatabasewithflow.ViewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter:UserAdapter
    private val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        userViewModel.getUser.observe(this, Observer {response->
            userAdapter.setUser(response as ArrayList<User>)
            Log.d("main", "$response")
        })
        save.setOnClickListener{
            setUserData()
        }
    }

    private fun setUserData() {
        val getName = name.text.toString().trim()
        val getAge = age.text.toString().trim()
         if(!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge))
         {
             val user=User(getName,getAge.toInt())
             userViewModel.insert(user)
         }
        else{
             Toast.makeText(applicationContext,"Please fill all the fields",Toast.LENGTH_SHORT).show()
         }

    }

    private fun initRecyclerView() {
        recyclerView=findViewById(R.id.recyclerView)
        userAdapter= UserAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=userAdapter
        }
    }
}