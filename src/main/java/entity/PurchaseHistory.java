package entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {
	private String userId;
	private int pruductId; 
	private LocalDate date;    
	private int number;
}
