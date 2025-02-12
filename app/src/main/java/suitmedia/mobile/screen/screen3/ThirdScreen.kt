package suitmedia.mobile.screen.screen3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import suitmedia.mobile.databinding.ActivityThirdScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import suitmedia.mobile.core.ui.UserAdapter

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModel()

    private val userAdapter = UserAdapter { user ->
        val intent = Intent().apply {
            putExtra("selected_user", "${user.first_name} ${user.last_name}")
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeUsers()

        binding.swipeToRefresh.setOnRefreshListener {
            userAdapter.refresh()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@ThirdScreen)
        }
    }

    private fun observeUsers() {
        lifecycleScope.launch {
            viewModel.pagedUsers.collect { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }


        userAdapter.addLoadStateListener { loadState ->

            if (loadState.source.refresh is LoadState.Error) {
                Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = if (loadState.source.refresh is LoadState.Loading) View.VISIBLE else View.GONE
            binding.rvUser.visibility = if (loadState.source.refresh is LoadState.Loading) View.GONE else View.VISIBLE

            val isEmptyList = loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    userAdapter.itemCount == 0

            binding.emptyView.visibility = if (isEmptyList) View.VISIBLE else View.GONE
        }
    }
}