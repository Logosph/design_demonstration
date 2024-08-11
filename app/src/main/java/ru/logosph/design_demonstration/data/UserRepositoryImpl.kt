package ru.logosph.design_demonstration.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import ru.logosph.design_demonstration.domain.UserModel
import ru.logosph.design_demonstration.domain.UserRepository

class UserRepositoryImpl : UserRepository {

    override suspend fun register(user: UserModel) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            user.email,
            user.password
        ).await()
    }

}