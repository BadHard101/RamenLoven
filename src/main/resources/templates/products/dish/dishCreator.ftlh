<#include "../../header.ftlh">
    <hr>
    <#if user.isActive()>
        <h3>Выставить блюдо на продажу</h3>
        <form action="/dish/create" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="file1" class="form-label">Фотография:</label>
                <input type="file" class="form-control" id="file1" name="file1">
            </div>
            Название блюда: <input type="text" name="name"/><br><br>
            Количество: <input type="number" name="quantity"/><br><br>
            Цена: <input type="number" step="0.01" name="price"/><br><br>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выставить на продажу" class="btn btn-primary"/>
        </form>
    </#if>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>

<#include "../../footer.ftlh">