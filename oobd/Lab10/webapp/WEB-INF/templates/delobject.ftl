<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<link rel='stylesheet' href='${model["app_path"]}/css/lab10.css'>
	<script src='${model["app_path"]}/js/lab10.js'></script>

	<title>lab12_Galiullin</title>
</head>
<body>

<div class="menu-bar">
	<ul>
		<li><a class="active" href='${model["app_path"]}'>Главная</a></li>
        <li><a href='${model["app_path"]}/customer'>Покупатели</a></li>
        <li><a href='${model["app_path"]}/addcustomer'>Новый покупатель</a></li>
        <li><a href='${model["app_path"]}/customerbyname'>Поиск покупателя</a></li>
        <li><a href='${model["app_path"]}/delobject'>Удаление объектов</a></li>

	</ul>
</div>

<div class="c-wrapper">

<H2>
	Удаление объектов
</H2>

	<h3>
		Удаление покупателя
	</h3>
	<form method="post" action='${model["app_path"]}/customer/delete'>
		ID покупателя<input name="customerid"/>
		<input type="submit" value="Удалить"/>
	</form>

	<h3>
		Удаление заказа
	</h3>
	<form method="post" action='${model["app_path"]}/customer/deleteorder'>
		ID заказа<input name="orderid"/>
		<input type="submit" value="Удалить"/>
	</form>

	<h3>
		Удаление всех заказов покупателя
	</h3>
	<form method="post" action='${model["app_path"]}/customer/deleteallorders'>
		ID покупателя<input name="customerid"/>
		<input type="submit" value="Удалить"/>
	</form>

</div>
</body>
</html>