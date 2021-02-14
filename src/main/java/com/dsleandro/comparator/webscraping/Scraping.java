package com.dsleandro.comparator.webscraping;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dsleandro.comparator.entity.Product;

public class Scraping {
	public Document getHtml(String url, boolean isSearch) {

		Document doc = null;

		try {

			if (isSearch) {
				doc = Jsoup.connect("https://listado.mercadolibre.com.ar/" + url).get();
			} else {
				doc = Jsoup.connect(url).get();
			}

			return doc;

		} catch (IOException e) {
			System.out.println("Error: No se pudo acceder a la pagina web");
			return doc;
		}
	}

	public ArrayList<Product> scrape(String product) {
		
		ArrayList<Product> arrayListWithProducts = new ArrayList<>();
		
		Elements productList = getHtml(product, true).select("li.ui-search-layout__item");

		for (Element productElement : productList) {
			
			String title = productElement.select("h2.ui-search-item__title").text();
			String price = productElement.select(
					"div.ui-search-price__second-line span.price-tag-fraction")
					.text();
			String productLink = productElement.select("a.ui-search-link").attr("href");

			arrayListWithProducts.add(new Product(title, price, productLink));
		}
		
		return arrayListWithProducts;
	}

}
