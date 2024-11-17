<%@page import="java.util.ArrayList"%>
<%@page import="org.model.ProductDetails"%>
<%@page import="java.util.List"%>
<%@page import="org.DAO.UpwardDAOimpl"%>
<%@page import="org.DAO.UpwardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
*{
	padding:0px;
	margin:0px;
	
}
body{
	width:100vw;
	height:100vh;
	overflow: hidden;
	
}
#header{
width:100vw;
height:28vh;
border: 1px solid black;
}
#container{
	width: 100vw;
	height:71vh;
	
}
.child{
	width: 79.8vw;
	height: 100%;
	border: 0.5px solid black;
	float: left;
}
#leftchild{
	width: 19.8vw;
	display: flex;
	flex-direction:column;
	justify-content:space-around;
	align-items: center;

	
}
#home{

	position: absolute;
	left:22vw;
	font-size: 2vw;
	
}table{
	position: relative;
	left:20vw;
	top:7vw;
}
table,th,td{
	border: 1px solid black;
	border-collapse: collapse;
}#forms{

position: relative;
left:10vw;
top:5vw;
}
.search{
position: relative;
left:12vw;
top: 2vw;
width: 7vw;
height: 2vw;
background-color:transparent;
border:2px solid navy;
border-radius:5px;
font-size:15px;
text-transform:capitalize;
}
#export{
position: relative;
left:21vw;
top: 0.1vw;
}
.export{
width: 9vw;
height: 2vw;
background-color:transparent;
border:2px solid navy;
border-radius:5px;
font-size:15px;
text-transform:capitalize;
}
#content{
width:100px;
height:25px;
background-color:transparent;
border:2px solid navy;
border-radius:5px;
font-size:15px;
text-transform:capitalize;
}
</style>

</head>
<body>
<nav id="header">
<center>
<img alt="" src="https://upwardiq.com/assets/img/upwardiq_logo-removebg-preview.png"style="width:800px;height:200px;">
</center>
</nav>
<div id="container">
<div id="leftchild" class="child">
<h1><a href="UploadProduct.jsp">Upload Product</a></h1>
<h1><a href="SearchProduct.jsp">Search Product</a></h1>
</div>
<div id="rightchild" class="child">
<span id="home">Home >> Search Product</span>
<div id="forms">
<form action="SearchProduct.jsp" method="get">
<label for="content">Product Name : &nbsp;  &nbsp;  &nbsp;  &nbsp;</label>
<input id="content" type="text" name="product" autofocus="autofocus"> &nbsp;  &nbsp;  &nbsp;  &nbsp;
<label>Product Type : &nbsp;  &nbsp;  &nbsp;  &nbsp;</label>
<select name="type">
<option>Select Type</option>
<%  
UpwardDAO dao1=new UpwardDAOimpl();
List<ProductDetails> list1=dao1.getAllDetails();

List<String> types=new ArrayList();
for(ProductDetails p:list1){
if(!types.contains(p.getProductype())){
	types.add( p.getProductype());

%>
<option value="<%=p.getProductype() %>" name="type"><%=p.getProductype() %></option>

<% }}
%>
</select><br>
<input type="submit" class="search" value="search"> &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;
 
</form>
<form id="export" action="createExcel" method="post"><input type="submit" class="export" value="Export to Excel"></form>
</div>
<form method="get"></form>
<table>
    <tr>
    <td>Product_ID</td>
    <td>Product_Name</td>
    <td>Product_Type</td>
    <td>Product_Price</td>
    </tr>
<%
	String name=request.getParameter("product");
    String type=request.getParameter("type");
    List<ProductDetails> filteredList=new ArrayList();
    UpwardDAO dao=new UpwardDAOimpl();
    List<ProductDetails> list=dao.getAllDetails();
    if(name==null || type==null){
    
    
   
    for(ProductDetails p:list){
    
    %>
    <tr>
    <td><%=p.getProductid() %></td>
    <td><%=p.getProductname() %></td>
    <td><%=p.getProductype() %></td>
    <td><%=p.getProductprice() %></td>
    </tr>
    <% }}else{
    	
    	for(ProductDetails p:list){
    		
    		if(p.getProductname().equalsIgnoreCase(name) || p.getProductype().equalsIgnoreCase(type)){%>
    			<tr>
    <td><%=p.getProductid() %></td>
    <td><%=p.getProductname() %></td>
    <td><%=p.getProductype() %></td>
    <td><%=p.getProductprice() %></td>
   
    </tr>
    		<%
    		 filteredList.add(p);
    		}
    	}
    	if(filteredList!=null){
    	session.setAttribute("filteredList", filteredList);
    
    	}
    		   }%>
    </table>
</div>
</div>


</body>
</html>