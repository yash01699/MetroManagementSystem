package helper;
import java.util.*;
public class MetroInputOutput 
{
  public int card_num()
  {
	  System.out.println("Enter the card number:");
	  @SuppressWarnings("resource")
	  Scanner sc=new Scanner(System.in);
	  int c=sc.nextInt();
	  return c;
  }
@SuppressWarnings("resource")
public int amt()
  {
    System.out.println("Enter the amount you want to add:");
    Scanner sc=new Scanner(System.in);int a;
     a=sc.nextInt();
     while(a<20)
     {
    	 System.out.println("Please enter a value >=20 :");
    	 a=sc.nextInt();
     }
    return a;
  }
@SuppressWarnings("resource")
public char entry()
 {
	Scanner sc=new Scanner(System.in);
	System.out.println("Do you want to add balance? (y/n):");
	char c=sc.next().charAt(0);	
	return c;
 }
public void bal(int card_num,int rows,int flag)
{
	if(flag==0)
	{
	System.out.println("Balance of card "+card_num+" is "+rows);
	System.out.println("Balance is low! please recharge (>=20)");
	}
	else
	{
		System.out.println("Balance of card "+card_num+" is "+rows);
	}
}
}
