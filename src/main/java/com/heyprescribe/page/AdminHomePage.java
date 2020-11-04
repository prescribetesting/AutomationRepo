package com.heyprescribe.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.heyprescribe.base.BasePage;
import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class AdminHomePage extends BasePage {

	By btnAdmin = By.xpath("//header/div[1]/div[1]/div[1]/div[2]/div[1]/button[1]/span[1]/*[2]");

	private By lstSubItem(String name) {
		return By.xpath("//span[contains(text(),'" + name + "')]");
	}

	private By txtAvailableDoctor(String name) {
		return By.xpath("// p[contains(text(),'" + name + "')]");
	}

	By lstTodaysAvailableDoctors = By.cssSelector("div.MuiGrid-spacing-xs-2>div>div");

	public boolean userIcon() {
		WebDriverExtension.waitForElementPresence(btnAdmin);
		return WebElementExtension.isDisplayed(btnAdmin);
	}

	public void selectSubItem(String name) {
		WebDriverExtension.waitForElementVisible(btnAdmin);
		WebElementExtension.click(btnAdmin);
		WebElementExtension.jsScroll(lstSubItem(name));
		WebElementExtension.click(lstSubItem(name));
	}

	public String getAvailableDoctor(String name) throws InterruptedException {

		WebDriverExtension.waitForAllElementPresence(lstTodaysAvailableDoctors);
		List<WebElement> allDoctors = WebElementExtension.getElements(lstTodaysAvailableDoctors);
		String docDetail = null;
		for (int i = 0; i < allDoctors.size(); i++) {
			WebElement docDetailEle = allDoctors.get(i);
			docDetail = docDetailEle.getText();
			if (docDetail.contains(name)) {
				break;
			}
		}
		return docDetail;

	}

}
