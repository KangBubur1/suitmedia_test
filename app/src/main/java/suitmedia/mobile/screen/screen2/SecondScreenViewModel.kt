package suitmedia.mobile.screen.screen2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondScreenViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    fun setName(name: String) {
        _name.value = name
    }

    fun setUsername(username: String) {
        _username.value = username
    }

}