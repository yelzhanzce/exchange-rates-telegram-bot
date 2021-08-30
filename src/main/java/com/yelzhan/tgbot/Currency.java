package com.yelzhan.tgbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Currency {
    private Document document;

    public Currency() {

    }

    public Currency(String link) {
        run(link);
    }

    private void run(String link) {
        try {
            document = Jsoup.connect("https://www.exchangerates.org.uk/" + link + "-KZT-exchange-rate-history.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getChanges() {
        String res = "";
        Elements elements = document.getElementsByTag("tr");
        for (int i = 2; i < 13; i++) {
            Elements td = elements.get(i).getElementsByTag("td");
            for (int j = 1; j < td.size(); j++) {
                res += td.get(j).text() + " | ";
            }
            res = res.substring(0, res.length() - 2) + "\n";

        }

        return res;
    }
}