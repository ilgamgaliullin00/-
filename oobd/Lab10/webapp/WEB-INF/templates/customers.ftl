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
	Покупатели
</H2>

	<table>
		<thead>
			<th>id</th><th>Имя</th><th>Фамилия</th>
		</thead>
		<tbody>
		<#if model["customers"]??>
		<#list model["customers"] as customer>
		<tr>
			<td>
				<#if customer.id??>${customer.id}</#if>
			</td>
			<td><#if customer.name??><a href='${model["app_path"]}/addcustomer?id=${customer.id}'>${customer.name}</a></#if></td>
            <td><#if customer.surname??><a href='${model["app_path"]}/addcustomer?id=${customer.id}'>${customer.surname}</a></#if></td>
		</tr>
		</#list>
		</#if>
	</tbody>
	</table>

</div>
</body>
</html>