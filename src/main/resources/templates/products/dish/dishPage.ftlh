<#include "../../header.ftlh">
<hr>
<div class="row">
    <div class="col-md-6">
        <#if dish.hasPreview()>
            <img src="/images/${dish.previewImageId}" class="img-fluid rounded" alt="${dish.name}">
        </#if>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">Блюдо: ${dish.name}</h3>
                </p>
                <p class="card-text">Осталось: ${dish.quantity} шт.</p>
                <p class="card-text">Цена: ${dish.price}₽</p>
                <#if user.isAdmin()>
                    <a href="/dish/edit/${dish.id}" class="btn btn-primary btn-sm">Редактировать</a>
                    <a href="/dish/delete/${dish.id}" class="btn btn-danger btn-sm">Удалить</a>
                </#if>
                <a href="/product/addToCart/${dish.id}" class="btn btn-success btn-sm">Добавить в корзину</a>
            </div>
        </div>
    </div>
</div>
<#include "../../footer.ftlh">
