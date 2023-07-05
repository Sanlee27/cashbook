package cash.vo;

public class Hashtag {
	private int cashbookNo;
	private String word;
	private String createdate;
	private String updatedate;
	
	public int getCashbookNo() {
		return cashbookNo;
	}
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
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
	
	public Hashtag() {
		super();
	}
	
	public Hashtag(int cashbookNo, String word, String createdate, String updatedate) {
		super();
		this.cashbookNo = cashbookNo;
		this.word = word;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "Hashtag [cashbookNo=" + cashbookNo + ", word=" + word + ", createdate=" + createdate + ", updatedate="
				+ updatedate + "]";
	}
	
}
	
