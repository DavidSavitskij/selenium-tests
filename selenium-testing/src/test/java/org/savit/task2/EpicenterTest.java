package org.savit.task2;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class EpicenterTest {

    private static final String GOOGLE_URL = "https://google.com/ncr";
    private static final String EPICENTER_URL = "https://epicentrk.ua/";
    private static final String EPICENTER_CONTACTS_URL = "https://epicentrk.ua/ua/info/contacts/";

    private static final String SEARCH_FIELD_NAME="q";
    private static final String SEARCH_RESULT_ID="rso";
    private static final String SEARCH_RESULT_LIST_PATH="/*";

    private static final String EPICENTER_SEARCH_QUERY = "Епіцентр";
    private static final String CONTACTS_LINK_SELECTOR = "a[title='Контакти']";
    private static final String WORKTIME_SELECTOR = ".company__content h3";
    private static final String WORKTIME_FROM_TO = "Контакт-центр працює для Вас щоденно з 07:30 до 22:30.";

    private static final String HREF_ATTR = "href";

    private static WebDriver driver;


    @BeforeClass
    public static void setUpAll(){
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();

    }

    @Test
    public void shouldCheckWorkTime(){

        driver.get(GOOGLE_URL);

        driver.findElement(By.name(SEARCH_FIELD_NAME)).sendKeys(EPICENTER_SEARCH_QUERY + Keys.ENTER);

        driver.findElement(By.id(SEARCH_RESULT_ID)).findElements(By.xpath(SEARCH_RESULT_LIST_PATH)).get(0).click();
        assertThat(driver.getCurrentUrl()).isEqualTo(EPICENTER_URL);
        WebElement contactsLink = driver.findElement(By.cssSelector(CONTACTS_LINK_SELECTOR));
        assertThat(contactsLink.getAttribute(HREF_ATTR)).isEqualTo(EPICENTER_CONTACTS_URL);
        driver.get(contactsLink.getAttribute(HREF_ATTR));
        assertThat(driver.getCurrentUrl()).isEqualTo(EPICENTER_CONTACTS_URL);
        WebElement workTimeInfo = driver.findElement(By.cssSelector(WORKTIME_SELECTOR));
        assertThat(workTimeInfo.getText()).contains(WORKTIME_FROM_TO);
    }

    @After
    public void closeUp(){
        driver.quit();
    }



}
