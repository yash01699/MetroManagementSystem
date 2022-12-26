package bean;
import java.sql.Timestamp;
import java.time.LocalTime;
public class Transaction
{
  private int card_num,source,destination,fare;
  Timestamp ts,to;
  public Transaction()
  {
	  
  }
  public Transaction(int card_num,int source,int destination,int fare,Timestamp ts,Timestamp to)
  {
	  super();
	  this.card_num=card_num;
	  this.source=source;
	  this.destination=destination;
	  this.fare=fare;
	  this.ts=ts;
	  this.to=to;
  }
public int getCard_num() {
	return card_num;
}
public void setCard_num(int card_num) {
	this.card_num = card_num;
}
public int getSource() {
	return source;
}
public void setSource(int source) {
	this.source = source;
}
public int getDestination() {
	return destination;
}
public void setDestination(int destination) {
	this.destination = destination;
}
public int getFare() {
	return fare;
}
public void setFare(int fare) {
	this.fare = fare;
}
public Timestamp getTs() {
	return ts;
}
public void setTs(Timestamp ts) {
	this.ts = ts;
}
public Timestamp getTo() {
	return to;
}
public void setTo(Timestamp to) {
	this.to = to;
}
@Override
public String toString()
{
	return "" + card_num + "	" + source + "	   " + destination + "		"+ fare + "		" + ts + "		"+to;
}
  
}
