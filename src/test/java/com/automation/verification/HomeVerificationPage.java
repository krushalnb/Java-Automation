package com.automation.verification;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.init.AbstractPage;

public class HomeVerificationPage extends AbstractPage {

	public HomeVerificationPage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyPeachPaymentPage() {
		String expected_title = "Hosted Payment Page API Test";
		String actual_title = driver.getTitle();
		assertEquals(actual_title, expected_title);
		log("user is sucessfully redirected to 'Peach Payment' home Page.");
		return true;
	}

	@FindBy(how = How.XPATH, using = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-6']")
	List<WebElement>paymentType;

	public boolean verifyPaymentMethods() {
		boolean status = false;

		String paymentMethod_One = "Card";
		String paymentMethod_Two = "EFT Secure";
		String paymentMethod_Three = "Masterpass";
		String paymentMethod_Four = "Mobicred";
		String paymentMethod_Five = "Ozow";
		String paymentMethod_six = "1ForYou (1Voucher)";
		ArrayList<String> paymentMethodType = new ArrayList<String>();

		for (WebElement payment : paymentType) {
			paymentMethodType.add(payment.getText());
			System.out.println("element: "+ payment.getText());
		} 
		if (paymentMethodType.get(0).equalsIgnoreCase(paymentMethod_One)
				&& paymentMethodType.get(1).equalsIgnoreCase(paymentMethod_Two)
				&& paymentMethodType.get(2).equalsIgnoreCase(paymentMethod_Three)
				&& paymentMethodType.get(3).equalsIgnoreCase(paymentMethod_Four)
				&& paymentMethodType.get(4).equalsIgnoreCase(paymentMethod_Five)
				&& paymentMethodType.get(5).equalsIgnoreCase(paymentMethod_six)) {
			status = true;
			log("All six payment methods are displaying sucessfully.");
		} else {
			status = false;
			log("All six payment methods are not displaying sucessfully.");
		}
		return status;
	}

	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'Oops, Loading Error')]")
	WebElement errorMessage_Xpath;

	public boolean verifyRefreshPageMessage() {
		String expected_errorMessage = "Oops, Loading Error";
		String actual_errorMessage = errorMessage_Xpath.getText();
		assertEquals(actual_errorMessage, expected_errorMessage);
		log("Error message is displaying sucessfully.");
		return true;
	}

}
