package service;

import model.TestData;

public class TestDataCreator{
    private static final String USER_NAME_FOR_COMPARING ="test.user.name.for.comparing";
    private static final String TEST_EMAIL = "test.email";
    private static final String TEST_SUBJECT = "test.subject";
    private static final String TEST_EMAIL_TEXT = "test.email.text";

    public static TestData fromProperty(){
        return new TestData(TestDataReader.getTestData(USER_NAME_FOR_COMPARING));
    }

    public static TestData emailFromProperty(){
        return new TestData(TestDataReader.getTestData(TEST_EMAIL)
                , TestDataReader.getTestData(TEST_SUBJECT)
                , TestDataReader.getTestData(TEST_EMAIL_TEXT));
    }
}
