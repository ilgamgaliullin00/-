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
        <li><a href='${model["app_path"]}/customer'>Заказчики</a></li>
        <li><a href='${model["app_path"]}/addcustomer'>Новый заказчик</a></li>
        <li><a href='${model["app_path"]}/customerbyname'>Поиск заказчика</a></li>
        <li><a href='${model["app_path"]}/delobject'>Удаление объектов</a></li>
    </ul>
</div>

<div class="c-wrapper">

<H2>
	Новая аптека
</H2>


	<h3>Поиск покупателя</h3>
	<form action='${model["app_path"]}/customerbyname/' method="post">
		<div>
			Имя: <input name="name" type="text"/>
		</div>

		<input type="submit" value="Искать"/>

	</form>


</div>
</body>
</html>