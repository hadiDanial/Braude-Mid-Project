package controllers;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.IResultSetToObject;
import entities.discounts.Discount;
import entities.discounts.PercentageDiscount;
import entities.discounts.ValueDiscount;
import entities.products.CartItem;
import entities.products.CatalogItem;
import entities.users.Order;
import javafx.scene.chart.PieChart.Data;

public class DiscountController {
    private static DiscountController instance;
	private final DatabaseConnection databaseConnection;
    private static PercentageDiscount pd;
    private static ValueDiscount vd;    
    float discountedOrder=0;
	private static final String TABLE_NAME = "Discounts";
	private static final String DISCOUNTS_TABLE_NAME = "Orders_Discounts";
	private static final String ID_FIELD_NAME = "discountId";
	private IResultSetToObject<Discount> rsToDiscount;
	private static final String[] discountColumnNames =
	{ "discountId", "discountStartDate", "discountEndDate", "discountName", "discountValue", "discountType", "details", "orderDate",
			"deliveryDate" };
    private static final String[] discountsProductsColumnNames=
    { "catalogId","branchId","discountId"};
    private static final String[] ordersDiscountsColumnNames=
    { "orderId","discountId"};

    private DiscountController(){
        databaseConnection=DatabaseConnection.getInstance();
        rsToDiscount=new IResultSetToObject<Discount>() {

            @Override
            public Discount convertToObject(ResultSet rs) {
                try {
                    //"discountId", "discountStartDate", "discountEndDate", 
                    //"discountName", "discountValue", "discountType", "details", "orderDate",
			//"deliveryDate" }
                  if(rs.getString("discountType").equals(Discount.PERCENTAGE_DISCRIMINATOR))  {
                    Discount discount=Discount(Discount.PERCENTAGE_DISCRIMINATOR); 
                        discount.setDiscountId(rs.getInt(discountColumnNames[0]));
                        java.sql.Timestamp discountStartDate=rs.getTimestamp(discountColumnNames[1]);
                        discount.setDiscountStartDate((discountStartDate == null) ? null : discountStartDate.toInstant());
                        java.sql.Timestamp discountEndDate=rs.getTimestamp(discountColumnNames[2]);
                        discount.setDiscountEndDate((discountEndDate == null) ? null : discountEndDate.toInstant());
                        discount.setDiscountName(rs.getString(discountColumnNames[3]));
                        discount.setDiscountValue(rs.getInt(discountColumnNames[4]));
                       //anything else ?
                  }//whats the difference between percentage and value discounts here in initializing tables ?
                  else
                  {
                    Discount discount=Discount(Discount.VALUE_DISCRIMINATOR); 
                        discount.setDiscountId(rs.getInt(discountColumnNames[0]));
                        //about timstamps here ? java or sql wrapper
                        java.sql.Timestamp discountStartDate=rs.getTimestamp(discountColumnNames[1]);
                        discount.setDiscountStartDate((discountStartDate == null) ? null : discountStartDate.toInstant());
                        java.sql.Timestamp discountEndDate=rs.getTimestamp(discountColumnNames[2]);
                        discount.setDiscountEndDate((discountEndDate == null) ? null : discountEndDate.toInstant());
                        discount.setDiscountName(rs.getString(discountColumnNames[3]));
                        discount.setDiscountValue(rs.getInt(discountColumnNames[4]));
                  }
                } catch (Exception e) {
                    e.printStackTrace();
					return null;
                }
                return null;
            }
        };
    }
    protected Discount Discount(String percentageDiscriminator) {
        return null;
    }
    public static DiscountController getInstance()
	{
		if (instance == null)
		{
			instance = new DiscountController();
		}
		return instance;
	}
    public boolean createDiscount(ResultSet rs,Discount discount,int userId) throws SQLException{
        int res = databaseConnection.insertToDatabase(TABLE_NAME, discountsProductsColumnNames,
				new IObjectToPreparedStatementParameters<Discount>()
				{
                    //"discountId", "discountStartDate", "discountEndDate", 
                    //"discountName", "discountValue", "discountType", "details", "orderDate",
			//"deliveryDate" }
                    @Override
                    public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException {
                        statementToPrepare.setInt(1, discount.getDiscountId());
                        //both timestamps for startDate and endDate ?
                        statementToPrepare.setTimestamp(2,null);
                        statementToPrepare.setTimestamp(3,null);
						statementToPrepare.setString(4, discount.getDiscountName());
						statementToPrepare.setFloat(5,discount.getDiscountValue());
						                      
                    }
				});
                // do we do a loop here for every order we want to make discount for ??
                if(rs.getString("discountType").equals(Discount.PERCENTAGE_DISCRIMINATOR))
                {
                    discountedOrder=pd.applyDiscount(discount.getDiscountValue());
                }
                if(rs.getString("discountType").equals(Discount.VALUE_DISCRIMINATOR))
                {
                    discountedOrder=vd.applyDiscount(discount.getDiscountValue());
                }
                ArrayList<String> keys = new ArrayList<>();
		keys.add("totalCost");
		databaseConnection.updateById(userId, ID_FIELD_NAME, TABLE_NAME, keys,
				new IObjectToPreparedStatementParameters<Order>()
				{
					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setFloat(1,  discountedOrder);
					}
				});
                //do we update order table after discount ??
                
		return res == 1;
    }

    public boolean removeDiscount(Discount discount,int userId)
    {
        if(discount.getDiscountEndDate().equals(Instant.now()))
        {
            ArrayList<String> keys = new ArrayList<>();
            keys.add("totalCost");
            databaseConnection.updateById(userId, ID_FIELD_NAME, TABLE_NAME, keys,
                    new IObjectToPreparedStatementParameters<Order>()
                    {
                        @Override
                        public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
                        {
                            statementToPrepare.setFloat(1, Order.setTotalCost);
                            //how to get total cost pre discount ?
                        }
                    });
        }
        return false;
    }
}
