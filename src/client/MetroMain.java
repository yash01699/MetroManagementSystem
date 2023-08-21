package client;

import java.util.Scanner;

import presentation.MetroPresentation;
import presentation.MetroPresentationImp;

public class MetroMain 
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception 
	{	
		Scanner sc=new Scanner(System.in);
		MetroPresentation presentation=new MetroPresentationImp();
		while(true) 
		{
			presentation.showMenu();
			System.out.println("Enter Choice : ");
			int choice=sc.nextInt();
			presentation.performMenu(choice);
		}
	}
}