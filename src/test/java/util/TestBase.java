package util;

import io.github.cozyloon.EzConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class TestBase {
    public static SoftAssert softAssert;
    public static RestAssuredWrapper restAssuredWrapper;

    @BeforeTest
    public void beforeTest() {
        EzConfig.logINFO("TestClass Running" + this.getClass().toString());
        this.softAssert = new SoftAssert();
        this.restAssuredWrapper = new RestAssuredWrapper();
    }
}
