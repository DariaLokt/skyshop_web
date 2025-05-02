package org.skypro.skyshop.model.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    public Product(UUID id, String name) {
        this.id = id;
        checkName(name);
        this.name = name;
    }

    private static void checkName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Нет названия");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
    }


    public String getName() {
        return name;
    }

    public abstract float getPrice();

    public abstract boolean isSpecial();

//    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public UUID getId() {
        return id;
    }
}

