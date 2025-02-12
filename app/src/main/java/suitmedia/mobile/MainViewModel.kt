package suitmedia.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _palindromeResult = MutableLiveData<Boolean>()
    val palindromeResult: LiveData<Boolean> = _palindromeResult

    fun checkPalindrome(input: String) {
        val cleanInput = input.replace("\\s".toRegex(),"").lowercase()
        _palindromeResult.value = cleanInput == cleanInput.reversed()
    }
}