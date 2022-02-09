package service;

import model.User;

public class UserCreator {
    public static final String TEST_DATA_USER_NAME = "testdata.user.name";
    public static final String TEST_DATA_USER_WRONG_NAME = "testdata.user.wrong.name";
    public static final String TEST_DATA_USER_PASSWORD = "testdata.user.password";
    public static final String TEST_DATA_USER_WRONG_PASSWORD = "testdata.user.wrong.password";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(TEST_DATA_USER_NAME),
                TestDataReader.getTestData(TEST_DATA_USER_PASSWORD));
    }

    public static User withWrongUsername(){
        return new User(TestDataReader.getTestData(TEST_DATA_USER_WRONG_NAME)
                , TestDataReader.getTestData(TEST_DATA_USER_PASSWORD));
    }

    public static User withWrongPassword(){
        return new User(TestDataReader.getTestData(TEST_DATA_USER_NAME)
                , TestDataReader.getTestData(TEST_DATA_USER_WRONG_PASSWORD));
    }
}
