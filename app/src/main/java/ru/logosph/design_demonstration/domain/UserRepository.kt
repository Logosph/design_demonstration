package ru.logosph.design_demonstration.domain

interface UserRepository {

    suspend fun register(user: UserModel)

}