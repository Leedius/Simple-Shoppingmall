package dto;

public class MemberDTO {
	private String memId;
	private String memPw;
	private String memName;
	private String gender;
	private String addr;
	private String addrDetail;
	private String memTell;
	private String memEmail;
	private String createDate;
	private String isAdmin;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getMemTell() {
		return memTell;
	}
	//밑에 매개변수로 배열로 받는 SETTER가 있더라도
	//기본 SETTER가 있어야한다.
	public void setMemTell(String memTell) {
		this.memTell = memTell; 
	}
	public void setMemTell(String[] memTell) {
		this.memTell = memTell[0] + "-" + memTell[1] + "-" + memTell[2]; 
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public void setMemEmail(String[] memEmail) {
		this.memEmail = memEmail[0] + memEmail[1];
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	


}
