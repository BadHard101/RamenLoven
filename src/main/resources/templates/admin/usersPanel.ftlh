<#include "../header.ftlh">

<hr>
<h2>Панель администратора / Пользователи</h2>
<form action="/admin/users" method="get">
    <div class="mb-3">
        <label class="form-label">Поиск пользователя по почте:</label>
        <input class="form-control" type="text" name="email" <#if email??>value="${email}"</#if>>
    </div>
    <input type="submit" value="Поиск" class="btn btn-primary">
</form>
<br>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td><#if user.active>Да<#else>Нет</#if></td>
            <td><#list user.roles as role>
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
                </#list></td>
            <td>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан" class="btn btn-danger">
                </form>
            </td>
            <td><a href="/admin/user/edit/${user.id}" class="btn btn-primary">Редактирование</a></td>
            <td><a href="/admin/user/${user.id}" class="btn btn-primary">Подробная информация</a></td>
        </tr>
    <#else>
        <tr>
            <td colspan="8"><h3>Пользователей нет</h3></td>
        </tr>
    </#list>
</table>

<#if totalPages gt 0>
    <div class="d-flex justify-content-center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <#if currentPage gt 0>
                    <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}<#if email??>&email=${email}</#if>">Предыдущая</a></li>
                </#if>
                <#list 0..(totalPages - 1) as page>
                    <#if page == currentPage>
                        <li class="page-item active"><span class="page-link">${page + 1}</span></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="?page=${page}<#if email??>&email=${email}</#if>">${page + 1}</a></li>
                    </#if>
                </#list>
                <#if currentPage lt totalPages - 1>
                    <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}<#if email??>&email=${email}</#if>">Следующая</a></li>
                </#if>
            </ul>
        </nav>
    </div>
</#if>

<#include "../footer.ftlh">