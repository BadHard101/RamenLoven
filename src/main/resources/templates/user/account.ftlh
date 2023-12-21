<#include "../header.ftlh">

<hr>
<div class="user-info">
    <h3>Пользователь ${user.name}</h3>
    <#--<p>Номер телефона: ${user.phoneNumber}</p>-->
    <p>Почта: ${user.email}</p>
    <p>Дата создания: ${user.dateOfCreated.toLocalDate()} /
        ${user.dateOfCreated.getHour()}:${user.dateOfCreated.getMinute()}:${user.dateOfCreated.getSecond()}</p>
</div>
<hr>

<#include "../footer.ftlh">
