package bean;

public class Card 
{
	private String customerName;
	private int cardBalance;
	private int cardNumber;
	private int checkFlag;
	private String sourceStation;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String CardName) {
		this.customerName = CardName;
	}
	public int getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(int cardBalance) {
		this.cardBalance = cardBalance;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getSourceStation() {
		return sourceStation;
	}
	public void setSourceStation(String sourceStation) 
	{
		this.sourceStation = sourceStation;
	}
}
