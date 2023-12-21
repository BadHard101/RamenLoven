<#include "../../header.ftlh">
<hr>
<div class="row">
    <div class="col-md-6">
        <#if drink.hasPreview()>
            <img src="/images/${drink.previewImageId}" class="img-fluid rounded" alt="${drink.name}">
        </#if>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Напиток: ${drink.name}</h3>
                </p>
                <p class="card-text">Осталось: ${drink.quantity} шт.</p>
                <p class="card-text">Цена: ${drink.price}₽</p>
                <#if user.isAdmin()>
                    <a href="/drink/edit/${drink.id}" class="btn btn-primary btn-sm">Редактировать</a>
                    <a href="/drink/delete/${drink.id}" class="btn btn-danger btn-sm">Удалить</a>
                </#if>
                <a href="/product/addToCart/${drink.id}" class="btn btn-success btn-sm">Добавить в корзину</a>
            </div>
        </div>
    </div>
</div>
<#include "../../footer.ftlh">
