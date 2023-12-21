<#include "../header.ftlh">
<hr>
<h3>Редактирование пользователя ${user.name}</h3>
<form action="/admin/user/edit" method="post">
    <div class="mb-3">
        <label class="form-label">Выберите роль:</label><br>
        <#list roles as role>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="userRole" value="${role}" id="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="form-check-label" for="${role}">
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
                </label>
            </div>
        </#list>
    </div>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Сохранить</button>
</form>

<#include "../footer.ftlh">