package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID,Product> Products;
    private final Map<UUID,Article> Articles;

    public StorageService() {
        this.Products = new HashMap<>();
        this.Articles = new HashMap<>();
        fillInTestData();
    }

    public Collection<Product> getProducts() {
        return Products.values();
    }

    public Collection<Article> getArticles() {
        return Articles.values();
    }

    public Collection<Searchable> getAllSearchable() {
        Map<UUID,Searchable> allSearchable = new HashMap<>();
        allSearchable.putAll(Products);
        allSearchable.putAll(Articles);
        return allSearchable.values();
    }

    private void fillInTestData() {
        SimpleProduct lamp = new SimpleProduct(UUID.randomUUID(),"Lamp", 1000);
        DiscountedProduct table = new DiscountedProduct(UUID.randomUUID(),"Table", 15000, 10);
        DiscountedProduct book = new DiscountedProduct(UUID.randomUUID(),"Book", 50, 1);
        SimpleProduct phone = new SimpleProduct(UUID.randomUUID(),"Phone", 20000);
        FixPriceProduct pencil = new FixPriceProduct(UUID.randomUUID(),"Pencil");
        SimpleProduct toy = new SimpleProduct(UUID.randomUUID(),"Toy", 800);
        Article tables = new Article(UUID.randomUUID(),"Tablee", "Tables are funny.");
        Article redTables = new Article(UUID.randomUUID(),"Red Tables Table Table", "Red tables are even funnier.");
        Article lamps = new Article(UUID.randomUUID(),"Lamppp", "LLLaaammmppp!!!");
        Article tables2 = new Article(UUID.randomUUID(),"Tableb", "Tables are funny. Really.");
        this.Products.put(lamp.getId(),lamp);
        this.Products.put(table.getId(),table);
        this.Products.put(book.getId(),book);
        this.Products.put(phone.getId(),phone);
        this.Products.put(pencil.getId(),pencil);
        this.Products.put(toy.getId(),toy);
        this.Articles.put(tables.getId(),tables);
        this.Articles.put(redTables.getId(),redTables);
        this.Articles.put(lamps.getId(),lamps);
        this.Articles.put(tables2.getId(),tables2);
    }
}
