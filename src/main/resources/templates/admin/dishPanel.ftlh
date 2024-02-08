<#include "../header.ftlh">
<hr>
<h2>Панель администратора / Меню блюд</h2>


<form action="/dish/panel" method="get">
    <div class="mb-3">
        <label class="form-label">Поиск блюда по названию:</label>
        <input class="form-control" type="text" name="name" <#if name??>value="${name}"</#if>>
    </div>
    <input type="submit" value="Поиск" class="btn btn-primary">
</form>
<br>

<div class="col-md-4">
    <a href="/dish/create" class="btn btn-success btn-block">Добавить блюдо</a>
</div>
<br>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Кол-во</th>
        <th>Цена</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list dishes as dish>
        <tr>
            <td>${dish.id}</td>
            <td>${dish.name}</td>
            <td>${dish.quantity}</td>
            <td>${dish.price}₽</td>
            <td><a href="/dish/edit/${dish.id}" class="btn btn-primary">Редактирование</a></td>
            <td><a href="/dish/${dish.id}" class="btn btn-primary">Подробная информация</a></td>
        </tr>
    <#else>
        <tr>
            <td colspan="8"><h3>Блюд нет</h3></td>
        </tr>
    </#list>
</table>

<#if totalPages gt 0>
    <div class="d-flex justify-content-center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <#if currentPage gt 0>
                    <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}<#if name??>&name=${name}</#if>">Предыдущая</a></li>
                </#if>
                <#list 0..(totalPages - 1) as page>
                    <#if page == currentPage>
                        <li class="page-item active"><span class="page-link">${page + 1}</span></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="?page=${page}<#if name??>&name=${name}</#if>">${page + 1}</a></li>
                    </#if>
                </#list>
                <#if currentPage lt totalPages - 1>
                    <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}<#if name??>&name=${name}</#if>">Следующая</a></li>
                </#if>
            </ul>
        </nav>
    </div>
</#if>

<#include "../footer.ftlh">