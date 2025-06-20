package entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {
	private String user_id;
	private int pruduct_id; // CREATE文と一致（タイポも含めて）
	private LocalDate date;    
	private int number;
}
