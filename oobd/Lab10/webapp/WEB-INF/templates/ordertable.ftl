<thead>
<th>id</th><th>Номер</th>
</thead>
<tbody>
<#if model["orders"]??>
<#list model["orders"] as order>
<tr>
    <td>
        <#if order.id??>${order.id}</#if>
    </td>
    <td><#if order.orderName??>${order.orderName}</#if></td>
    <td>
        <#if order.id??><button onclick="delete_order(${order.id})">X</button></#if>
    </td>
</tr>
</#list>
</#if>
</tbody>
