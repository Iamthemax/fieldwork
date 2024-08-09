package com.sipl.aamdarparivar.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sipl.aamdarparivar.R
import com.sipl.aamdarparivar.database.AppDatabase
import com.sipl.aamdarparivar.database.entity.Users
import com.sipl.aamdarparivar.databinding.ActivitySyncUsersBinding
import com.sipl.aamdarparivar.repository.UserLocalRepository
import com.sipl.aamdarparivar.ui.adapters.UsersListAdapter
import com.sipl.aamdarparivar.viewmodel.UserLocalViewModel
import com.sipl.aamdarparivar.viewmodel.UserLocalViewModelFactory
import kotlinx.coroutines.launch

class SyncUsersActivity : AppCompatActivity() {
    lateinit var binding: ActivitySyncUsersBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: UsersListAdapter
    lateinit var userLocalViewModel: UserLocalViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySyncUsersBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val usersDao = AppDatabase.getDatabase(this).userDao()
        val userLocalRepository = UserLocalRepository(usersDao)
        userLocalViewModel =
            ViewModelProvider.create(this, UserLocalViewModelFactory(userLocalRepository))
                .get(UserLocalViewModel::class.java)

        lifecycleScope.launch {
           usersDao.getAllUserss().observeForever { data ->
                adapter = UsersListAdapter(data as ArrayList<Users>)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }
}