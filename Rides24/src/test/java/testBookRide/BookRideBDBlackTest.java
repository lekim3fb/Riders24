import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataaccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Traveler;
import testOperations.TestDataAccess;

public class BookRideBDBlackTest {
	
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	 
	 static Traveler t;
	 static Driver d;
	 static Ride r;
	
	@Test
	public void test1() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=sdf.parse("01/01/2025");
			
			testDA.open();
			String name = null;
			String pass = "123";
			Ride r = testDA.createRide("", "", rideDate, 5, 20, null);
			Traveler t = testDA.addTraveler(name, pass);
			t.setMoney(1000);
			
			boolean estado = false;
			
			testDA.close();
			sut.open();
			try {
				estado = sut.bookRide(name, r, 1, 0);
			} catch(Exception e) {
				e.printStackTrace();
			}
	
			assertFalse(estado);
			
		} catch(Exception e) {
			fail();
			sut.close();
		}
		finally {
			sut.close();
		}
	}
	
	@Test
	public void test2() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=sdf.parse("01/01/2025");
			
			testDA.open();
			String name = "nombre";
			String pass = "123";
			Ride r = null;
			Traveler t = testDA.addTraveler(name, pass);
			t.setMoney(1000);
			
			boolean estado = false;
			
			testDA.close();
			sut.open();
			try {
				estado = sut.bookRide(name, r, 1, 0);
			} catch(Exception e) {
				e.printStackTrace();
			}
	
			assertFalse(estado);
			
		} catch(Exception e) {
			fail();
			sut.close();
		}
		finally {
			sut.close();
		}
	}
	
	@Test
	public void test3() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=sdf.parse("01/01/2025");
			
			testDA.open();
			String name = null;
			String pass = "123";
			Ride r = testDA.createRide("", "", rideDate, 5, 20, null);
			Traveler t = testDA.addTraveler(name, pass);
			t.setMoney(1000);
			
			boolean estado = false;
			
			testDA.close();
			sut.open();
			try {
				estado = sut.bookRide(name, r, 0, 0);
			} catch(Exception e) {
				e.printStackTrace();
			}
	
			assertFalse(estado);
			
		} catch(Exception e) {
			fail();
			sut.close();
		}
		finally {
			sut.close();
		}
	}
	
	@Test
	public void test4() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=sdf.parse("01/01/2025");
			
			testDA.open();
			String name = null;
			String pass = "123";
			Ride r = testDA.createRide("", "", rideDate, 5, 20, null);
			Traveler t = testDA.addTraveler(name, pass);
			t.setMoney(1000);
			
			boolean estado = false;
			
			testDA.close();
			sut.open();
			try {
				estado = sut.bookRide(name, r, 1, -1);
			} catch(Exception e) {
				e.printStackTrace();
			}
	
			assertFalse(estado);
			
		} catch(Exception e) {
			fail();
			sut.close();
		}
		finally {
			sut.close();
		}
	}
	
	@Test
	public void test5() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=sdf.parse("01/01/2025");
			
			testDA.open();
			String name = "persona";
			String pass = "123";
			Ride r = testDA.createRide("", "", rideDate, 5, 20, null);
			Traveler t = testDA.addTraveler(name, pass);
			t.setMoney(1000);
			
			boolean estado = false;
			
			testDA.close();
			sut.open();
			try {
				estado = sut.bookRide("nadie", r, 1, 0);
			} catch(Exception e) {
				e.printStackTrace();
			}
	
			assertFalse(estado);
			
		} catch(Exception e) {
			fail();
			sut.close();
		}
		finally {
			sut.close();
		}
	}
	
	@Test
	public void test6() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;
			
		    try {
		        rideDate = sdf.parse("01/01/2025");
		    } catch (ParseException e) {
		        fail("Date parsing failed: " + e.getMessage());
		    }
			
			testDA.open();
			String name = "persona";
			String pass = "123";
			Ride r = testDA.createRide("", "", rideDate, 5, 20, null);
			Traveler t = testDA.addTraveler(name, pass);
			t.setMoney(1000);
			
			boolean estado;
			
			sut.open();
			
			System.out.println("Traveler: " + t.getUsername() + ", Money: " + t.getMoney());
			System.out.println("Ride: Price: " + r.getPrice() + ", Available Places: " + r.getnPlaces());
			
			estado = sut.bookRide(name, r, 1, 0);
	
			assertTrue(estado);
			
		} catch(Exception e) {
			fail();
		}
		finally {
			sut.close();
			testDA.close();
		}
	}
	

}
