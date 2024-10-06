package testGauzatuEragiketa;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import dataaccess.DataAccess;
import testOperations.TestDataAccess;
import domain.Driver;
import domain.User;

public class gauzatuEragiketaBDWhiteTest {

	//sut:system under test
		 static DataAccess sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 static TestDataAccess testDA=new TestDataAccess();

		@SuppressWarnings("unused")
		private Driver driver;
		
		@Test
		public void test1() {
			boolean operacion;
			try {
				String  userName = null;
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
		public void test2() {
			boolean operacion;
			try {
				String  userName = "mikel";
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
		public void test3() {
			boolean operacion = false;
			String  userName = "mikel";
			try {
				double amount = 10.0;
				boolean deposite = true;
				testDA.open();
					testDA.createDriver(userName, "jajaja");
				    
				testDA.close();
				sut.open();
				operacion= sut.gauzatuEragiketa(userName, amount, deposite);
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
		public void test4() {
			boolean operacion = false;
			String  userName = "mikel";
			try {
				double amount = 10.0;
				boolean deposite = false;
				double wallet=9.0;
				double expectedMoney=0;
				boolean correct=false;
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
		public void test5() {
			boolean operacion = false;
			String  userName = "mikel";
			try {
				double amount = 10.0;
				boolean deposite = false;
				double wallet=11.0;
				double expectedMoney=wallet-amount;
				boolean correct=false;
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
}
