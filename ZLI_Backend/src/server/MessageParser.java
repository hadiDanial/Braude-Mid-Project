package server;

import org.json.simple.JSONObject;

import database.DBController;
import entities.Order;
import ocsf.server.ConnectionToClient;

public class MessageParser {

	/**
	 * 
	 * @param msg
	 * @throws Exception
	 */
	public static void parseMessage(Object msg, ConnectionToClient client) throws Exception {
		JSONObject message = (JSONObject) msg;

		JSONObject data = (JSONObject) message.get("data");

		switch ((String) message.get("command")) {
			case "ADD_NEW_ORDER_TO_DB": // ! will change to be an enum
				if (data.get("type").equals("Order")) {
					Order order = new Order((int) data.get("orderID"), (float) data.get("totalCost"),
							(String) data.get("greetingCard"),
							(String) data.get("color"), (String) data.get("dOrder"), (String) data.get("branch"),
							(String) data.get("deliveryDate"),
							(String) data.get("orderDate"));
					DBController.sendNewOrderToDB(order);
				}
				break;
			case "GET_ORDER_FROM_DB":// ! will change to be an enum
				Order returnOrder = DBController.getOrderFromDB("123");
				generateMessage("RETURN_ORDER", returnOrder.generateJsonObject(), client);
				break;
			case "UPDATE_ORDER_COLOR_DATE_IN_DB":
				DBController.updateColorDateOrderInDB(data.get("orderID").toString(), (String) data.get("color"),
						(String) data.get("orderDate"));
				break;
			case "UPDATE_ORDER_DATE_IN_DB":
				DBController.updateDateOrderInDB(data.get("orderID").toString(), (String) data.get("orderDate"));
				break;
			case "UPDATE_ORDER_COLOR_IN_DB":
				DBController.updateColorOrderInDB(data.get("orderID").toString(), (String) data.get("color"));
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 * @param message
	 */
	public static void generateMessage(String command, Object data, ConnectionToClient client) {
		JSONObject jsonObject = new JSONObject();

		switch (command) {
			case "RETURN_ORDER":
				jsonObject.put("command", "RETURN_ORDER");
				jsonObject.put("data", data);
				Server.getInstance().sendToClient(jsonObject, client);
				break;

			default:
				break;
		}
	}

}