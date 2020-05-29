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

    <form action='${model["app_path"]}/customerbyname' method="post">
        <input type="text" name="name" placeholder="Укажите имя покупателя"/>
        <input type="submit" value="Найти покупателя"/>
    </form>

    <table>
        <thead>
        <th>
            id
        </th>
        <th>
            Имя
        </th>
        <th>
            Фамилия
        </th>
        <th>
            Номер покупателя
        </th>
        <tbody>
        <#if model["customer"]??>
            <tr>
                <td>
                    <#if model["customer"].id??>${model["customer"].id}</#if>
                </td>
                <td>
                    <#if model["customer"].name??>${model["customer"].name}</#if>
                </td>
                <td>
                    <#if model["customer"].surname??>${model["customer"].surname}</#if>
                </td>
                <td>
                    <#if model["customer"].companyNumber??>${model["customer"].companyNumber}</#if>
                </td>
            </tr>
        </#if>
        </tbody>
        </thead>
    </table>

</div>
</body>
</html>