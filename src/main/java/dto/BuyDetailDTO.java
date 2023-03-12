package dto;

import java.util.List;

public class BuyDetailDTO {
	private String buyDetailCode;
	private String itemCode;
	private int buyQuantity;
	private int buyPrice;
	private String buyCode;
	private List<BuyDetailDTO> buyDetailList;
	
	public String getBuyDetailCode() {
		return buyDetailCode;
	}
	public void setBuyDetailCode(String buyDetailCode) {
		this.buyDetailCode = buyDetailCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getBuyCode() {
		return buyCode;
	}
	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}
	public List<BuyDetailDTO> getBuyDetailList() {
		return buyDetailList;
	}
	public void setBuyDetailList(List<BuyDetailDTO> buyDetailList) {
		this.buyDetailList = buyDetailList;
	}
	
	
}
