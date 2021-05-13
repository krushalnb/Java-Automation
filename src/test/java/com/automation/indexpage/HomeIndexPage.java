package com.automation.indexpage;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.init.AbstractPage;
import com.automation.init.Common;
import com.automation.init.TestData;
import com.automation.verification.HomeVerificationPage;

public class HomeIndexPage extends AbstractPage {

	public HomeIndexPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='secret_key']")
	WebElement secretKey_Xpath;
 
	
	public void enterSecretKey() {
		String secretKey = TestData.readPropertiesFile("Resources/config.properties", "secret_key");
		Common.type(secretKey_Xpath, secretKey);
		log("<b> Secret Key : </b>" + secretKey );
		log("Entered Secret Key.");
		
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_entityId']")
	WebElement entityID_Xpath;

	public void enterEntityID() {
		String entityID = TestData.readPropertiesFile("Resources/config.properties", "entity_id");
		Common.type(entityID_Xpath, entityID);
		log("<b> Entity ID : </b>" + entityID );
		log("Entered entity ID.");
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_amount']")
	WebElement amount_Xpath;

	public void enterAmount() {
		String amount = TestData.readPropertiesFile("Resources/config.properties", "amount");
		Common.type(amount_Xpath, amount);
		log("<b> Amount : </b>" + amount );
		log("Entered amount.");
		
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_currency']")
	WebElement currency_Xpath;

	public void enterCurrency() {
		String currency = TestData.readPropertiesFile("Resources/config.properties", "currency");
		Common.type(currency_Xpath, currency); 
		log("<b> Currency : </b>" + currency );
		log("Entered currency.");
		
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_paymentType']")
	WebElement paymentType_Xpath;

	public void enterPaymentType() {
		String paymentType = TestData.readPropertiesFile("Resources/config.properties", "payment_type");
		Common.type(paymentType_Xpath, paymentType);
		log("<b> payment Type : </b>" + paymentType );
		log("Entered payment type.");
		
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_shopperResultUrl']")
	WebElement shopper_result_url_Xpath;

	public void enterShopperResultURL() {
		String shopperResultURL = TestData.readPropertiesFile("Resources/config.properties", "shopper_result_url");
		Common.type(shopper_result_url_Xpath, shopperResultURL);
		log("<b> Shopper Result URL : </b>" + shopperResultURL );
		log("Entered shopper result URL.");
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_merchantTransactionId']")
	WebElement merchant_ID_Xpath;

	public void enterMerchantID() {
		String merchant_id = TestData.readPropertiesFile("Resources/config.properties", "merchant_id");
		Common.type(merchant_ID_Xpath, merchant_id);
		log("<b> Merchant ID : </b>" + merchant_id );
		log("Entered merchant ID.");
	}

	@FindBy(how = How.XPATH, using = "//input[@id='id_nonce']")
	WebElement nonce_Xpath;

	public void enterNonce() {
		String nonceID = TestData.rndmString(8);
		Common.type(nonce_Xpath, nonceID);
		log("<b> Nonce ID : </b>" + nonceID );
		log("Entered nonce ID.");
	}

	@FindBy(how = How.XPATH, using = "//button[@class='btn-primary']")
	WebElement sendPostRequest_Xpath;

	public void clickOnSendPostRequest() {
		Common.clickOn(driver, sendPostRequest_Xpath);
		log("Clicked on send post request button.");
	}

	public void refreshThePage() {
		Common.refresh(driver);
		log("Page refreshed sucessfully.");
	}
}
