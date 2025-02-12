package suitmedia.mobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import suitmedia.mobile.databinding.ActivityMainBinding
import suitmedia.mobile.screen.screen2.SecondScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            if(binding.etName.text.toString().isEmpty()){
                binding.etName.error = "Name cannot be empty"
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, SecondScreen::class.java)
            intent.putExtra("name", binding.etName.text.toString())
            startActivity(intent)
        }

        binding.btnCheck.setOnClickListener {
            if(binding.etPalindrome.text.toString().isEmpty()){
                binding.etPalindrome.error = "Palindrome cannot be empty"
                Toast.makeText(this, "Palindrome cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val input = binding.etPalindrome.text.toString()
            mainViewModel.checkPalindrome(input)


        }
        observePalindromeResult()
    }

    private fun observePalindromeResult() {
        mainViewModel.palindromeResult.observe(this) { result ->
            Log.d("PalindromeCheck", "Observer called with result: $result")
            val message = if (result) "isPalindrome" else "not palindrome"
            showAlertDialog(message)
        }
    }

    private fun showAlertDialog(message:String){
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Palindrome Result")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss()}
            .show()

    }
}