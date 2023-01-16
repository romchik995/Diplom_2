import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import main.BaseApi;
import main.UserApi;
import main.UserAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static main.UserGenerator.*;

public class RenameUserTest extends BaseApi {
    UserApi userApi = new UserApi();
    UserAssert userAssert = new UserAssert();

    @Before
    public void setUp() {
        openUri();
        userApi.registration(getCreateUser());
    }

    @Test
    @DisplayName("Чек изменения Email авторизованного пользователя")
    public void renameEmailWithAuthorization() {
        Response status = userApi.rename("email", getNewEmail(), userApi.getToken());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Чек изменения имени авторизованного пользователя")
    public void renameNameWithAuthorization() {
        Response status = userApi.rename("name", getNewName(), userApi.getToken());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Чек изменения Email неавторизованного пользователя")
    public void renameEmailNonAuthorization() {
        Response status = userApi.rename("email", getNewEmail(), "");
        userAssert.statusUnauthorized(status);
    }

    @Test
    @DisplayName("Чек изменения имени неавторизованного пользователя")
    public void renameNameNonAuthorization() {
        Response status = userApi.rename("name", getNewName(), "");
        userAssert.statusUnauthorized(status);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser();
    }
}