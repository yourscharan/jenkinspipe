package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutComplete;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class TC4 extends BaseTest {
	@Test
	public void testFullCheckoutFlow() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addProductToCart();
		productsPage.goToCart();

		CartPage cartPage = new CartPage(driver);
		cartPage.checkout();
		Thread.sleep(2000);

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.enterCustomerInfo("Nagendra", "Tester", "524132");
		checkoutPage.finishOrder();

		CheckoutComplete completePage = new CheckoutComplete(driver);
		String actualMsg = completePage.getThankYouMessage();

		Assert.assertEquals(actualMsg, "Thank you for your order!");
	}

}
