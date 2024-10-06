package testBookRide;
import static org.junit.Assert.*;
import domain.Booking;
import domain.Ride;
import domain.Traveler;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataaccess.DataAccess;

public class BookRideMockWhiteTest {
	
	DataAccess sut;

	protected MockedStatic<Persistence> persistenceMock;

	@Mock
	protected  EntityManagerFactory entityManagerFactory;
	@Mock
	protected  EntityManager db;
	@Mock
    protected  EntityTransaction  et;
	

	@Before
    public  void init() {
        MockitoAnnotations.openMocks(this);
        persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() -> Persistence.createEntityManagerFactory(Mockito.any()))
        .thenReturn(entityManagerFactory);
        
        Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccess(db);
    }
	@After
    public  void tearDown() {
		persistenceMock.close();
    }
	
	@Test
	public void test1() {
	    try {
	    	
			String travellerName = null;
			
			TypedQuery<Traveler> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT t FROM Traveller t WHERE t.username = :username", Traveler.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", travellerName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(null);
			
			sut.open();
			boolean estado = sut.bookRide(travellerName, null, 0, 0);
			
			assertFalse(estado);
			
	    } catch(Exception e) {
	    	fail();
	    	sut.close();
	    } finally {
	    	sut.close();
	    }
    }
	
	@Test
	public void test2() {
		try {
	    	
			String travellerName = "tnombre";
			
			TypedQuery<Traveler> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT t FROM Traveller t WHERE t.username = :username", Traveler.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", travellerName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(null);
			
			sut.open();
			boolean estado = sut.bookRide(travellerName, null, 0, 0);
			
			assertFalse(estado);
			
	    } catch(Exception e) {
	    	fail();
	    	sut.close();
	    } finally {
	    	sut.close();
	    }
	}
	
	@Test
	public void test3() {
		try {
			
			boolean correcto = true;
	    	
			String travellerName = "tnombre";
			Traveler traveler1 = new Traveler("tnombre", "123");
			traveler1.setMoney(1000);
			
			Ride r = new Ride("", "", null, 0, 1, null);
			
			TypedQuery<Traveler> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT t FROM Traveller t WHERE t.username = :username", Traveler.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", travellerName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(traveler1);
			
			sut.open();
			int seats = 1;
			boolean estado = sut.bookRide(travellerName, r, seats, 0);
			if(seats > (r.getnPlaces())) {
				correcto = false;
			}
			
			assertFalse(correcto);
			assertFalse(estado);
			
	    } catch(Exception e) {
	    	fail();
	    	sut.close();
	    } finally {
	    	sut.close();
	    }
	}
	
	@Test
	public void test4() {
		try {
			boolean correcto = false;
	    	
			String travellerName = "tnombre";
			Traveler traveler1 = new Traveler("tnombre", "123");
			traveler1.setMoney(10);
			
			Ride r = new Ride("", "", null, 100, 1, null);
			
			TypedQuery<Traveler> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT t FROM Traveller t WHERE t.username = :username", Traveler.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", travellerName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(traveler1);
			
			sut.open();
			int num = 0;
			int seats = 1;
			boolean estado = sut.bookRide(travellerName, r, seats, num);
			if(traveler1.getMoney() < (r.getPrice()-num)*seats) {
				correcto = true;
			}
			
			assertFalse(estado);
			assertFalse(correcto);
			
	    } catch(Exception e) {
	    	fail();
	    	sut.close();
	    } finally {
	    	sut.close();
	    }
	}
	
	@Test
	public void test5() {
		try {
			boolean correcto = false;
	    	
			String travellerName = "tnombre";
			Traveler traveler1 = new Traveler("tnombre", "123");
			traveler1.setMoney(1000);
			
			Ride r = new Ride("", "", null, 100, 1, null);
			
			TypedQuery<Traveler> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT t FROM Traveller t WHERE t.username = :username", Traveler.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", travellerName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(traveler1);
			
			sut.open();
			int num = 0;
			int seats = 1;
			boolean estado = sut.bookRide(travellerName, r, seats, num);
			if(traveler1.getMoney() < (r.getPrice()-num)*seats) {
				correcto = true;
			}
			
			if(seats > (r.getnPlaces())) {
				correcto = false;
			}
			
			assertFalse(estado);
			assertFalse(correcto);
			
	    } catch(Exception e) {
	    	fail();
	    	sut.close();
	    } finally {
	    	sut.close();
	    }
	}
}
