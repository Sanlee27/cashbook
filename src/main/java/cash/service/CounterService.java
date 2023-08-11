package cash.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cash.model.CounterDao;

public class CounterService {
	private CounterDao counterDao;
	// 최초접속시 insert처리
	public void addCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counterDao.insertCounter(conn);
		} catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
	}
	
	// 최초 이후 접속시 update처리
	public void modifyCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counterDao.updateCounter(conn);
		} catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
	}
	
	// 일일 접속자 카운트
	public int getCounter() {
		int counter = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			counter = counterDao.selectCounterCurdate(conn);
		} catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
		return counter;
	}
	
	// 누적 접속자 카운트
	public int getCounterAll() {
		int totalCounter = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			totalCounter = counterDao.selectCounterAll(conn);
		} catch(Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// conn.commit();
		return totalCounter;
	}
}
