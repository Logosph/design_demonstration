package ru.logosph.design_demonstration.domain

class RegisterUseCase {

    companion object {
        suspend fun execute(
            user: UserModel,
            repo: UserRepository
        ) {
            repo.register(user)
        }
    }

}