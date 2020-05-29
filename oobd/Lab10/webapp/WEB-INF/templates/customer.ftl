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
	Покупатель
</H2>

	<form action='${model["app_path"]}/addcustomer' method="post">
		<input type="hidden" name="id" value='${model["customer"].id!}'/>

		<div>Имя        <input type="text" name="name" value='${model["customer"].name!}'/></div>
		<div>Фамилия    <input type="text" name="surname" value='${model["customer"].surname!}'/></div>
		<div>Номер покупателя<input type="text" name="companyNumber" value='${model["customer"].companyNumber!}'/></div>

		<input type="submit" value="Сохранить"/>

	</form>

	<h3>Список заказов</h3>

	<table id="ordertable">
		<thead>
		<th>id</th><th>Название</th>
		</thead>
		<tbody>
		<#if model["orders"]??>
		<#list model["orders"] as orders>
		<tr>
			<td>
				<#if orders.id??>${orders.id}</#if>
			</td>
			<td>
                <#if orders.orderName??>${orders.orderName}</#if>
            </td>
            <td>
				<#if orders.id??><button
                        onclick="document.location = '${model['app_path']}/deleteorder?orderid=${orders.id}&customerid=${model["customer"].id!}'">X</button></#if>
            </td>
		</tr>
		</#list>
	</#if>
	</tbody>
	</table>

	<#if model["customer"].id??>
	<form action='${model["app_path"]}/addorder' method="post">
		<input type="hidden" name="id" value='${model["customer"].id!}'/>
		<input type="text" name="name" placeholder="Укажите название заказа"/>
		<input type="submit" value="Добавить заказ"/>
	</form>
	</#if>
</div>
</body>
</html>