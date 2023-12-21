<#include "../header.ftlh">

<style>
    /* Стили для кнопок */
    .cart-button {
        width: 30px; /* Задайте фиксированную ширину */
        height: 30px; /* Задайте фиксированную высоту */
        font-size: 20px; /* Задайте размер шрифта */
        text-align: center; /* Выравнивание текста по центру */
        line-height: 20px; /* Высота текстовой строки для выравнивания по центру вертикально */
        border: none; /* Уберите границу */
        background-color: #007bff; /* Цвет фона кнопки */
        color: #fff; /* Цвет текста */
        cursor: pointer; /* Изменение курсора при наведении */
        border-radius: 50%; /* Сделать кнопку круглой */
    }

    /* Стили для кнопки "Оформить заказ" */
    .checkout-button {
        width: 150px; /* Задайте фиксированную ширину */
        height: 40px; /* Задайте фиксированную высоту */
        font-size: 18px; /* Задайте размер шрифта */
        text-align: center; /* Выравнивание текста по центру */
        line-height: 40px; /* Высота текстовой строки для выравнивания по центру вертикально */
        border: none; /* Уберите границу */
        background-color: #28a745; /* Цвет фона кнопки (зеленый) */
        color: #fff; /* Цвет текста */
        cursor: pointer; /* Изменение курсора при наведении */
        margin-top: 10px; /* Отступ сверху */
        border-radius: 10px; /* Сделать кнопку круглой с более мягкими углами */
    }
</style>

<hr>
<#if user.isActive()>
    <h3>Корзина</h3>
    <#assign productsFound = false>
    <#list items as item>
        <div class="product-card" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #ccc; padding: 10px;">
            <#if item.key.images?has_content>
                <img src="/images/${item.key.previewImageId}" alt="Изображение продукта" style="width: 150px; height: auto;">
            </#if>
            <div style="flex-grow: 1; margin:10px;">
                <h4>
                    <#if item.key.productType == "Dish">
                         Блюдо
                    </#if>
                    <#if item.key.productType == "Drink">
                         Напиток
                    </#if> ${item.key.name}
                </h4> <!-- Название продукта -->
            </div>
            <div style="min-width: 200px; text-align: right;">
                <p style="font-size: 24px; font-weight: bold;">Цена: ${item.key.price}₽</p> <!-- Цена -->
                <div style="display: flex; justify-content: flex-end; align-items: center;">
                    <button class="cart-button" type="button" onclick="location.href='/product/shoppingCart/removeItem/${item.key.id}'">-</button> <!-- Кнопка уменьшения количества -->
                    <span style="min-width: 40px; text-align: center;">${item.value}</span> <!-- Количество -->
                    <button class="cart-button" type="button" onclick="location.href='/product/shoppingCart/addItem/${item.key.id}'">+</button> <!-- Кнопка увеличения количества -->
                </div>
            </div>
        </div>
        <#assign productsFound = true>
    </#list>
    <#if productsFound == false>
        <h4><i>Товаров в корзине пока нет...</i></h4>
    </#if>
    <#if cart.calculateTotal() gt 0>
        <br>
        <#--<div style="display: flex; justify-content: space-between; align-items: center;">
            <h2>Общая сумма заказа: ${cart.calculateTotal()}₽</h2>
            <button class="checkout-button" type="button" onclick="location.href='/product/shoppingCart/checkout'">Оформить заказ</button>
        </div>-->
        <form method="get" action="/product/shoppingCart/checkout">
            <!-- Добавьте поле для ввода адреса -->
            <label for="address">Введите адрес:</label>
            <input type="text" id="address" name="address" required>

            <!-- ... ваш текущий код ... -->

            <div style="display: flex; justify-content: space-between; align-items: center;">
                <h2>Общая сумма заказа: ${cart.calculateTotal()}₽</h2>
                <!-- Изменил onclick, чтобы отправлять форму с введенным адресом -->
                <button class="checkout-button" type="submit">Оформить заказ</button>
            </div>
        </form>
        <hr>
        <#if successLabel??>
            <h2 style="color: green">${successLabel}</h2>
        </#if>
        <#if errorMessage??>
            <h2 style="color: red">${errorMessage}</h2>
        </#if>
    </#if>
</#if>

<#include "../footer.ftlh">
