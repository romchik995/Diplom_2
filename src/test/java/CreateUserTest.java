import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import main.BaseApi;
import main.UserApi;
import main.UserAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static main.UserGenerator.*;

public class CreateUserTest extends BaseApi {
    UserApi userApi = new UserApi();
    UserAssert userAssert = new UserAssert();

    @Before
    public void setUp() {
        openUri();
    }

    @Test
    @DisplayName("Чек создание нового пользователя")
    public void successfulRegistrationNewUser() {
        Response status = userApi.registration(getCreateUser());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Пробы создать пользователя с повторяющимся именем")
    public void registrationDuplicateUser() {
        userApi.registration(getCreateUser());
        Response status = userApi.registrationWithoutExtractToken(getCreateUser());
        userAssert.statusForbidden(status);
    }

    @Test
    @DisplayName("Пробы создания пользователя без заполненного email")
    public void registrationUserWithoutEmail() {
        Response status = userApi.registrationWithoutExtractToken(getCreateUserWithoutEmail());
        userAssert.statusForbidden(status);
    }

    @Test
    @DisplayName("Пробы создания пользователя без заполненного Password")
    public void registrationUserWithoutPassword() {
        Response status = userApi.registrationWithoutExtractToken(getCreateUserWithoutPassword());
        userAssert.statusForbidden(status);
    }

    @Test
    @DisplayName("Пробы создания пользователя без заполненного имени пользователя")
    public void registrationUserWithoutUserName() {
        Response status = userApi.registrationWithoutExtractToken(getCreateUserWithoutUserName());
        userAssert.statusForbidden(status);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser();
    }
}
