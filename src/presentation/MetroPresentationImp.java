package presentation;
import java.util.Scanner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bean.Station;
import bean.Transaction;
import service.BalanceCheckedException;
import service.MetroService;
import service.MetroServiceImp;
import service.StationCheckedException;

public class MetroPresentationImp implements MetroPresentation {

	private MetroService metroService=new MetroServiceImp();int src;
	@Override
	public void showMenu() {
		System.out.println("WELCOME TO METRO MANAGEMENT SYSTEM");
		System.out.println("1. Already have a card?");
		System.out.println("2. Issue a new card");
		System.out.println("3. Exit");
	}

	@SuppressWarnings("resource")
	@Override
	public void performMenu(int choice) throws Exception
	{
		switch(choice) 
		{
		case 1:
			if(metroService.checkCard())
			{
			 int x=show_second_menu();
			  while(true)
			  {
				switch(x)
				{
				
				case 1:
					{
						int r=metroService.check_flag();
						if(r==0)
						{
					    System.out.println("================================");
						Collection<Station> employees=null;
						try {
							employees = metroService.getAllEmployees();
						} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
						for(Station employee:employees) {
							System.out.println(employee);
						}
						System.out.println("================================\n");
						Scanner sc=new Scanner(System.in);
						try
						{
						System.out.println("Enter the source station:");
						src=sc.nextInt();
						if(src>metroService.max_stat()||src<1)
						{
							throw new StationCheckedException("Invalid entry! please try again");
						}
						 boolean p=metroService.swipein();
						   if(p==true)
						   {
							metroService.checkBalance();
							int t=swipe_out_menu();
							switch(t)
							{
							case 1:
							System.out.println("Enter destination station:");
							int dest=sc.nextInt();
							if(dest>metroService.max_stat()||dest<1)
							{
								throw new StationCheckedException("Invalid entry!");
							}
							System.out.println("\n");
							System.out.println("*********************************************");
							int fare=metroService.swipeout(src,dest);
							int f=metroService.checkfare(fare);
							System.out.println("Your fare is:"+f);
							System.out.println("*********************************************\n");
							t=0;
							break;
							case 2:
								 break;
							default:
								System.out.println("Invalid input!");
								return;
							}
						   }
						   x=show_second_menu();
						}catch(StationCheckedException e) {System.out.println("Station entered does not exist!");}
					  }
						else
						{
							System.out.println("You have already swiped in at station "+src+"!");
							System.out.println("================================");
							Collection<Station> employees=null;
							try {
								employees = metroService.getAllEmployees();
							} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
							for(Station employee:employees) {
								System.out.println(employee);
							}
							System.out.println("================================\n");
							Scanner sc=new Scanner(System.in);
							int t=swipe_out_menu();
							switch(t)
							{
							case 1:
							System.out.println("Enter destination station:");
							int dest=sc.nextInt();
							try
							{
							if(dest>metroService.max_stat()||dest<1)
							{
								throw new StationCheckedException("Invalid entry!");
							}
							}catch(StationCheckedException e)
							  {
								System.out.println("Station entered does not exist!");	
							  }
							System.out.println("\n");
							System.out.println("*********************************************");
							int fare=metroService.swipeout(src,dest);
							int f=metroService.checkfare(fare);
							System.out.println("Your fare is:"+f);
							System.out.println("*********************************************\n");
							//t=0;
							break;
							case 2:
								 break;
							default:
								System.out.println("Invalid input!");
						    }
							x=show_second_menu();
				     }
					}
					break;
				case 2:
			   {
				   metroService.addBalance(); 
				   metroService.checkBalance();
				  x=show_second_menu();
				  break;
			   }
				case 3:
			   {
				   metroService.checkBalance();
				   x=show_second_menu();
				   break;
			   }
				case 4:
				{
					System.out.println("================================================================================================================================");
					System.out.println("CARD 	SOURCE	DESTINATION	FARE		SWIPE IN TIME 	     		SWIPE OUT TIME");
					System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
					Collection<Transaction> employees=null;
					try {
						employees = metroService.getAllTransactions();
					} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
					for(Transaction employee:employees) {
						System.out.println(employee);
					}
					System.out.println("================================================================================================================================\n");
					 x=show_second_menu();
					break;
				}
				case 6:
			     {
				   System.out.println("Thank you for using Metro Management System!!!");
					System.exit(0); 
			     }
				case 5:
					return;
			    default:
			    	System.out.println("Invalid input!");
			   }
			  }
			} 
			break;	
		    case 2:
			int r=metroService.addcard();
			 System.out.println("New card issued with card number "+r);
			 if(metroService.checkCard(r))
			{
				 int x=show_second_menu();
				  while(true)
				  {
					switch(x)
					{
					
					case 1:
					{
						int r1=metroService.check_flag();
						if(r1==0)
						{
					    System.out.println("================================");
						Collection<Station> employees=null;
						try {
							employees = metroService.getAllEmployees();
						} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
						for(Station employee:employees) {
							System.out.println(employee);
						}
						System.out.println("================================\n");
						Scanner sc=new Scanner(System.in);
						try
						{
						System.out.println("Enter the source station:");
						src=sc.nextInt();
						if(src>metroService.max_stat()||src<1)
						{
							throw new StationCheckedException("Invalid entry! please try again");
						}
						 boolean p=metroService.swipein();
						   if(p==true)
						   {
							metroService.checkBalance();
							int t=swipe_out_menu();
							switch(t)
							{
							case 1:
							System.out.println("Enter destination station:");
							int dest=sc.nextInt();
							if(dest>metroService.max_stat()||dest<1)
							{
								throw new StationCheckedException("Invalid entry!");
							}
							System.out.println("\n");
							System.out.println("*********************************************");
							int fare=metroService.swipeout(src,dest);
							int f=metroService.checkfare(fare);
							System.out.println("Your fare is:"+f);
							System.out.println("*********************************************\n");
							t=0;
							break;
							case 2:
								 break;
							default:
								System.out.println("Invalid input!");
								return;
							}
						   }
						   x=show_second_menu();
						}catch(StationCheckedException e) {System.out.println("Station entered does not exist!");}
					  }
						else
						{
							System.out.println("You have already swiped in at station "+src+"!");
							System.out.println("================================");
							Collection<Station> employees=null;
							try {
								employees = metroService.getAllEmployees();
							} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
							for(Station employee:employees) {
								System.out.println(employee);
							}
							System.out.println("================================\n");
							Scanner sc=new Scanner(System.in);
							int t=swipe_out_menu();
							switch(t)
							{
							case 1:
							System.out.println("Enter destination station:");
							int dest=sc.nextInt();
							try
							{
							if(dest>metroService.max_stat()||dest<1)
							{
								throw new StationCheckedException("Invalid entry!");
							}
							}catch(StationCheckedException e)
							  {
								System.out.println("Station entered does not exist!");	
							  }
							System.out.println("\n");
							System.out.println("*********************************************");
							int fare=metroService.swipeout(src,dest);
							int f=metroService.checkfare(fare);
							System.out.println("Your fare is:"+f);
							System.out.println("*********************************************\n");
							//t=0;
							break;
							case 2:
								 break;
							default:
								System.out.println("Invalid input!");
						    }
							x=show_second_menu();
				     }
					}
						break;
					case 2:
				   {
					   metroService.addBalance(); 
					   metroService.checkBalance();
					  x=show_second_menu();
					  break;
				   }
					case 3:
				   {
					   metroService.checkBalance();
					   x=show_second_menu();
					   break;
				   }
					case 4:
					{
						System.out.println("================================================================================================================================");
						System.out.println("CARD 	SOURCE	DESTINATION	FARE		SWIPE IN TIME 	     		SWIPE OUT TIME");
						System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
						Collection<Transaction> employees=null;
						try {
							employees = metroService.getAllTransactions();
						} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
						for(Transaction employee:employees) {
							System.out.println(employee);
						}
						System.out.println("================================================================================================================================\n");
						 x=show_second_menu();
						break;
					}
					case 6:
				     {
					   System.out.println("Thank you for using Metro Management System!!!");
						System.exit(0); 
				     }
					case 5:
						return;
				    default:
				    	System.out.println("Invalid input!");
				   }
				  }  
			}
			break;
		case 3:
			System.out.println("Thank you for using Metro Management System!!!");
			System.exit(0);
		default :
			System.out.println("Invalid Choice");
		}

	}
	public int show_second_menu()
	{
		System.out.println("1. Swipe-in");
		System.out.println("2. Add Balance");
		System.out.println("3. Check Balance");
	    System.out.println("4. Get Transaction History");
	    System.out.println("5. Use another card");
		System.out.println("6. Exit");
		System.out.println("Enter your choice:");
		Scanner sc=new Scanner(System.in);
		int g=sc.nextInt();
		while(g>6||g<0)
		{
			System.out.println("Invalid input! Enter the value again:");
			g=sc.nextInt();
		}
		return g;
	}
	public int swipe_out_menu()
	{
		System.out.println("\n1.Swipe Out");
		System.out.println("2. Exit");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your choice:");
		int t=sc.nextInt();
		while(t<0||t>2)
		{
			System.out.println("Invalid input! Enter the value again:");
			t=sc.nextInt();
		}
		return t;
	}

}