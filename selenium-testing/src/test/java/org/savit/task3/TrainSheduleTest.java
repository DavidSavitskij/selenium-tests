package org.savit.task3;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainSheduleTest {

    private static final String RADA_URL = "https://www.rada.gov.ua/";
    private static final String TELEGRAM_CHANNEL_URL = "https://t.me/verkhovnaradaukrainy";

    private static final String TELEGRAM_LINK_SELECTOR = ".map_menu .telegram-ico";
    private static final String SUBSCRIBERS_SELECTOR = ".tgme_page_extra";
    private static final String REGEX = "\\d";

    private static final String HREF_ATTR = "href";

    private static WebDriver driver;


    @BeforeClass
    public static void setUpAll(){
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldGetEmptyPageTitle(){

        driver.get(RADA_URL);
        assertThat(driver.getCurrentUrl()).isEqualTo(RADA_URL);
        WebElement telegramLink = driver.findElement(By.cssSelector(TELEGRAM_LINK_SELECTOR));
        assertThat(telegramLink.getAttribute(HREF_ATTR)).isEqualTo(TELEGRAM_CHANNEL_URL);
        driver.get(telegramLink.getAttribute(HREF_ATTR));
        String subscribers = driver.findElement(By.cssSelector(SUBSCRIBERS_SELECTOR)).getText();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(subscribers);

        String total = "";
        while(matcher.find()){
            total+=matcher.group();
        }
        assertThat(Integer.parseInt(total) < 10000).isTrue();


    }

    @After
    public void closeUp(){
        driver.quit();
    }

}
