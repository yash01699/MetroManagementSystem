package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bean.Station;
import bean.Transaction;
import service.BalanceCheckedException;

public interface MetroDao {

	Collection<Station> getAllRecords() throws SQLException, ClassNotFoundException, Exception;

	boolean checking_card(int card_num) throws SQLException, ClassNotFoundException;

	int adding_card()throws SQLException, ClassNotFoundException;

	boolean swiping_in(int card_num)throws ClassNotFoundException, SQLException; 

	int adding_balance(int amount,int card_num)throws ClassNotFoundException, SQLException; 

	int checking_balance(int card_num)throws ClassNotFoundException, SQLException, BalanceCheckedException; 

	int swiping_out(int src, int dest,int card_num)throws ClassNotFoundException, SQLException;

	int checking_fare(int fare,int card_num)throws ClassNotFoundException, SQLException; 
	int check_max_stat()throws ClassNotFoundException, SQLException;
	Collection<Transaction> getAlltrans(int card_num) throws SQLException, ClassNotFoundException, Exception;
	int checking_flag(int card_num) throws ClassNotFoundException, SQLException;
}