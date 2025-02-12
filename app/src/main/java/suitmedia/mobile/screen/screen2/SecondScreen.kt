package suitmedia.mobile.screen.screen2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import suitmedia.mobile.R
import suitmedia.mobile.databinding.ActivitySecondScreenBinding
import suitmedia.mobile.screen.screen3.ThirdScreen

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel: SecondScreenViewModel by viewModel()

    private val userResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedUser = result.data?.getStringExtra("selected_user")
            viewModel.setUsername(selectedUser ?: getString(R.string.selected_user_name))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        viewModel.setName(name ?: "")


        viewModel.name.observe(this) { name ->
            binding.tvName.text = name
        }
        viewModel.username.observe(this) { selectedUser ->
            binding.tvSelectedUser.text = selectedUser
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            userResult.launch(intent)
        }
    }
}