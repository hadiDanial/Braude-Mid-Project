package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DatabaseConnection;
import database.Tables;
import entities.other.Branch;
import entities.other.Location;
import entities.products.CartItem;
import entities.products.CatalogItem;
import entities.products.Item;
import entities.products.Product;
import entities.users.Order;
import entities.users.Delivery;
import entities.users.User;
import enums.AccountStatus;
import enums.ColorEnum;
import enums.OrderStatus;
import enums.UserRole;
import javafx.scene.chart.PieChart.Data;

public class OrderControllerTest
{
	DatabaseConnection databaseConnection;
	
	/** 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		DatabaseConnection.getInstance().connectToDB("localhost", "zlig13", "root", "6plle2nmfr4m");
	}

	
	/** 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		DatabaseConnection.getInstance().disconnect();
	}

 	@Test
 	public void test()
 	{
 		testGetCatalogByBranch();
 		testLogin();
 		testLogout();
 		testRegister();
 		testAddOrder();
 		testGetPendingOrders();
 		assert(true);
 	}

 	private void testGetPendingOrders()
 	{
 		ArrayList<Order> pending = OrderController.getInstance().getOrdersByStatusAndBranch(2, OrderStatus.Pending); 
 		for (Order order : pending)
 		{
 			System.out.println(order);
 			for (CartItem cartItem : order.getProducts())
 			{
 				System.out.println("x" + cartItem.getQuantity() + ": " + cartItem.getCatalogItem());
 			}
 		}
 	}

 	private void testGetCatalogByBranch()
 	{
 		System.out.println(ProductController.getInstance().getCatalogByBranch(1));
 	}

 	private void testRegister()
 	{
 		User newUser = new User("Amr", "amrDaMan", "Amr", "Kalany", "amr@gmail.com", "043142134",
 		UserRole.CustomerServiceSpecialist, AccountStatus.Pending, 5000);
 		UserController.getInstance().register(newUser);
		User expectedUser;
		ResultSet expected;
		expected=databaseConnection.getByID(newUser.getUserId(), Tables.USERS_TABLE_NAME, "userId");
		expectedUser=UserController.getInstance().convertRSToUser(expected);
		int id=newUser.getUserId();
		try 
		{
			assertEquals(expected.getString(Tables.usersColumnNames[0]),id+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}

 	private void testLogout()
 	{
		boolean expected;
 		UserController.getInstance().logout(1);
		try{
		ResultSet rs=databaseConnection.getByID(1, Tables.USERS_TABLE_NAME,"isLoggedIn");
		expected =rs.getBoolean(Tables.usersColumnNames[10]);
		assertFalse(expected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		UserController.getInstance().logout(2);
 	}
	@Test
 	public void testLogin()
 	{
		boolean expected;
		User user=new User("Amr", "123", "Amr", "Kalany", "AmrKal@gmail.com", "0504707027",
		UserRole.Customer, AccountStatus.Active, 0);
		UserController.getInstance().login(user.getUsername(),user.getPassword());
	   	try{
	   	ResultSet rs=databaseConnection.getByID(1, Tables.USERS_TABLE_NAME,"isLoggedIn");
	   	expected =rs.getBoolean(Tables.usersColumnNames[10]);
		assertTrue(expected);
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
 		System.out.println("Should be null: " + UserController.getInstance().login("Hadi", "test"));
 		System.out.println("Should be null: " + UserController.getInstance().login("test", "bestpassword123"));
 		System.out.println("Should be Hadi User: " + UserController.getInstance().login("Hadi", "123"));
 		System.out
 				.println("Should be Yosef User: " + UserController.getInstance().login("Yosef", "123"));
 	}

 	private void testAddOrder()
 	{
 		Order order = new Order();
 		Location location = new Location("Haifa", 1234, "123 Street", "");
 		location.setLocationId(1);
 		User user = new User();
 		user.setUserId(1);
 		user.setEmailAddress("hadi.dan@me.com");
 		order.setCustomer(user);
 		order.setDeliveryDetails(new Delivery(order, "Hadi", "0525614352", location));
 		order.setGreetingCard("Hello noob");
 		order.setOrderDate(Instant.now());
 		order.setDeliveryDate(Instant.now().plusSeconds(6000000));
 		order.setTotalCost(500);
 		order.setOrderStatus(OrderStatus.Pending);
 		Branch branch = new Branch();
 		branch.setBranchId(1);
 		order.setBranch(branch);
 		ArrayList<CartItem> cart = new ArrayList<>();
 		CartItem item1 = new CartItem();
 		item1.setQuantity(5);
 		Product p = new Product();
 		p.setProductId(3);
 		p.setProductName("Cactus");
 		p.setPrice(20);
 		CartItem item2 = new CartItem();
 		item2.setQuantity(3);
 		Item i = new Item();
 		i.setProductId(1);
 		i.setProductName("Rose");
 		i.setPrice(15);
 		item1.setCatalogItem(new CatalogItem(p, branch, 10));
 		item2.setCatalogItem(new CatalogItem(i, branch, 17));
 		cart.add(item1);
 		cart.add(item2);
 		order.setProducts(cart);
 		OrderController.getInstance().createNewOrder(order);
 	}

}
