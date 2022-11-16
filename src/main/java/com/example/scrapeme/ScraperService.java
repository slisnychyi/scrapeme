package com.example.scrapeme;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ScraperService {

    public String scrape(String token) throws IOException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");

        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://example.com");
        WebElement element = driver.findElement(By.xpath("/html/body/div/p[2]/a"));

        String result = element.getText() + getToken(token);

        System.out.println("generated result : " + result);

        driver.close();
        driver.quit();

        return result;
    }

    private String getToken(String token) throws IOException {
        Process process = Runtime.getRuntime().exec("stoken --token=" + token);
        return new BufferedReader(new InputStreamReader(process.getInputStream())).readLine();
    }

}
