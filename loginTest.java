package testfiles.tests;

import java.util.HashMap;
import java.util.List; 

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basefiles.pageobjects.BillingAddress;
import basefiles.pageobjects.CartPage;
import basefiles.pageobjects.ProductCatalogue;
import basefiles.pageobjects.conformOrder;
import testfiles.testcomponents.BaseTest;

public class loginTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void EcomerceApplication(HashMap<String, String> input) throws Exception {

		page.loginApplication(input.get("email"), input.get("password"));

		ProductCatalogue pcl = new ProductCatalogue(driver);
		List<WebElement> products = pcl.produstsList();
		pcl.getProductByName("zara coat 3");
		pcl.getAddToCart(input.get("productName"));
		Thread.sleep(1000);

		CartPage cp = new CartPage(driver);
		cp.getCartPage();
		cp.getValiadateProduct(input.get("productName"));

		BillingAddress billing = new BillingAddress(driver);
		billing.getBilligDetails("123", "ind");
		billing.countrySeelection();
		billing.countrySeelection();
		billing.getConformOrder();

		conformOrder co = new conformOrder(driver);
		String acatualResult = co.getConfromOrder();
		AssertJUnit.assertTrue(acatualResult.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("successfully order the product");

	}

	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "njananxmlk@gmail.com");
		map.put("password", "Chinna@5237");
		map.put("productName", "zara coat 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "njananxmlk@gmail.com");
		map1.put("password", "Chinna@5237");
		map1.put("productName", "adidas original");

		return new Object[][] { { map }, { map1 } };
		// another way to define @Dataprovider
//		return new Object[][] { { "njananxmlk@gmail.com", "Chinna@5237", "zara coat 3" },
//				{ "njananxmlk@gmail.com", "Chinna@5237", "adidas original" } };

	}
}