1. RunningScripts.java



package com.fresco.jdbc.code.util;



import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.Reader;

import java.sql.Connection;



import org.apache.ibatis.jdbc.ScriptRunner;





public class RunningScripts {

   public void runDbScript() throws Exception {

      

         ScriptRunner sr = new ScriptRunner(DbUtil.getConnection());

        //Creating a reader object

        Reader reader = new BufferedReader(new FileReader("./db.sql"));

        //Running the script

        sr.runScript(reader);

   }

}

DbOperations:

package com.fresco.jdbc.code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fresco.jdbc.code.util.DbUtil;

public class DbOperations {
Connection con;
public static final String INSERT_CATEGORY = "INSERT INTO category(type) VALUES (?)";
public static final String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
public static final String GET_ALL_CATEGORY = "SELECT * FROM category";

public static final String INSERT_PRODUCT = "INSERT INTO product(name,price,category_id) VALUES (?,?,"
+ "(SELECT id FROM category WHERE type = ?))";
public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
public static final String GET_ALL_PRODUCT = "SELECT * FROM product";

public static final String INSERT_ORDERS = "INSERT INTO orders(product_id,order_date) VALUES ("
+ "(SELECT id FROM product WHERE name = ?),?)";
public static final String GET_ORDERS_BY_ID = "SELECT * FROM orders WHERE id = ?";
public static final String GET_ALL_ORDERS = "SELECT * FROM orders";

public DbOperations(){
con =DbUtil.getConnection();
}
public boolean insertCategory(String type) throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(INSERT_CATEGORY);
pStatement.setString(1, type);
return pStatement.executeUpdate()>0;
}
public ArrayList getCategoryById(int id) throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(GET_CATEGORY_BY_ID);
pStatement.setInt(1, id);
ResultSet rSet = pStatement.executeQuery();
ArrayList arrayList = new ArrayList();
while(rSet.next()){
arrayList.add(rSet.getInt("id"));
arrayList.add(rSet.getString("type"));
}
return arrayList;
}
public ResultSet getAllCategory() throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(GET_ALL_CATEGORY);
return pStatement.executeQuery();
}
public boolean insertProduct(String name, float price, String type) throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(INSERT_PRODUCT);
pStatement.setString(1, name);
pStatement.setFloat(2, price);
pStatement.setString(3, type);
return pStatement.executeUpdate()>0;
}
public ArrayList getProductById(int id) throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(GET_PRODUCT_BY_ID);
pStatement.setInt(1, id);
ResultSet rSet = pStatement.executeQuery();
ArrayList arrayList = new ArrayList();
while(rSet.next()){
arrayList.add(rSet.getInt("id"));
arrayList.add(rSet.getString("name"));
arrayList.add(rSet.getFloat("price"));
arrayList.add(rSet.getInt("category_id"));
}
return arrayList;
}
public ResultSet getAllProduct() throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(GET_ALL_PRODUCT);
return pStatement.executeQuery();
}
public boolean insertOrder(String product_name, Date date) throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(INSERT_ORDERS);
pStatement.setString(1, product_name);
pStatement.setDate(2, date);
return pStatement.executeUpdate()>0;
}
public ArrayList getOrderById(int id) throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(GET_ORDERS_BY_ID);
pStatement.setInt(1, id);
ResultSet rSet = pStatement.executeQuery();
ArrayList arrayList = new ArrayList();
while(rSet.next()){
arrayList.add(rSet.getInt("id"));
arrayList.add(rSet.getInt("product_id"));
arrayList.add(rSet.getDate("date"));

}
return arrayList;
}
public ResultSet getAllOrder() throws SQLException {
PreparedStatement pStatement= this.con.prepareStatement(GET_ALL_ORDERS);
return pStatement.executeQuery();
}
}