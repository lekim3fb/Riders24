package testGauzatuEragiketa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import dataaccess.DataAccess;
import domain.Driver;
import testOperations.TestDataAccess;

public class gauzatuEragiketaBDBlackTest {

	//sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver;
	
	@Test
	public void test1() {
		boolean operacion;
		String  userName = "mikel";
		try {
			double amount = 10.0;
			boolean deposite = true;
			boolean correct=false;
			double expectedMoney=amount;
			testDA.open();
			testDA.createDriver(userName, "jajaja");
			testDA.close();
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			if(sut.getDriver(userName).getMoney()==expectedMoney) {
				correct=true;
			}
			assertTrue(correct);
			assertTrue(operacion);
			
			
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
			testDA.open();
			testDA.removeDriver(userName);
			testDA.close();
		}
	}
	
	
	@Test
	public void test2() {
		boolean operacion;
		String  userName = "mikel";
		try {
			double amount = 10.0;
			boolean deposite = false;
			double wallet = 11.0;
			boolean correct=false;
			double expectedMoney=wallet-amount;
			testDA.open();
			testDA.createDriverWithMoney(userName, "jajaja", wallet);
			testDA.close();
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			if(sut.getDriver(userName).getMoney()==expectedMoney) {
				correct=true;
			}
			assertTrue(correct);
			assertTrue(operacion);
			
			
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
			testDA.open();
			testDA.removeDriver(userName);
			testDA.close();
		}
	}
	
	
	@Test
	public void test3() {
		boolean operacion;
		String  userName = "mikel";
		try {
			double amount = 10.0;
			boolean deposite = false;
			double wallet = 9.0;
			testDA.open();
			testDA.createDriverWithMoney(userName, "jajaja", wallet);
			testDA.close();
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			assertFalse(operacion);
			
			
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
			testDA.open();
			testDA.removeDriver(userName);
			testDA.close();
		}
	}
	
	
	
	@Test
	public void test4() {
		boolean operacion;
		String  userName = null;
		try {
			double amount = 10.0;
			boolean deposite = true;
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			assertFalse(operacion);
			
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
		}
	}
	
	@Test
	public void test5() {
		boolean operacion;
		String  userName = "mikel";
		try {
			double amount = -8;
			boolean deposite = true;
			testDA.open();
			testDA.createDriver(userName, "jajaja");
			testDA.close();
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			assertFalse(operacion);
			
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
			testDA.open();
			testDA.removeDriver(userName);
			testDA.close();
		}
	}
	
	@Test
	public void test6() {
		boolean operacion;
		String  userName = "mikel";
		try {
			double amount = 10;
			testDA.open();
			testDA.createDriver(userName, "jajaja");
			testDA.close();
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, (Boolean) null);
			assertFalse(operacion);
			
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(false);
		}finally {
			sut.close();
			testDA.open();
			testDA.removeDriver(userName);
			testDA.close();
		}
	}
}
