package ru.logosph.design_demonstration.presenter.fragments.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.logosph.design_demonstration.data.UserRepositoryImpl
import ru.logosph.design_demonstration.domain.RegisterUseCase
import ru.logosph.design_demonstration.domain.UserModel

class MainViewModel : ViewModel() {

    suspend fun register(
        email: String,
        password: String
    ) : Flow<State> = flow {

        Thread.sleep(2000)

        emit(State.LOADING)

        try {
            val repo = UserRepositoryImpl()

            RegisterUseCase.execute(
                UserModel(
                    email = email,
                    password = password
                ),
                repo = repo
            )

            emit(State.SUCCESS)
        } catch (e: Exception) {
            emit(State.ERROR)
        }
    }

}

enum class State {
    LOADING,
    SUCCESS,
    ERROR
}