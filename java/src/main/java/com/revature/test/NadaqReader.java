package com.revature.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class NadaqReader {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("Nasdaq.csv"));
		PrintWriter pw = new PrintWriter(new FileOutputStream("../AssetPricing.sql", false));
		String line;
		int i = 0;
		while ((line = bf.readLine()) != null) {
			i++;
			String sp[] = line.split(",");
			String symbole = "'" + sp[0].trim().replaceAll("'", "''") + "', ";
			String name = "'" + sp[1].trim().replaceAll("'", "''") + "'";
			String out = "INSERT INTO asset_pricing (ticker_symbol, company_name) VALUES (" + symbole + name + ");";
			System.out.println(out);
			pw.println(out);
		}
		System.out.println(i);
		bf.close();
		pw.close();
	}
}
