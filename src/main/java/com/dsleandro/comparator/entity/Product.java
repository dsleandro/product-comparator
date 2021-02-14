package com.dsleandro.comparator.entity;

public class Product {

	public static String[] fieldNames = { "Title", "Price", "Link" };
	private String title;
	private String price;
	private String link;

	public Product(String title, String price, String link) {
		this.title = title;
		this.price = price;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return " Title: " + title + "\n Price: " + price + "\n Link: " + link;
	}

}
