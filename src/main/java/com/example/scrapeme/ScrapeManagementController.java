package com.example.scrapeme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/scrape")
public class ScrapeManagementController {

  private final ScraperService scraperService;

  public ScrapeManagementController(ScraperService scraperService) {
    this.scraperService = scraperService;
  }

  @GetMapping("/{tokenString}")
  public String getData(@PathVariable("tokenString") String tokenString) throws IOException {
    System.out.println("received request with token = " + tokenString);
    return scraperService.scrape(tokenString);
  }

}
