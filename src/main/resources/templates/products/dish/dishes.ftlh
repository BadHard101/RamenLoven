<#include "../../header.ftlh">
<hr>
<#if user.isActive()>
    <h3>Блюда</h3>
    <#assign productsFound = false>
    <div class="row">
        <#list dishes as dish>
            <#if dish.getProductType() == "Dish" && dish.quantity gt 0>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <#if dish.hasPreview()>
                            <img src="/images/${dish.previewImageId}" class="card-img-top" alt="${dish.name}">
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${dish.name}</h5>
                            <p class="card-text">
                                Блюдо ${dish.price}₽ | Количество: ${dish.quantity}
                            </p>
                            <#if user.isAdmin()>
                                <a href="/dish/edit/${dish.id}" class="btn btn-primary btn-sm">Редактировать</a>
                                <a href="/dish/delete/${dish.id}" class="btn btn-danger btn-sm">Удалить</a>
                            </#if>
                            <a href="/product/addToCart/${dish.id}" class="btn btn-success btn-sm">Добавить в корзину</a>
                            <a href="/dish/${dish.id}" class="btn btn-outline-info btn-sm">Подробнее...</a>
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
