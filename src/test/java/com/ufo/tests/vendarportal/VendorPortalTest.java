package com.ufo.tests.vendarportal;


import com.ufo.tests.AbstractTest;
import com.ufo.tests.vendarportal.model.VendorPortalTestData;
import com.ufo.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.vendorportal.DashboardPage;
import pages.vendorportal.LoginPage;


public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    public VendorPortalTestData testdata;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.testdata = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
        this.dashboardPage = new DashboardPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testdata.getUsername(),testdata.getPassword());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testdata.getMonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testdata.getAnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testdata.getProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testdata.getAvailableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testdata.getSearchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testdata.getSearchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        // confirm we are login page again after logging out
        Assert.assertTrue(loginPage.isAt());
    }

}
