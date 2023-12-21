<#include "../header.ftlh">
<hr>
    <h3>Пользователь ${client.name}</h3>
    <p>ID: ${client.id}</p>
    <#--<p>Номер телефона: ${client.phoneNumber}</p>-->
    <p>Роль: <#list user.roles as role>
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
        </#list>
    </p>
    <p>Почта: ${client.email}</p>
    <p>Дата создания: ${client.dateOfCreated.toLocalDate()} /
        ${client.dateOfCreated.getHour()}:${client.dateOfCreated.getMinute()}:${client.dateOfCreated.getSecond()}</p>

<hr>
<#include "../footer.ftlh">