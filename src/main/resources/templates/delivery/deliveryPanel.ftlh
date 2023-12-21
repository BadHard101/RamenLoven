<#include "../header.ftlh">

<style>
    .cart-button {
        display: inline-block;
        padding: 10px 15px;
        margin: 5px;
        font-size: 45px;
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s ease-in-out;
        min-width: 300px;
    }

    .cart-button.accept {
        background-color: #2196F3; /* Blue */
        color: white;
    }

    .cart-button.cancel {
        background-color: #f44336; /* Red */
        color: white;
    }

    .cart-button.cooked {
        background-color: #4CAF50; /* Green */
        color: white;
    }

    .order-actions {
         display: flex;
         flex-direction: column-reverse; /* Updated to column-reverse */
         text-align: right;
         margin-left: 20px;
     }
</style>

<hr>

<#if user.isActive()>
    <#list orders as order>
        <#if order.isDelivering() && order.getCarrierId() == user.getId() || order.isCooked()>
            <div class="order-card"
                 style="border: 1px solid #ccc; padding: 10px; margin-bottom: 20px; display: flex; justify-content: space-between; align-items: stretch;">
                <div style="flex-grow: 1;">
                    <h3>Заказ № ${order.getId()}</h3>
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
                <div class="order-actions">
                    <#if order.isCooked()>
                        <a href="/delivery/order/delivering/${order.getId()}" class="cart-button accept">Принять</a>
                    </#if>

                    <#if order.isDelivering()>
                        <a href="/delivery/order/cancel/${order.getId()}" class="cart-button cancel">Отменить</a>
                        <a href="/delivery/order/complete/${order.getId()}" class="cart-button cooked">Доставлен</a>
                    </#if>
                </div>
            </div>
        </#if>
    </#list>
    <#if orders?size == 0>
        <h4><i>Заказов пока нет...</i></h4>
    </#if>
</#if>

<#include "../footer.ftlh">
