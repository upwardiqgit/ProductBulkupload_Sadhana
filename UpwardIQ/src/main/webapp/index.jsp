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
#content{

	width:80%;
	height:80%;	
	font-size: 3vw;
	font-weight: bold;
	text-align: center;
	letter-spacing: 1px;	
	line-height: -15px;
	position: relative;
	left: 10vw;
	top:10vw;
}
#rightchild{
	diplay:flex;
	justify-content: center;
	align-items: center;
	
}
#home{

	position: absolute;
	left:22vw;
	font-size: 2vw;
	
}
</style>

<html>
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
	<span id="home">Home</span>
	<p id="content">Welcome to UpwardIQ Bulk Upload Product Data Application</p>
</div>
</div>
</body>
</html>
