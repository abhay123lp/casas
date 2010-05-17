<%@ page language="java"
	import="java.util.*,javax.naming.*,javax.sql.DataSource,java.sql.*"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
		This is my JSP page.
		<br>
		<%
			DataSource ds = null;
			Connection con = null;
			PreparedStatement pr = null;
			InitialContext ic;
			try {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:/MySqlDS");
				con = ds.getConnection();
				int isolation = con.getMetaData()
						.getDefaultTransactionIsolation();
				out.println("The default Isolation level is: ");
				switch (isolation) {
				case Connection.TRANSACTION_READ_UNCOMMITTED:
					out.print("READ_UNCOMMITTED");
					break;
				case Connection.TRANSACTION_READ_COMMITTED:
					out.print("READ_COMMITTED");
					break;
				case Connection.TRANSACTION_REPEATABLE_READ:
					out.print("REPEATABLE_READ");
					break;
				case Connection.TRANSACTION_SERIALIZABLE:
					out.print("SERIALIZABLE");
					break;
				default:
					out.print("NONE");
				}
			} catch (Exception e) {
				out.println("Exception thrown " + e);
			} finally {
				if (con != null) {
					con.close();
				}
			}
		%>
	</body>
</html>
