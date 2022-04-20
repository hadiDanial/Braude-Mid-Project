import java.util.ArrayList;

public class Main
{

	public static void mainTest(String[] args)
	{
		Controller controller = new Controller();
		User u1 = new User("Manager", Role.Manager);   
		User u2 = new User("CEO", Role.CEO);           
		User u3 = new User("Worker", Role.Worker);     
		User u4 = new User("Customer", Role.Customer); 
		controller.addUser(u1);
		controller.addUser(u2);
		controller.addUser(u3);
		controller.addUser(u4);
		
		try
		{
			ArrayList<User> users;
			users = controller.getAllUsers(u1);
			System.out.println(u1 + " called getAllUsers and got: " + users);
			users = controller.getAllUsers(u2);
			System.out.println(u2 + " called getAllUsers and got: " + users);
			users = controller.getAllUsers(u4);
			System.out.println(u4 + " called getAllUsers and got: " + users);
			users = controller.getAllUsers(u3);
			System.out.println(u3 + " called getAllUsers and got: " + users);
		} catch (RoleNotAllowedException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
