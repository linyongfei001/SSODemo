package aura.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * 使用HIVE JDBC API的方式操作Hive数据仓库
 * 使用注意：
 * 1、要导入hive依赖jar包
 * 2、要导入mysql链接驱动jar包
 * 3、要导入操作hdfs的jar包
 */
public class HiveJDBCAPIDemo {
	
	// jdbc方式操作hive的驱动
	private static final String HIVE_DRIVER = "org.apache.hive.jdbc.HiveDriver";
	// jdbc方式操作mysql（关系型数据库）驱动（没有用到，写在这儿，就是给大家做个对比）
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";    
	
	// hive连接url(格式：jdbc:hive2://hostname:port/database_name)
	// hostname是你启动hiveserver2的机器，port是默认端口10000, database_name是数据库名
	private static final String HIVE_CONNECT_URL = "jdbc:hive2://hadoop01:10000/dbtest";
	// mysql链接字符串（没有用到，写在这儿，就是给大家做个对比）
	private static final String MYSQL_CONNECT_URL = "jdbc:mysql://hadoop01:3306/mydb";
	
	// 用户名和密码
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	// 数据库
	private static final String HIVE_DATABASE = "hive_test_db";
	
	// 表
	private static final String HIVE_TABLE = "hive_test_table";
	
	// 表字段
	private static final String[][] HIVE_TABLE_ATTR_ARRAY = new String[][]{
		new String[]{"id","name","sex","age","department"},
		new String[]{"int","string","string","int","string"}
	};
	
	// 列分隔符
	private static final String HIVE_TABLE_DELIMITER_DEFAULT = ",";
	// 集合分隔符
	private static final String HIVE_TABLE_COLLECTION_DELIMITER_DEFAULT = ":";
	// map分隔符
	private static final String HIVE_TABLE_MAP_DELIMITER_DEFAULT = "-";
	
	// 文件存储格式
	private static final String HIVE_TABLE_FILE_FORMAT_DEFAULT = "textfile";
	
	// 文件路径
	private static final String FILE_PATH = "/home/hadoop/studentss.txt";
	
	private static Connection connection = null;
	private static Statement statement = null;

	public static void main(String[] args) throws Exception {
		
		// 测试字段数据
		printTwoMatrix(HIVE_TABLE_ATTR_ARRAY);
		
		// hive初体验
//		testFirtHiveConnect();
		
		// 正式hive测试
		testHive();
	}
	
	
	/**
	 * hive主测试方法
	 */
	public static void testHive() throws Exception{
		// 初始化连接
		connection = getHiveConnection();
		statement = getStatement(connection);
		
		// 创建数据库表
		createDatabase(HIVE_DATABASE);
		
		// 删除数据库
		dropDatabase(HIVE_DATABASE);
		
		// 创建表
		createHiveTable(HIVE_TABLE, HIVE_TABLE_ATTR_ARRAY, HIVE_TABLE_DELIMITER_DEFAULT, HIVE_TABLE_FILE_FORMAT_DEFAULT);
		
		// 查看表
		showTables(HIVE_TABLE);
		showTables(null);
		
		// 往表中加载数据
		loaddata(HIVE_TABLE, FILE_PATH);
		
		// 查询数据
		selectData(HIVE_TABLE);
		
		// 删除表
		dropHiveTable(HIVE_TABLE);
		
		// 连接关闭
		close(connection);
	}
	
	/**
	 * 查询数据
	 */
	public static void selectData(String table) throws Exception{
		// 查询该表有哪些字段
		List<String> attrList = descTable(table);
		
		// 根据查询出来的表的字段拼凑sql语句
		// 最终效果
		// String select_sql = "select id, name, sex, age, department from hive_test_table"
		StringBuffer select_sql = new StringBuffer("select ");
		int attrListLength = attrList.size();
		for(int i=0;i<attrListLength;i++){
			select_sql.append(attrList.get(i));
			if(i != attrListLength - 1){
				select_sql.append(", ");
			}
		}
		select_sql.append(" from ");
		select_sql.append(table);
		
		// 执行sql语句
		ResultSet rs = statement.executeQuery(select_sql.toString());
		
		// 遍历输出查询结果
		while(rs.next()){
			for(int i=0;i<attrListLength;i++){
				System.out.print(rs.getObject(attrList.get(i))+"\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 往hive表导入数据
	 * 注意：path是你启动的hiveserver2服务的那台机器的哪个用户名的家目录
	 * 也就是说， path = hadoop02(我的hiveserver2服务启动机器)的用户hadoop的家目录
	 * 第一种方式：/home/hadoop/student.txt(绝对路径写法)
	 * 第二种方式：student.txt(相对路径写法，也就是相对/home/hadoop/这个目录而言)
	 */
	public static void loaddata(String table, String path) throws Exception{
		String loaddata_sql = "load data local inpath '"+path+"' into table "+table;
		int executeUpdate = statement.executeUpdate(loaddata_sql);
		System.out.println((executeUpdate == 0 ? "往"+table+"表导入数据"+path+"成功":"往"+table+"表导入数据"+path+"失败"));
	}
	
	// 查看表
	public static void showTables(String table) throws Exception{
		String show_tables_sql = "";
		if(!StringUtils.isBlank(table)){
			show_tables_sql = "show tables '"+table+"'";
		}else{
			show_tables_sql = "show tables";
		}
		ResultSet rs = statement.executeQuery(show_tables_sql);
		while(rs.next()){
			System.out.println("表名："+rs.getString(1));
		}
	}
	
	public static List<String> descTable(String table) throws Exception{
		String desc_table_sql = "desc "+table;
		ResultSet rs = statement.executeQuery(desc_table_sql);
		List<String> strList = new ArrayList<String>();
		while(rs.next()){
			String attr = rs.getString(1);
			System.out.println("表字段："+ attr);
			strList.add(attr);
		}
		return strList;
	}
	
	/**
	 * 删除hive表
	 */
	public static void dropHiveTable(String table) throws Exception{
		String drop_table_sql = "drop table "+table;
		int executeUpdate = statement.executeUpdate(drop_table_sql);
		System.out.println(executeUpdate+"  ---  "+(executeUpdate == 0 ? "删除"+table+"表成功":"删除"+table+"表失败"));
	}
	
	/**
	 * 创建hive表
	 */
	public static void createHiveTable(String table, String[][] attrArray, String delimiter, String fileFormat) throws Exception{
		// 直接手写全方式
		/*String create_hive_table_sql = "create table "+table+"(id int, name string, sex string, "
				+ "age int, department string) "
				+ "row format delimited fields terminated by ','";*/
		
		// 拼建表语句
		StringBuffer create_hive_table_sql = new StringBuffer("create table ");
		create_hive_table_sql.append(table);
		create_hive_table_sql.append("(");
		
		int totalAttrLength = attrArray[0].length;
		for(int i=0;i<totalAttrLength;i++){
			create_hive_table_sql.append(attrArray[0][i]+" "+attrArray[1][i]);
			if(i != totalAttrLength - 1){
				create_hive_table_sql.append(",");
			}
		}
		create_hive_table_sql.append(") row format delimited fields terminated by '");
		create_hive_table_sql.append(delimiter);
		create_hive_table_sql.append("' stored as ");
		create_hive_table_sql.append(fileFormat);
		
		int executeUpdate = statement.executeUpdate(create_hive_table_sql.toString());
		System.out.println(executeUpdate+"  ---  "+(executeUpdate == 0 ? "创建"+table+"表成功":"创建"+table+"表失败"));
	}
	
	/**
	 * 创建库
	 */
	public static void createDatabase(String database) throws Exception{
		String create_database_sql = "create database "+database;
		int executeUpdate = statement.executeUpdate(create_database_sql);
		System.out.println(executeUpdate == 0 ? "数据库"+database+"创建成功":"数据库"+database+"创建失败");
	}
	
	/**
	 * 删除库
	 */
	public static void dropDatabase(String database) throws Exception{
		String drop_database_sql = "drop database "+database;
		int executeUpdate = statement.executeUpdate(drop_database_sql);
		System.out.println(executeUpdate == 0 ? "数据库"+database+"删除成功":"数据库"+database+"删除失败");
	}
	
	/**
	 * 获取hive连接
	 */
	public static Connection getHiveConnection() throws Exception{
		// 注册驱动
		Class.forName(HIVE_DRIVER);
		// 获取hive连接
		Connection connection = DriverManager.getConnection(HIVE_CONNECT_URL, USER, PASSWORD);
		return connection;
	}
	
	/**
	 * 获取statement
	 */
	public static Statement getStatement(Connection conn) throws Exception{
		return conn.createStatement();
	}
	
	/**
	 * 执行完成，关闭链接
	 */
	public static void close(Connection conn) throws Exception{
		if(null != conn){
			conn.close();
		}
	}
	
	/**
	 * hive jdbc 初体验
	 */
	public static void testFirtHiveConnect() throws Exception{
		// 注册驱动
		Class.forName(HIVE_DRIVER);
		// 获取hive连接
		Connection connection = DriverManager.getConnection(HIVE_CONNECT_URL, USER, PASSWORD);
		Statement pstmt = connection.createStatement();
		ResultSet rs = pstmt.executeQuery("show tables");
		while(rs.next()){
			System.out.println(rs.getObject(1));
		}
	}
	
	/**
	 * 打印二维数组
	 */
	public static void printTwoMatrix(String[][] attrArray){
		for(int i=0;i<attrArray.length;i++){
			for(int j=0;j<attrArray[0].length;j++){
				System.out.print(attrArray[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
