<#include "../../header.ftlh">

<#if user.isActive()>
    <hr>
    <h3>Выставить на продажу:</h3>
    <!-- Button Row for Categories -->
    <div class="row">
        <div class="col-md-4">
            <a href="/drink/create" class="btn btn-success btn-block"><i class="fas fa-mobile-alt"></i> Телефоны</a>
        </div>
        <div class="col-md-4">
            <a href="/dish/create" class="btn btn-info btn-block"><i class="fas fa-dish"></i> Книги</a>
        </div>
        <div class="col-md-4">
            <a href="/washingMachine/create" class="btn btn-warning btn-block"><i class="fas fa-tshirt"></i> Стиральные машины</a>
        </div>
    </div>
</#if>

<#include "../../footer.ftlh">
