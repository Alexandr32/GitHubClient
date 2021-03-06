package ru.appdevelopers.dal.repository

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import ru.appdevelopers.domain.models.User
import ru.appdevelopers.domain.models.toAuthType
import ru.appdevelopers.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository @Inject constructor(context: Context): IUserRepository {

    companion object {
        const val STORAGE_NAME = "AUTH"

        const val AUTH_TYPE = "AUTH_TYPE"
        const val USERNAME = "USERNAME"
    }

    private var sPref: SharedPreferences =
        context.getSharedPreferences(STORAGE_NAME, Activity.MODE_PRIVATE);
    private var editor: SharedPreferences.Editor = sPref.edit();

    override fun saveAuth(user: User) {
        editor.putInt(AUTH_TYPE, user.type.value)
        editor.putString(USERNAME, user.userName)
        editor.commit();
    }

    override fun getAuth(): User? {
        val authType = sPref.getInt(AUTH_TYPE, 0)

        if(authType == 0) {
            return null;
        }

        val user = sPref.getString(USERNAME, null)
        return User(authType.toAuthType(), user)
    }


}