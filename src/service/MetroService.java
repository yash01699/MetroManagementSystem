package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bean.Station;
import bean.Transaction;

public interface MetroService 
{

	Collection<Station> getAllEmployees()throws SQLException, ClassNotFoundException, Exception;
	Collection<Transaction> getAllTransactions()throws SQLException, ClassNotFoundException, Exception;
	boolean checkCard() throws SQLException, ClassNotFoundException;
	boolean checkCard(int card_num) throws SQLException, ClassNotFoundException;
	int max_stat()throws SQLException, ClassNotFoundException;
	int addcard()throws SQLException, ClassNotFoundException;
	boolean swipein()throws ClassNotFoundException, SQLException; 
	void addBalance()throws ClassNotFoundException, SQLException; 
	void checkBalance()throws ClassNotFoundException, SQLException, BalanceCheckedException; 
	int swipeout(int src, int dest)throws ClassNotFoundException, SQLException; 
	int checkfare(int fare)throws ClassNotFoundException, SQLException; 
	int check_flag() throws ClassNotFoundException, SQLException;
}