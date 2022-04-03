package reviewoski.jdbc;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWithOracle {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @BeforeMethod
    public void connect() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@18.234.167.95:1521:xe";
        String dbUserName = "hr";
        String dbPassword="hr";
        String query = "select first_name,last_name,salary from employees";

        connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
    }
    @AfterMethod
    public void closeDb() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void connectionTest() throws SQLException {


        //resultSet.next(); gets the cursor to nest row

        //resultSet.getObject(1): brings info in that cell;

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
        }
    }
    @Test
    public void verifyExample() throws SQLException {
        //get steve king salary and verify that it is 24000
        resultSet.next(); //at first query stands at the line zero
       //int actualSalary = resultSet.getInt(3);
        //int expectedSalary = 24000;
        String actualSalary = resultSet.getString(3);
        String expectedSalary = "24000";
        System.out.println("actualSalary = " + actualSalary);
        System.out.println("expectedSalary = " + expectedSalary);

        Assert.assertEquals(actualSalary,expectedSalary);

    }
    @Test
    public void listOfMapExample(){
        Map<String,Object> rowOneData = new HashMap<>();
        rowOneData.put("first_name","Steven");
        rowOneData.put("last_name","King");
        rowOneData.put("salary","24000");
        System.out.println("rowOneData = " + rowOneData);

        Map<String,Object> rowTwoData = new HashMap<>();
        rowTwoData.put("firstname","Neena");
        rowTwoData.put("lastname","Kochhar");
        rowTwoData.put("salary","17000");
        System.out.println("rowTwoData = " + rowTwoData);

        List<Map<String,Object>> list = new ArrayList<>();

        list.add(rowOneData);
        list.add(rowTwoData);

        //neena salary
        System.out.println("get neena salary"+ list.get(1).get("salary"));

    }
    @Test
    public void MetaDataExample() throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        String columnName = rsmd.getColumnName(1);
        System.out.println("columnCount = " + columnCount);
        System.out.println("columnName = " + columnName);

    }

    @Test
    public void DynamicMapMethod() throws SQLException {

        List<Map<String,Object>> queryResultList = new ArrayList<>();
        // row: resultset.next
        // column count: rsmd.getcolumncount
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columncount = rsmd.getColumnCount();
        //KEY: column name: rsmd.getcolumnName
        //Value: how to read data from cell : resultset.getobject

        while (resultSet.next()){
            Map<String,Object> rowMap = new HashMap<>();
            for (int i = 1; i <= columncount ; i++) {
                 rowMap.put(rsmd.getColumnName(i),resultSet.getObject(i));

            }
            queryResultList.add(rowMap);
        }

        for (Map<String, Object> eachRow : queryResultList) {
            System.out.println("eachRow = " + eachRow);
        }
    }
}
