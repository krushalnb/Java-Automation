package com.automation.index;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import com.automation.init.Common;
import com.automation.init.SeleniumInit;

public class HomeIndex extends SeleniumInit {

	@Test(priority = 1)
	void sendPostRequest() throws UnsupportedEncodingException, URISyntaxException, IOException {
		int step = 1;
		int exp = 1;

		testcaseId("TC_01");
		testDescription(" To verify that all payment methods and error message are displaying properly or not");
		log("Step " + step++ + " : Open url:<a>" + testUrl + "</a>");

		Common.pause(1);

		logverification(exp++, "To verify user redirects to 'Peach Payment' home Page successfully.");
		if (homeVerificationPage.verifyPeachPaymentPage()) {
			logStatus("pass");
		} else {
			logStatus("fail");
			assertTrue(false);
		}
		log("Step " + step++ + " : Enter Secret Key.");
		homeIndexPage.enterSecretKey();

		log("Step " + step++ + " : Enter Entity ID.");
		homeIndexPage.enterEntityID();

		log("Step " + step++ + " : Enter Amount.");
		homeIndexPage.enterAmount();

		log("Step " + step++ + " : Enter Currency.");
		homeIndexPage.enterCurrency();

		log("Step " + step++ + " : Enter Payment Type.");
		homeIndexPage.enterPaymentType();

		log("Step " + step++ + " : Enter Shopper Result URL.");
		homeIndexPage.enterShopperResultURL();

		log("Step " + step++ + " : Enter Merchant ID.");
		homeIndexPage.enterMerchantID();

		log("Step " + step++ + " : Enter Nonce.");
		homeIndexPage.enterNonce();

		log("Step " + step++ + " : Click on Send Post Request Button.");
		homeIndexPage.clickOnSendPostRequest();

		logverification(exp++, "To verify all six payment methods are displaying successfully.");
		if (homeVerificationPage.verifyPaymentMethods()) {
			logStatus("pass");

		} else {
			logStatus("fail");
		}

		log("Step " + step++ + " : Refresh the payment method page.");
		homeIndexPage.refreshThePage();

		logverification(exp++, "To verify error message is displaying successfully.");
		if (homeVerificationPage.verifyRefreshPageMessage()) {
			logStatus("pass");
		} else {
			logStatus("fail");
		}

	}
}
