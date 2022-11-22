package src.tests.gson;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import src.test_data.model.LoginCred;
import src.test_data.utils.DataObjectBuilder;

public class TestGson {

    public static void main(String[] args) {
        //from Json to Object
        String fileLocation = "/src/tests/java/src/tests/gson/login.json";
        LoginCred[] loginCred = DataObjectBuilder.buildDataObject(fileLocation, LoginCred[].class);
        for (LoginCred cred : loginCred) {
            System.out.println(cred);
        }

        //from Object to Json
        //LoginCred loginCred01 = new LoginCred("Ti", "87654321");
        //System.out.println(gson.toJson(loginCred01));

    }
}
