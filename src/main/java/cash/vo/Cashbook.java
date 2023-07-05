package cash.vo;

public class Cashbook {
	private int cashbookNo;
	private String memberId;
	private String category;
	private String cashbookDate;
	private int price;
	private String memo;
	private String createdate;
	private String updatedate;
	
	public int getCashbookNo() {
		return cashbookNo;
	}
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCashbookDate() {
		return cashbookDate;
	}
	public void setCashbookDate(String cashbookDate) {
		this.cashbookDate = cashbookDate;
	}
	public int getPrice() {
		return price;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	public Cashbook() {
		super();
	}
	
	public Cashbook(int cashbookNo, String memberId, String category, String cashbookDate, int price, String memo, String createdate,
			String updatedate) {
		super();
		this.cashbookNo = cashbookNo;
		this.memberId = memberId;
		this.category = category;
		this.cashbookDate = cashbookDate;
		this.price = price;
		this.memo = memo;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "Cashbook [cashbookNo=" + cashbookNo + ", memberId=" + memberId + ", category=" + category
				+ ", cashbookDate=" + cashbookDate + ", price=" + price + ", memo=" + memo + ", createdate="
				+ createdate + ", updatedate=" + updatedate + "]";
	}
	
	
}
