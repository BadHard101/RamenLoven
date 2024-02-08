<#include "../header.ftlh">
<hr>
<h3>Редактирование статуса заказа ${order.id}</h3>
<form action="/admin/order/edit/${order.id}" method="post">
    <div class="mb-3">
        <label class="form-label">Выберите статус:</label><br>
        <#list status as st>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="orderStatus" value="${st}" id="${st}" ${order.status?seq_contains(st)?string("checked", "")}>
                <label class="form-check-label" for="${st}">
                    <#if st=="CREATED">
                        Создан
                    <#elseif st=="ACCEPTED">
                        Принят
                    <#elseif st=="COOKED">
                        Приготовлен
                    <#elseif st=="DELIVERING">
                        Доставляется
                    <#elseif st=="COMPLETED">
                        Выполнен
                    <#elseif st=="CANCELED">
                        Отменен
                    <#else>
                        ${st}<br>
                    </#if>
                </label>
            </div>
        </#list>
    </div>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Сохранить</button>
</form>

<#include "../footer.ftlh">