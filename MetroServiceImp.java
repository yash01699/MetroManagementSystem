package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bean.Station;
import bean.Transaction;
import helper.MetroInputOutput;
import persistence.MetroDao;
import persistence.MetroDaoImp;

public class MetroServiceImp implements MetroService {

	private MetroDao employeeDao=new MetroDaoImp();int card_num;
	MetroInputOutput mio=new MetroInputOutput();
	@Override
	public Collection<Station> getAllEmployees()throws SQLException, ClassNotFoundException, Exception {
		
		return employeeDao.getAllRecords();
	}
    public boolean checkCard()throws SQLException, ClassNotFoundException
    {
    	card_num=mio.card_num();
		return employeeDao.checking_card(card_num);
    	
    }
    public int max_stat()throws SQLException, ClassNotFoundException
    {
    	return employeeDao.check_max_stat();
    }
	@Override
	public int addcard() throws SQLException, ClassNotFoundException
	{
		return employeeDao.adding_card();
		
	}
	@Override
	public boolean swipein() throws ClassNotFoundException, SQLException 
	{
		return employeeDao.swiping_in(card_num);
	}
	@Override
	public void addBalance()throws ClassNotFoundException, SQLException  
	{
		int amount=mio.amt();
		employeeDao.adding_balance(amount,card_num);
	}
	public void checkBalance() throws ClassNotFoundException, SQLException, BalanceCheckedException 
	{
		employeeDao.checking_balance(card_num);
	}
	@Override
	public int swipeout(int src, int dest) throws ClassNotFoundException, SQLException 
	{
		return employeeDao.swiping_out(src,dest,card_num);	
	}
	@Override
	public int checkfare(int fare) throws ClassNotFoundException, SQLException 
	{
		return employeeDao.checking_fare(fare,card_num);
		
	}
	@Override
	public boolean checkCard(int card_num) throws SQLException, ClassNotFoundException 
	{
		this.card_num=card_num;
		return employeeDao.checking_card(card_num);
	}
	@Override
	public Collection<Transaction> getAllTransactions() throws Exception 
	{
		return employeeDao.getAlltrans(card_num);
	}
	public int check_flag()throws ClassNotFoundException, SQLException
	{
		return employeeDao.checking_flag(card_num);
	}
}
