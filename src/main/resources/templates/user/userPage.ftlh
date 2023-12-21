<#include "../header.ftlh">
<hr>
    <h3>Пользователь ${client.login}</h3>
    <p>ID: ${client.id}</p>
    <p>Имя: ${client.name}</p>
    <p>Почта: ${client.email}</p>

<hr>
    <h3>Продукты пользователя на продажу</h3>
<#assign productsFound = false>
<#list products as product>
    <#if product.seller.id == client.id>
        <h4>Тип: ${product.productType}</h4>
        <p>Название: ${product.name}</p>
        <p>Цена: ${product.price}₽</p>
        <p>Количество: ${product.quantity} шт.</p>
        <#assign productsFound = true>
        <hr>
    </#if>
</#list>
<#if productsFound == false>
    <h4><i>Продуктов на продажу нет</i></h4>
</#if>
<#include "../footer.ftlh">