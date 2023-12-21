<#include "../header.ftlh">

<hr>
<h2>Панель администратора / Заказы</h2>
<form action="/admin/orders" method="get">
    <div class="mb-3">
        <label class="form-label">Поиск заказа по адресу:</label>
        <input class="form-control" type="text" name="address" <#if address??>value="${address}"</#if>>
    </div>
    <input type="submit" value="Поиск" class="btn btn-primary">
</form>
<br>
<table class="table">
    <tr>
        <th>ID</th>
        <#--<th>Дата</th>-->
        <th>Адрес</th>
        <th>Сумма</th>
        <th>Пользователь</th>
        <th>Статус</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list orders as order>
        <tr>
            <td>${order.getId()}</td>
            <#--<td>${order.creationDate.toLocalDate()} /
                ${order.creationDate.getHour()}:
                ${order.creationDate.getMinute()}:
                ${order.creationDate.getSecond()}</td>-->
            <td>${order.address}</td>
            <td>${order.calculateTotal()} ₽</td>
            <td>${order.getClient().name}</td>
            <td><#list order.status as status>
                    <#if status=="CREATED">
                        Создан
                    <#elseif status=="ACCEPTED">
                        Принят
                    <#elseif status=="COOKED">
                        Приготовлен
                    <#elseif status=="DELIVERING">
                        Доставляется
                    <#elseif status=="COMPLETED">
                        Выполнен
                    <#elseif status=="CANCELED">
                        Отменен
                    <#else>
                        ${status}<br>
                    </#if>
                </#list></td>
            <td><a href="/admin/order/edit/${order.id}" class="btn btn-primary">Редактирование</a></td>
            <td><a href="/admin/order/${order.id}" class="btn btn-primary">Подробная информация</a></td>
        </tr>
    <#else>
        <tr>
            <td colspan="8"><h3>Заказов нет</h3></td>
        </tr>
    </#list>
</table>

<#if totalPages gt 0>
    <div class="d-flex justify-content-center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <#if currentPage gt 0>
                    <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}<#if address??>&address=${address}</#if>">Предыдущая</a></li>
                </#if>
                <#list 0..(totalPages - 1) as page>
                    <#if page == currentPage>
                        <li class="page-item active"><span class="page-link">${page + 1}</span></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="?page=${page}<#if address??>&address=${address}</#if>">${page + 1}</a></li>
                    </#if>
                </#list>
                <#if currentPage lt totalPages - 1>
                    <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}<#if address??>&address=${address}</#if>">Следующая</a></li>
                </#if>
            </ul>
        </nav>
    </div>
</#if>

<#include "../footer.ftlh">