package com.heyprescribe.page;

import org.openqa.selenium.By;

import com.heyprescribe.base.BasePage;
import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class AdminLogin extends BasePage {

	By txbEmailId = By.cssSelector("input#email");
	By txbPassword = By.cssSelector("input#password");
	By btnLogin = By.cssSelector("button[type='submit']");

	public void login(String url, String emailId, String password) {
		goToUrl(url);
		WebDriverExtension.waitForElementVisible(txbEmailId);
		WebElementExtension.sendKeys(txbEmailId, emailId);
		WebElementExtension.sendKeys(txbPassword, password);
		WebElementExtension.click(btnLogin);
	}

}
