package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
private int productId;
private String productName;
private int price;
private int stock;
private String description;
private String imageDirectory;
private int categoryId;
}
