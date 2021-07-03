package com.leology.gittrends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.leology.gittrends.ui.RecyclerViewAdapter
import com.leology.gittrends.value_objects.RepoList
import com.leology.gittrends.viewmodel.GitViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var gitViewModel: GitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recycler_view.adapter = recyclerViewAdapter
    }

    private fun initViewModel() {
        gitViewModel = androidx.lifecycle.ViewModelProvider(this).get(GitViewModel::class.java)
        gitViewModel.getLiveDataObserver().observe(this, object : Observer<RepoList?> {
            override fun onChanged(t: RepoList?) {
                if (t != null) {
                    recyclerViewAdapter.setUpdatedItem(t.items)
                    recyclerViewAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "error in getting data", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
        gitViewModel.makeApiCall()

    }
}