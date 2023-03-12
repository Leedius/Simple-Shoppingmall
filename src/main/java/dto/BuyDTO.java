package dto;

import java.util.List;

public class BuyDTO {
	private String buyCode;
	private String memId;
	private int buyPrice;
	private String buyDate;
	private List<String> cartCodesList;
	
	public String getBuyCode() {
		return buyCode;
	}
	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public List<String> getCartCodesList() {
		return cartCodesList;
	}
	public void setCartCodesList(List<String> cartCodesList) {
		this.cartCodesList = cartCodesList;
	}
}
