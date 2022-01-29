package model;

public class TestData {
    private String userNameForComparing;
    private String testEmail;
    private String testSubject;
    private String testEmailText;

    public TestData(String userNameForComparing) {
        this.userNameForComparing = userNameForComparing;
    }

    public TestData(String testEmail, String testSubject, String testEmailText) {
        this.testEmail = testEmail;
        this.testSubject = testSubject;
        this.testEmailText = testEmailText;
    }

    public String getUserNameForComparing() {
        return userNameForComparing;
    }

    public String getTestEmail() {
        return testEmail;
    }

    public String getTestSubject() {
        return testSubject;
    }

    public String getTestEmailText() {
        return testEmailText;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "userNameForComparing='" + userNameForComparing + '\'' +
                ", testEmail='" + testEmail + '\'' +
                ", testSubject='" + testSubject + '\'' +
                ", testEmailText='" + testEmailText + '\'' +
                '}';
    }
}
