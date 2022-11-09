package com.example.scrapeme;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws IOException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");

        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://example.com");
        WebElement element = driver.findElement(By.xpath("/html/body/div/p[2]/a"));

        System.out.println(element.getText() + getToken());

        driver.close();
        driver.quit();
    }

    private String getToken() throws IOException {
        Process process = Runtime.getRuntime().exec("stoken tokencode");
        return new BufferedReader(new InputStreamReader(process.getInputStream())).readLine();
    }
}
