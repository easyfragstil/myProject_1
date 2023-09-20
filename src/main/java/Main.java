import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("1. Открыли браузер и развернули на весь экран.");

        driver.get("https://yandex.ru");
        System.out.println("2. Зашли на yandex.ru");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@action='https://yandex.ru/search/']"))).click();

        driver.get("https://market.yandex.ru");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-zone-name='catalog']/button"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Смартфоны']"))).click();
        System.out.println("3. В разделе Маркет выбрали смартфоны.");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-auto='allFiltersButton']"))).click();
        System.out.println("4. Зашли в расширенный поиск.");

        WebElement priceFrom = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_307sS _2k6P8']/div/div[@data-prefix='до']/input"))));
        priceFrom.sendKeys("20000");
        Thread.sleep(500);
        WebElement footer = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-zone-name='footer']"))));
        new Actions(driver)
                .moveToElement(footer)
                .perform();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Диагональ экрана (точно)')]/.."))).click();
        WebElement diagonalFrom = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Диагональ экрана (точно)')]/../../div/div/div[1]/input"))));
        diagonalFrom.sendKeys("3");
        Thread.sleep(500);
        System.out.println("5. Задали параметр поиска до 20000 рублей и Диагональ экрана от 3 дюймов.");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_307sS _2k6P8']/div/div[2]/div[1]/label/div"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_307sS _2k6P8']/div/div[2]/div[2]/label/div"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_307sS _2k6P8']/div/div[2]/div[3]/label/div"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_307sS _2k6P8']/div/div[2]/div[4]/label/div"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_307sS _2k6P8']/div/div[2]/div[5]/label/div"))).click();

        System.out.println("6. Выбрали 5 любых производителей, среди популярных.");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Показать ')]"))).click();
        System.out.println("7. Нажали кнопку Показать.");

        WebElement footer1 = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-zone-name='footer']"))));
        Thread.sleep(1000);
        new Actions(driver)
                .moveToElement(footer1)
                .perform();
        WebElement lastElement = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-test-id='virtuoso-item-list']/div[last()]"))));
        String dataSet = lastElement.getAttribute("data-index");
        System.out.println("8. Проверили, что элементов на странице "+ lastElement.getAttribute("data-index") + ".");

        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-index='1']//article"))));
        Thread.sleep(1000);
        String url = element.getAttribute("innerHTML");
        String headText = element.getAttribute("innerText");
        String domenUrl = "https://market.yandex.ru";

        String cropUrl = url.substring(url.indexOf("href=")+6);
        domenUrl = domenUrl.concat(cropUrl);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        int start = headText.indexOf("\n")+1;
        int end = headText.indexOf("\n",10);

        if (start > 11) {
            start = 0;
            return;
        }

        String dst = headText.substring(start,end);
        System.out.println("9. Запомнили первый элемент в списке " + "(" + dst + ").");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='по цене']"))).click();
        Thread.sleep(1000);
        System.out.println("10. Изменили Сортировку на другую (по цене).");

        WebElement headerSearch = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("header-search"))));
        headerSearch.click();
        headerSearch.sendKeys(dst, Keys.ENTER);
        System.out.println("11. Найшли  запомненный объект по имени.");

        WebElement rating = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-index='1']//article/div/div[4]/div/div/span[1]"))));
        String rate = rating.getAttribute("innerText");
        System.out.println("12. Вывели цифровое значение его оценки: "+ rate + ".");

        driver.quit();
        System.out.println("13. Закрыли браузер.");
    }
}

