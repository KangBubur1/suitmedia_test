package suitmedia.mobile.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import suitmedia.mobile.MainViewModel
import suitmedia.mobile.screen.screen3.ThirdScreenViewModel
import suitmedia.mobile.core.domain.usecase.UserInteractor
import suitmedia.mobile.core.domain.usecase.UserUseCase
import suitmedia.mobile.screen.screen2.SecondScreenViewModel


val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get())}
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SecondScreenViewModel() }
    viewModel { ThirdScreenViewModel(get()) }
}
