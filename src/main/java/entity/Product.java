package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
private int product_id;
private String product_name;
private int price;
private int stock;
private String description;
private String image_directory;
private int category_id;
}
