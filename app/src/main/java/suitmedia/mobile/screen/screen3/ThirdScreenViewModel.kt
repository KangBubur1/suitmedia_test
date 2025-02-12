package suitmedia.mobile.screen.screen3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import suitmedia.mobile.core.data.remote.Resource
import suitmedia.mobile.core.domain.model.User
import suitmedia.mobile.core.domain.usecase.UserUseCase

class ThirdScreenViewModel(
    userUseCase: UserUseCase
) : ViewModel() {

    val pagedUsers: Flow<PagingData<User>> = userUseCase.getPagedUsers(10)
        .cachedIn(viewModelScope)


}