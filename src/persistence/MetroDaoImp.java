package persistence;


import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import bean.Card;
import bean.Station;
import bean.Transaction;
import helper.MetroInputOutput;
import helper.MySQLConnection;
import service.BalanceCheckedException;
import service.MetroService;
import service.MetroServiceImp;

import java.util.Date;
@SuppressWarnings("unused")
public class MetroDaoImp implements MetroDao
{
	int x,t; static int ans; static Timestamp ts;
	MetroInputOutput mio=new MetroInputOutput();
	public int check_max() throws ClassNotFoundException, SQLException
	{
		Connection connection=MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select max(card_number) as count from card");
		ResultSet result=statement.executeQuery();
		x=0;
		
		while(result.next()){
			x=result.getInt("count");
		}
		return x;
	}
	public int check_max_stat() throws ClassNotFoundException, SQLException
	{
		Connection connection=MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select max(station_id) as count from station");
		ResultSet result=statement.executeQuery();
		t=0;
		
		while(result.next()){
			t=result.getInt("count");
		}
		return t;
	}
	
	@Override
	public Collection<Station> getAllRecords() throws SQLException, ClassNotFoundException,Exception 
	{

		ArrayList<Station> stations = null;
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from station");
		ResultSet resultset = statement.executeQuery();

		Station employee = null;
		stations = new ArrayList<Station>();

		while (resultset.next()) {
			employee = new Station();
			employee.setStation_id(resultset.getInt("station_id"));
			employee.setStation_name(resultset.getString("station_name"));

			stations.add(employee);
		}

		connection.close();

		return stations;
	}

	@Override
	public boolean checking_card(int card_num) throws SQLException, ClassNotFoundException 
	{
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from card where card_number=?");
		statement.setInt(1,card_num);
		ResultSet resultset = statement.executeQuery();
		Card employee=new Card();
		if(resultset.next()==false)
		{
			System.out.println("Invalid card! Please issue a new one");
			connection.close();
			return false;
		}
		else
		{
			employee.setCardNumber(resultset.getInt("card_number"));
			System.out.println("Card number "+employee.getCardNumber()+" found successfuly");
			connection.close();
			return true;
		}
	}

	@Override
	public int adding_card() throws ClassNotFoundException, SQLException 
	{
		int y=check_max();
		y++;
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("insert into card values (?,?,?);");
		statement.setInt(1,y);
		statement.setInt(2,100);
		statement.setInt(3,0);
		statement.executeUpdate();
		connection.close();
		return y;
	}

	@Override
	public boolean swiping_in(int card_num) throws ClassNotFoundException, SQLException 
	{  
		    Connection connection = MySQLConnection.getConnection();
			PreparedStatement statement=connection.prepareStatement("update card set check_flag=1 where card_number=?");
			statement.setInt(1,card_num);
			int x=statement.executeUpdate();
			 Date date= new Date();
		     long swipe_in_time = date.getTime();
		     ts = new Timestamp(swipe_in_time);
			connection.close();
		    return true;
	}

	@Override
	public int adding_balance(int amount,int card_num) throws ClassNotFoundException, SQLException 
	{
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select card_balance as count from card where card_number=?");
		statement.setInt(1,card_num);
		ResultSet result=statement.executeQuery();
		int rows=0;
		
		while(result.next()){
			rows=result.getInt("count");
		}
		int bal=rows+amount;
		statement = connection.prepareStatement("update card set card_balance=? where card_number=?");
		statement.setInt(1,bal);
		statement.setInt(2,card_num);
		statement.executeUpdate();
		System.out.println("Amount "+amount+" is added to card number "+card_num);
		rows=bal;
		connection.close();
		return rows;
	}

	@Override
	public int checking_balance(int card_num) throws SQLException, ClassNotFoundException, BalanceCheckedException {
		Connection connection=MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select card_balance as count from card where card_number=?");
		statement.setInt(1,card_num);
		ResultSet result=statement.executeQuery();
		int rows=0;
		
		while(result.next()){
			rows=result.getInt("count");
		}
		try
		{
		while(rows<20)
		{
			throw new BalanceCheckedException("Low Balance!");
		}
		}catch(BalanceCheckedException e) 
		{ 

			mio.bal(card_num, rows,0);
			char c=mio.entry();
			if(c=='y')
			{
			int amt=mio.amt();
			rows=adding_balance(amt,card_num);
			}
		}
			mio.bal(card_num, rows,1);
			connection.close();
			return rows;
	}

	@Override
	public int swiping_out(int src, int dest,int card_num) throws ClassNotFoundException, SQLException 
	{
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select card_balance as count from card where card_number=?");
		statement.setInt(1,card_num);
		ResultSet result=statement.executeQuery();
		int rows=0;
		
		while(result.next()){
			rows=result.getInt("count");
		}
		try
		{
		while(rows<20||rows-(Math.abs(src-dest)*5)<0)
		 {
			throw new BalanceCheckedException("Low Balance!");
		 }
		}
		catch(BalanceCheckedException e)
		{
			mio.bal(card_num, rows,0);
			int amt=mio.amt();
			rows=adding_balance(amt,card_num);
		}
		
			statement=connection.prepareStatement("update card set check_flag=0 where card_number=?");
			statement.setInt(1,card_num);
			int x=statement.executeUpdate();
			statement=connection.prepareStatement("INSERT INTO TRANSACTION VALUES (?,?,?,?,?,CURRENT_TIME)");
			ans=Math.abs(src-dest);
			statement.setInt(1,card_num);
			statement.setInt(2,src);
			statement.setInt(3,dest);
			statement.setInt(4,ans*5);
			statement.setTimestamp(5, ts);
			System.out.println("Swiped out successfully!");
			int y=statement.executeUpdate();
			connection.close();
		return Math.abs(src-dest);
	}

	@Override
	public int checking_fare(int fare,int card_num) throws SQLException, ClassNotFoundException 
	{
		fare=ans*5;
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select card_balance as count from card where card_number=?");
		statement.setInt(1,card_num);
		ResultSet result=statement.executeQuery();
		int rows=0;
		
		while(result.next())
		{
		 rows=result.getInt("count");
		}
		int bal=rows-fare;
		connection = MySQLConnection.getConnection();
		statement=connection.prepareStatement("update card set card_balance=? where card_number=?");
		statement.setInt(1,bal);
		statement.setInt(2,card_num);
		int x=statement.executeUpdate();
		connection.close();
		return fare;
	}
	@Override
	public Collection<Transaction> getAlltrans(int card_num) throws SQLException, ClassNotFoundException, Exception {
		ArrayList<Transaction> transactions = null;
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from transaction where card_number=?");
		statement.setInt(1,card_num);
		ResultSet resultset = statement.executeQuery();

		Transaction employee = null;
		transactions = new ArrayList<Transaction>();

		while (resultset.next()!=false) 
		{
			employee = new Transaction();
			employee.setCard_num(resultset.getInt("card_number"));
			employee.setSource(resultset.getInt("src_station"));
			employee.setDestination(resultset.getInt("dest_station"));
			employee.setFare(resultset.getInt("fare"));
			employee.setTs(resultset.getTimestamp("swipe_in"));
			employee.setTo(resultset.getTimestamp("swipe_out"));
			
			transactions.add(employee);
		}
		connection.close();
		return transactions;
	}
	
	public int checking_flag(int card_num) throws ClassNotFoundException, SQLException
	{
		Connection connection=MySQLConnection.getConnection();
		PreparedStatement statement=connection.prepareStatement("select check_flag as count from card where card_number=?");
		statement.setInt(1,card_num);
		ResultSet result=statement.executeQuery();
		t=0;
		
		while(result.next()){
			t=result.getInt("count");
		}
		return t;
	}

}