package testBookRide;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataaccess.DataAccess;
import domain.Ride;
import domain.Traveler;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import testOperations.TestDataAccess;
import domain.Driver;

public class BookRideDBWhiteTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver; 

	
	@Test
		public void test1() {
		
			try {
				
				testDA.open();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date rideDate=null;;
				try {
					rideDate = sdf.parse("05/10/2026");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				Driver d = testDA.createDriver("d", "1");
				Ride r = testDA.createRide("from", "to", rideDate, 5, 20, d.getUsername());
				
				Traveler t = testDA.addTraveler("nombre", "123");
				t.setMoney(1000);
				
				testDA.close();
				sut.open();
				
				boolean estado = sut.bookRide(t.getUsername(),r , 1, 10);
				
				assertTrue(estado);
				
				} catch (Exception e) {
					fail();
					
				} finally {
					sut.close();
				}
	}
	
	@Test
		public void test2() {
		
			try {
				
				testDA.open();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date rideDate=null;
				try {
					rideDate = sdf.parse("05/10/2026");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				Driver d = testDA.createDriver("d", "1");
				Ride r = null;
				
				Traveler t = testDA.addTraveler("nombre", "123");
				t.setMoney(1000);
				
				testDA.close();
				sut.open();
				
				boolean estado = sut.bookRide(t.getUsername(), r, 1, 10);
				
				assertFalse(estado);
				
				} catch (Exception e) {
					fail();
					
				} finally {
					sut.close();
				}
	}
	
	@Test
		public void test3() {
		
			try {
				
				testDA.open();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date rideDate=null;;
				try {
					rideDate = sdf.parse("05/10/2026");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				Driver d = testDA.createDriver("d", "1");
				Ride r = testDA.createRide("from", "to", rideDate, 5, 20, null);
				
				Traveler t = testDA.addTraveler("nombre", "123");
				t.setMoney(1000);
				
				testDA.close();
				sut.open();
				
				boolean estado = sut.bookRide("nadie", r, 1, 10);
				
				assertFalse(estado);
				
				} catch (Exception e) {
					fail();
					
				} finally {
					sut.close();
				}
	}
	
	@Test
		public void test4() {
		
			try {
				
				boolean correcto = true;
				int seats = 5;
				testDA.open();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date rideDate=null;;
				try {
					rideDate = sdf.parse("05/10/2026");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				Driver d = testDA.createDriver("d", "1");
				Ride r = testDA.createRide("from", "to", rideDate, 1, 20, null);
				
				Traveler t = testDA.addTraveler("nombre", "123");
				t.setMoney(1000);
				
				testDA.close();
				sut.open();
				
				if(seats<10) {
					correcto = false;
				}
				boolean estado = sut.bookRide(t.getUsername(), r, seats, 10);
				
				assertFalse(estado);
				assertFalse(correcto);
				
				} catch (Exception e) {
					fail();
					
				} finally {
					sut.close();
				}
	}
	
	@Test
	public void test5() {
	
		try {
			
			testDA.open();
			boolean correcto = true;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date rideDate=null;;
			try {
				rideDate = sdf.parse("05/10/2026");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			Driver d = testDA.createDriver("d", "1");
			Ride r = testDA.createRide("from", "to", rideDate, 1, 0, null);
			
			Traveler t = testDA.addTraveler("nombre", "123");
			t.setMoney(1);
			
			testDA.close();
			sut.open();
			
			if(1 <= (20-0)*1) {
				correcto = false;	
			}
			
			boolean estado = sut.bookRide(t.getUsername(), r, 1, 10);
			
			assertFalse(correcto);
			assertFalse(estado);
			
			} catch (Exception e) {
				fail();
				
			} finally {
				sut.close();
			}
}
}