package dto;

import java.util.List;

public class CartDTO {
	private String cartCode;
	private String itemCode;
	private String memId;
	private int itemQuantity;
	private int totalPrice;
	private String putDate;
	private ItemDTO itemDTO;
	private List<ImgDTO> imgList;
	
	//buy매퍼에서 선택구매쿼리문 포이치문에서 
	//cartCodeList를 사용하려면 getter호출해야하기때문 만들어줘야한다
	private List<String> cartCodeList;	//cartCode는 문자열자료이기때문에 cartCodeList는 String 자료형이다!


	public String getCartCode() {
		return cartCode;
	}
	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPutDate() {
		return putDate;
	}
	public void setPutDate(String putDate) {
		this.putDate = putDate;
	}
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public List<ImgDTO> getImgList() {
		return imgList;
	}
	public void setImgList(List<ImgDTO> imgList) {
		this.imgList = imgList;
	}
	public List<String> getCartCodeList() {
		return cartCodeList;
	}
	public void setCartCodeList(List<String> cartCodeList) {
		this.cartCodeList = cartCodeList;
	}
	
	
	@Override
	public String toString() {
		return "CartDTO [cartCode=" + cartCode + ", itemCode=" + itemCode + ", memId=" + memId + ", itemQuantity="
				+ itemQuantity + ", totalPrice=" + totalPrice + ", putDate=" + putDate + ", itemDTO=" + itemDTO + "]";
	}
	
	
	
}
