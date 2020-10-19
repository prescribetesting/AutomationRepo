package com.heyprescribe.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.heyprescribe.base.BasePage;
import com.heyprescribe.utill.Screenshort;

public class ExtentRepoertListeners extends BasePage implements ITestListener, ISuiteListener {
	WebDriver driver;

	public static ExtentReports extent;

	public static ExtentReports startReport() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/TestReport.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Environment", "QA");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setReportName("Smoke Testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		return extent;
	}

	public void onFinish(ITestContext arg0) {
	}

	public void onStart(ITestContext arg0) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void onTestFailure(ITestResult arg0) {
		Screenshort screenshot = new Screenshort(getDriver());

		String screenShotPath = null;
		try {
			screenShotPath = screenshot.captureScreenshot(arg0.getName());
		} catch (IOException e) {

			e.printStackTrace();
		}
		getTest().log(Status.FAIL,
				MarkupHelper.createLabel(arg0.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
		getTest().fail(arg0.getThrowable());

		try {
			getTest().fail("Snapshot below: " + getTest().addScreenCaptureFromPath(screenShotPath));
		} catch (IOException e) {
			e.printStackTrace();

		}
		extent.flush();

	}

	public void onTestSkipped(ITestResult arg0) {

		getTest().log(Status.SKIP, MarkupHelper.createLabel(arg0.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
		getTest().skip(arg0.getThrowable());
		extent.flush();
	}

	public void onTestStart(ITestResult arg0) {

	}

	public void onTestSuccess(ITestResult arg0) {

		getTest().log(Status.PASS, MarkupHelper.createLabel(arg0.getName() + " Test Case PASSED", ExtentColor.GREEN));
		extent.flush();

	}

	public void onFinish(ISuite arg0) {
		extent.flush();

	}

	public void onStart(ISuite arg0) {

	}

}
