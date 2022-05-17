package controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DatabaseConnection;
import entities.other.Branch;
import entities.other.Location;
import entities.products.CartItem;
import entities.products.CatalogItem;
import entities.products.Item;
import entities.products.Product;
import entities.users.Order;
import entities.users.OrderDelivery;
import entities.users.User;
import enums.AccountStatus;
import enums.ColorEnum;
import enums.OrderStatus;
import enums.UserRole;

class OrderControllerTest
{

	@BeforeEach
	void setUp() throws Exception
	{
		DatabaseConnection.getInstance().connectToDB("localhost", "zlig13", "root", "mYtsb46Ql97");
	}

	@AfterEach
	void tearDown() throws Exception
	{
		DatabaseConnection.getInstance().disconnect();
	}

	@Test
	void test()
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
		ArrayList<Order> pending = OrderController.getInstance().getPendingOrders(2); 
		for (Order order : pending)
		{
			System.out.println(order);
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
	}

	private void testLogout()
	{
		UserController.getInstance().logout(1);
		UserController.getInstance().logout(2);
	}

	private void testLogin()
	{
		System.out.println("Should be null: " + UserController.getInstance().login("Hadi", "test"));
		System.out.println("Should be null: " + UserController.getInstance().login("test", "bestpassword123"));
		System.out.println("Should be Hadi User: " + UserController.getInstance().login("Hadi", "bestpassword123"));
		System.out
				.println("Should be Yosef User: " + UserController.getInstance().login("Yosef", "bestpassword456"));
	}

	private void testAddOrder()
	{
		Order order = new Order();
		order.setColor(ColorEnum.Blue);
		Location location = new Location("Haifa", 1234, "123 Street", "", "");
		location.setLocationId(1);
		User user = new User();
		user.setUserId(1);
		user.setEmailAddress("hadi.dan@me.com");
		order.setCustomer(user);
		order.setDeliveryDetails(new OrderDelivery(order, "Hadi", "0525614352", location));
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
