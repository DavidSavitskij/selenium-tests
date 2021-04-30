package org.savit.task1;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KpiShedulePageTest {

    private static WebDriver driver;

    private static final String URL = "http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx";
    private static final String INPUT_TEXT_ID = "ctl00_MainContent_ctl00_txtboxGroup";
    private static final String INPUT_BUTTON_ID = "ctl00_MainContent_ctl00_btnShowSchedule";
    private static final String GROUP = "КП-92";
    private static final String WEDNWSDAY_SUBJECTS_TITLES_SELECTOR = "#ctl00_MainContent_FirstScheduleTable td:nth-child(4) span";
    private static final String SUBJECT_TITLE = "Компоненти програмної інженерії 2. Якість та тестування програмного забезпечення";
    private static final String VALUE_ATTR = "value";
    private static final String INPUT_TAG = "input";

    @BeforeClass
    public static void setUpAll(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldCheckSubject(){
        driver.get(URL);
        WebElement findField = driver.findElement(By.id(INPUT_TEXT_ID));
        assertThat(findField.getTagName()).isEqualTo(INPUT_TAG);

        findField.sendKeys(GROUP);
        assertThat(findField.getAttribute(VALUE_ATTR)).isEqualTo(GROUP);

        WebElement button = driver.findElement(By.id(INPUT_BUTTON_ID));
        assertThat(button.getTagName()).isEqualTo(INPUT_TAG);

        button.click();

        List<WebElement> elements = driver.findElements(By.cssSelector(WEDNWSDAY_SUBJECTS_TITLES_SELECTOR));
        elements.stream().map(a -> a.getText()).forEach(System.out::println);
        assertThat(elements.stream().map(a -> a.getText()).anyMatch(SUBJECT_TITLE::equals)).isTrue();
    }

    @After
    public void closeUp(){
        driver.close();
    }


}
