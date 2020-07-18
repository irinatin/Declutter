//package com.techelevator.model;
//
//import java.math.BigDecimal;
//
//import org.hibernate.validator.constraints.NotBlank;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//
//public class BuyingEvent {
//	
//	private long buyingEventId;
//	
//	@NotBlank(message = "User is required")
//	private long userId;
//	
//	@NotBlank(message = "Item ID is required")
//	private long itemId;
//	
//	@NotBlank(message = "Price per item is required")
//	private BigDecimal pricePerItem;
//	
//	@NotBlank(message = "Category is required")
//	private String categoryName;
//	
//	@NotBlank(message = "Quantity is required")
//	private int quantity;
//	
//	private BigDecimal total;
//	
//	@NotBlank(message = "Date is required")
//	@DateTimeFormat(pattern = "MM-dd-yyyy")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") 
//	private String dateOfPurchase;
//
//	public long getBuyingEventId() {
//		return buyingEventId;
//	}
//
//	public void setBuyingEventId(long buyingEventId) {
//		this.buyingEventId = buyingEventId;
//	}
//
//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
//
//	public long getItemId() {
//		return itemId;
//	}
//
//	public void setItemId(long itemId) {
//		this.itemId = itemId;
//	}
//
//	public BigDecimal getPricePerItem() {
//		return pricePerItem;
//	}
//
//	public void setPricePerItem(BigDecimal pricePerItem) {
//		this.pricePerItem = pricePerItem;
//	}
//
//	public String getCategoryName() {
//		return categoryName;
//	}
//
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public BigDecimal getTotal() {
//		return total;
//	}
//
//	public void setTotal(BigDecimal total) {
//		this.total = total;
//	}
//
//	public String getDateOfPurchase() {
//		return dateOfPurchase;
//	}
//
//	public void setDateOfPurchase(String dateOfPurchase) {
//		this.dateOfPurchase = dateOfPurchase;
//	}
//	
//	
//	
//	
//	
//	
//
//}
