package com.dsleandro.comparator;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Workbook;

import com.dsleandro.comparator.entity.Product;
import com.dsleandro.comparator.filemanager.ExcelFile;
import com.dsleandro.comparator.webscraping.Scraping;

public class App {
	public static void main(String[] args) {

		Scraping webScraping = new Scraping();
		Scanner sc = new Scanner(System.in);

		System.out.println("Ingresa un producto a comparar");
		String productToSearch = sc.nextLine();

		System.out.println("¿Crear archivo Excel o mostrar resultados aquí?");
		System.out.println("1. Crear archivo Excel \n" + "2. Mostrar en consola");

		int option = sc.nextInt();
		sc.close();

		ArrayList<Product> products;

		switch (option) {
		case 1:

			System.out.println("Creando archivo, por favor espere...");
			products = webScraping.scrape(productToSearch);

			ExcelFile excel = new ExcelFile();
			Workbook workbook = excel.createWorkbook();

			excel.createSheetAndInsertData(workbook, products);

			excel.createFile(workbook, 1);

			break;

		case 2:

			System.out.println("Obteniendo productos, por favor espere...");
			products = webScraping.scrape(productToSearch);

			for (Product product : products) {
				System.out.println(product.toString());
				System.out.println("-------------");
			}
			break;

		default:
			break;
		}

	}
}
