package ru.appdevelopers.dal

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import ru.appdevelopers.domain.models.User
import ru.appdevelopers.dal.repository.UserRepository
import ru.appdevelopers.domain.models.AuthType

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {

    /**
     * Пользователь не найден
     * */
    @Test
    fun emptyUserRepositoryTest() {

        // Arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        val repository = UserRepository(context)

        // Act
        val userInRepository = repository.getAuth()

        // Assert
        Assert.assertNull(userInRepository)
    }

    /**
     * Проверяет, правильно ли сохраняется и извлекается
     * */
    @Test
    fun stateUserRepositoryTest() {

        // Arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        val repository = UserRepository(context)
        val user = User(AuthType.GOOGLE, "Иван")

        // Act
        repository.saveAuth(user)
        val userInRepository = repository.getAuth()

        // Assert
        Assert.assertEquals(user.type.value, userInRepository?.type?.value)
        Assert.assertEquals(user.userName, userInRepository?.userName)
    }
}