package testGauzatuEragiketa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import domain.Driver;
import domain.User;

public class gauzatuEragiketaMockBlackTest {

static DataAccess sut;
	
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
	
	
	Driver driver;
	
	@Test
	public void test1() {
		boolean operacion;
		try {
			String  userName = "mikel";
			double amount = 10.0;
			boolean deposite = true;
			double expectedMoney=amount;
			boolean correct=false;
			Driver driver1=new Driver(userName,"jajaja");
			TypedQuery<User> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", userName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(driver1);
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			if(driver1.getMoney()==expectedMoney) {
				correct=true;
			}
			assertTrue(correct);
			assertTrue(operacion);
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
			double wallet=11.0;
			boolean deposite = false;
			double expectedMoney=wallet-amount;
			boolean correct=false;
			Driver driver1=new Driver(userName,"jajaja");
			driver1.setMoney(wallet);
			TypedQuery<User> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", userName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(driver1);
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			if(driver1.getMoney()==expectedMoney) {
				correct=true;
			}
			assertTrue(correct);
			assertTrue(operacion);
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
		}
	}
	
	
	@Test
	public void test3() {
		boolean operacion;
		try {
			String  userName = "mikel";
			double amount = 10.0;
			double wallet=9.0;
			boolean deposite = false;
			Driver driver1=new Driver(userName,"jajaja");
			driver1.setMoney(wallet);
			TypedQuery<User> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", userName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(driver1);
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
	public void test4() {
		boolean operacion;
		try {
			String  userName = null;
			double amount = 10.0;
			boolean deposite = true;
			TypedQuery<User> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", userName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(null);
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
		try {
			String  userName = "mikel";
			double amount = -8;
			boolean deposite = true;
			Driver driver1=new Driver(userName,"jajaja");
			TypedQuery<User> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", userName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(driver1);
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, deposite);
			System.out.println(driver1.getMoney());
			
			assertFalse(operacion);
		}catch(Exception e) {
			fail();
		}finally {
			sut.close();
		}
	}
	
	
	@Test
	public void test6() {
		boolean operacion;
		try {
			String  userName = "mikel";
			double amount = 10.0;
			Driver driver1=new Driver(userName,"jajaja");
			TypedQuery<User> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter("username", userName)).thenReturn(queryMock);
			Mockito.when(queryMock.getSingleResult()).thenReturn(driver1);
			sut.open();
			operacion= sut.gauzatuEragiketa(userName, amount, (Boolean) null);
			
			assertFalse(operacion);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(false);
		}finally {
			sut.close();
		}
	}
	
}
