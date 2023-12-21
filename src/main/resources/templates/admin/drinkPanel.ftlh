<#include "../header.ftlh">
<hr>
<h2>Панель администратора / Меню напитков</h2>


<form action="/drink/" method="get">
    <div class="mb-3">
        <label class="form-label">Поиск напитка по названию:</label>
        <input class="form-control" type="text" name="name">
    </div>
    <input type="submit" value="Поиск" class="btn btn-primary">
</form>
<br>

<div class="col-md-4">
    <a href="/drink/create" class="btn btn-success btn-block">Добавить напиток</a>
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
    <#list drinks as drink>
        <tr>
            <td>${drink.id}</td>
            <td>${drink.name}</td>
            <td>${drink.quantity}</td>
            <td>${drink.price}</td>
            <td><a href="/drink/edit/${drink.id}" class="btn btn-primary">Редактирование</a></td>
            <td><a href="/drink/${drink.id}" class="btn btn-primary">Подробная информация</a></td>
        </tr>
    <#else>
        <tr>
            <td colspan="8"><h3>Напитков нет</h3></td>
        </tr>
    </#list>
</table>

<#include "../footer.ftlh">