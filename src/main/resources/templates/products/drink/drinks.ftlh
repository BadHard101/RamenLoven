<#include "../../header.ftlh">
<hr>
<#if user.isActive()>
    <h3>Напитки</h3>
    <#assign productsFound = false>
    <div class="row">
        <#list drinks as drink>
            <#if drink.getProductType() == "Drink" && drink.quantity gt 0>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <#if drink.hasPreview()>
                            <img src="/images/${drink.previewImageId}" class="card-img-top" alt="${drink.name}">
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${drink.name}</h5>
                            <p class="card-text">
                                Напиток ${drink.price}₽ | Количество: ${drink.quantity}
                            </p>
                            <#if user.isAdmin()>
                                <a href="/drink/edit/${drink.id}" class="btn btn-primary btn-sm">Редактировать</a>
                                <a href="/drink/delete/${drink.id}" class="btn btn-danger btn-sm">Удалить</a>
                            </#if>
                            <a href="/product/addToCart/${drink.id}" class="btn btn-success btn-sm">Добавить в корзину</a>
                            <a href="/drink/${drink.id}" class="btn btn-outline-info btn-sm">Подробнее...</a>
                        </div>
                    </div>
                </div>
                <#assign productsFound = true>
            </#if>
        </#list>
    </div>
    <#if productsFound == false>
        <h4><i>Блюд на продажу нет</i></h4>
    </#if>
    <hr>
</#if>

<#include "../../footer.ftlh">
