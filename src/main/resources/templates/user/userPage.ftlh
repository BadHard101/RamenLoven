<#include "../header.ftlh">
<hr>
    <h3>Пользователь ${client.name}</h3>
    <p>ID: ${client.id}</p>
    <#--<p>Номер телефона: ${client.phoneNumber}</p>-->
    <p>Роль: <#list client.roles as role>
        <#if role=="ROLE_ADMIN">
            Администратор
        <#elseif role=="ROLE_EMPLOYEE">
            Работник
        <#elseif role=="ROLE_USER">
            Пользователь
        <#elseif role=="ROLE_DELIVERYMAN">
            Доставщик
        <#else>
            ${role}<br>
        </#if>
        </#list>
    </p>
    <p>Почта: ${client.email}</p>
    <p>Дата создания: ${client.dateOfCreated.toLocalDate()} /
        ${client.dateOfCreated.getHour()}:${client.dateOfCreated.getMinute()}:${client.dateOfCreated.getSecond()}</p>
<hr>
<#list orders as order>
    <div class="order-card"
         style="font-size: 20px; border: 1px solid #ccc; padding: 10px; margin-bottom: 20px; display: flex; justify-content: space-between; align-items: stretch;">
        <div style="flex-grow: 1;">
            <h3>Заказ № ${order.getId()}</h3>
            Дата создания: ${order.creationDate.toLocalDate()} /
            ${order.creationDate.getHour()}:${order.creationDate.getMinute()}:${order.creationDate.getSecond()}
            <br>
            Адрес: ${order.address}
            <br>
            Статус: <#list order.status as status>
                <#if status=="CREATED">
                    Создан
                <#elseif status=="ACCEPTED">
                    Принят
                <#elseif status=="COOKED">
                    Приготовлен
                <#elseif status=="DELIVERING">
                    Доставляется
                <#elseif status=="COMPLETED">
                    Завершен
                <#elseif status=="CANCELED">
                    Отменен
                <#else>
                    ${status}<br>
                </#if>
            </#list>
            <br>
            Имя заказчика: ${order.client.name}
            <br>
            Телефон: ${order.client.phoneNumber}
            <br>
            Почта: ${order.client.email}
            <br>
            <br>
            <h3>Список заказа:</h3>
            <#list order.getItems() as product, quantity>
                <div class="product-card"
                     style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #ccc; padding: 10px;">
                    <#if product.images?has_content>
                        <img src="/images/${product.previewImageId}" alt="Изображение продукта"
                             style="width: 150px; height: auto;">
                    </#if>
                    <div style="flex-grow: 1; display: flex; justify-content: space-between; align-items: center;">
                        <div style="display: flex; flex-direction: column; align-items: flex-start; margin: 20px;">
                            <h3>
                                <#if product.productType == "Dish">
                                    Блюдо
                                </#if>
                                <#if product.productType == "Drink">
                                    Напиток
                                </#if>
                                ${product.name}
                            </h3>
                            <h4>Количество: ${quantity}</h4>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#list>
<#include "../footer.ftlh">