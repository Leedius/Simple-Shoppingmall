package dto;

import java.util.List;

public class ImgDTO {
	private String imgCode;
	private String originFileName;
	private String attachedFileName;
	private String itemCode;
	private String isMain;
	private List<ImgDTO> imgList;
	
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getAttachedFileName() {
		return attachedFileName;
	}
	public void setAttachedFileName(String attachedFileName) {
		this.attachedFileName = attachedFileName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public List<ImgDTO> getImgList() {
		return imgList;
	}
	public void setImgList(List<ImgDTO> imgList) {
		this.imgList = imgList;
	}
	
	@Override
	public String toString() {
		return "ImgDTO [imgCode=" + imgCode + ", originFileName=" + originFileName + ", attachedFileName="
				+ attachedFileName + ", itemCode=" + itemCode + ", isMain=" + isMain + "]";
	}
	
	
}
