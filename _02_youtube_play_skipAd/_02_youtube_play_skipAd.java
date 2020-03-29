package _workbook_selenium;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class _02_youtube_play_skipAd {
	public static void main(String[] args) {
		// Using Selenium for Java
		// 1) Open YouTube
		// 2) Search for Rick Astley and play song "Never gonna give you up"
		// If there is an Ad on the beginning of the video, skip it.

		// After that, from the list of the songs
		// on the right side ("Watch next")
		// play first offered video.
		// Skip Ad again.
		// do the second part of the task for 5 times.

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.youtube.com");
		driver.manage().window().maximize();

		// enter search data
		WebElement searchbox = driver.findElement(By.xpath("//input[@id='search']"));
		searchbox.click();
		searchbox.sendKeys("Rick Astley Never gonna give you up");

		// click on search btn
		WebElement searchBtn = driver.findElement(
				By.xpath("//button[@id='search-icon-legacy']//yt-icon[@class='style-scope ytd-searchbox']"));
		searchBtn.click();

		waitFOR(2000);

		// select the song from the offered list
		WebElement selectSong = driver.findElement(By.xpath(
				"//body/ytd-app/div[@id='content']/ytd-page-manager[@id='page-manager']/ytd-search[@class='style-scope ytd-page-manager']/div[@id='container']/ytd-two-column-search-results-renderer[@class='style-scope ytd-search']/div[@id='primary']/ytd-section-list-renderer[@class='style-scope ytd-two-column-search-results-renderer']/div[@id='contents']/ytd-item-section-renderer[@class='style-scope ytd-section-list-renderer']/div[@id='contents']/ytd-video-renderer[1]/div[1]/div[1]/div[1]/div[1]/h3[1]/a[1]"));
		selectSong.click();
		waitFOR(4000);

		removeLoginNote(driver);

		removeAd(driver, 0);

		for (int i = 1; i <= 5; i++) {

			nextSong(driver);
			removeAd(driver, i);

			waitFOR(3000);
		}

		driver.close();

	}

	public static void waitFOR(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void removeLoginNote(WebDriver wd) {
		// Pop-up appears, that prevents clicking on the next song.
		// This method removes it.
		/*
		 * "You are signed out from YouTube. Sign in to like videos, comment and
		 * subscribe. GOT IT"
		 */
		WebElement gotIt = wd.findElement(By.xpath(
				"/html/body/ytd-app/ytd-popup-container/iron-dropdown/div/yt-tooltip-renderer/div[2]/div[1]/yt-button-renderer/a"));
		gotIt.click();

	}

	public static void nextSong(WebDriver wd) {
		// Chooses the first song from the "watch next" list
		String song1 = "/html/body/ytd-app/div/ytd-page-manager/ytd-watch-flexy/div[4]/div[2]/div/div[3]/ytd-watch-next-secondary-results-renderer/div[2]/ytd-compact-autoplay-renderer/div[2]/ytd-compact-video-renderer/div[1]/div/div[1]/a";
		WebElement selectSong1 = wd.findElement(By.xpath(song1));
		selectSong1.click();

	}

	public static void removeAd(WebDriver wd, int i) {
		/*
		 * This method should skip Ad if it is playing at the beginning of the video.
		 * But, it seams that one can not skip Ad if not logged in.
		 */
		By skipAdd = By.xpath("//div[@class='ytp-ad-player-overlay-skip-or-preview']");
		// Previous line declare and create object of Class By

		// then it checks if there is an Ad playing within.
		if (isElementPresent(skipAdd, wd)) {
			System.out.print("Ad detected ... " + (i + 1) + " ");
			waitFOR(10000);
			// if it "founds" Ad, "if" block is conducted.But...

			try {
				// ...but, SkipAd button does not appear if one is not logged in on YT...

				wd.findElement(By.className("ytp-ad-skip-button")).click();

				// various attempts to locate "Skip Ad" btn:
				// wd.findElement(By.xpath("//*[text() = 'Skip Ad']"));
				// wd.findElement(By.name("ytp-ad-skip-button ytp-button")).click();
				// wd.findElement(By.className("ytp-ad-skip-button")).click();
				// wd.findElement(By.className("ytp-ad-text ytp-ad-skip-button-text")).click();
				// wd.findElement(By.xpath("//button[@class='ytp-ad-skip-button
				// ytp-button']")).click();
				// wd.findElement(By.className("ytp-ad-skip-button ytp-button")).click();
				// ytp-ad-text ytp-ad-skip-button-text

			} catch (org.openqa.selenium.NoSuchElementException e) {
				// e.printStackTrace();
				System.out.println("Ad closed without 'Skip Ad' button");
			}

		} else {
			waitFOR(1000);

			System.out.println("No Ad playing " + (i + 1));
		}
	}

	public static boolean isElementPresent(By by, WebDriver wd) {
		// Check if there is an Ad playing in the video "container" received by
		// removeAd() method
		waitFOR(1000);

		try {
			wd.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

}
