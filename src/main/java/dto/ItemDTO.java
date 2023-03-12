package dto;

import java.util.List;

public class ItemDTO {
	private String itemCode;
	private String cateCode;
	private String itemName;
	private int itemPrice;
	private String itemIntro;
	private String regDate;
	private String fileName;
	private String orgFileName;
	private String attachedFileName;
	private List<ImgDTO> imgList;
	

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemIntro() {
		return itemIntro;
	}
	public void setItemIntro(String itemIntro) {
		this.itemIntro = itemIntro;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getAttachedFileName() {
		return attachedFileName;
	}
	public void setAttachedFileName(String attachedFileName) {
		this.attachedFileName = attachedFileName;
	}
	public List<ImgDTO> getImgList() {
		return imgList;
	}
	public void setImgList(List<ImgDTO> imgList) {
		this.imgList = imgList;
	}
	
	@Override
	public String toString() {
		return "ItemDTO [itemCode=" + itemCode + ", cateCode=" + cateCode + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice + ", itemIntro=" + itemIntro + ", regDate=" + regDate + ", fileName=" + fileName
				+ ", orgFileName=" + orgFileName + ", attachedFileName=" + attachedFileName + ", imgList=" + imgList
				+ "]";
	}
	
	
}
