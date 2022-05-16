package controllers;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.IResultSetToObject;
import entities.discounts.Discount;
import entities.discounts.PercentageDiscount;
import entities.discounts.ValueDiscount;
import entities.users.Order;
import javafx.scene.chart.PieChart.Data;

public class DiscountController {
    private static DiscountController instance;
	private final DatabaseConnection databaseConnection;
    private static PercentageDiscount pd;
    private static ValueDiscount vd;    
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
    public boolean createDiscount(ResultSet rs,Discount discount) throws SQLException{
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
                    pd.applyDiscount(discount.getDiscountValue());
                }
                if(rs.getString("discountType").equals(Discount.VALUE_DISCRIMINATOR))
                {
                    vd.applyDiscount(discount.getDiscountValue());
                }
                //do we update order table after discount ??
		return res == 1;
    }
}
