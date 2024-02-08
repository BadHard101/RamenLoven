<!DOCTYPE html>
<html>
<head>
    <title>Доставка по Москве</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        body {
            padding: 1% 5% 2% 5%;
        }
        .button-row {
            margin-bottom: 20px; /* Spacing below the button row */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>RamenLoven 🍜🍥🥢(๑ᵔ⤙ᵔ๑)</h1>
    <hr>
    <#if user.email??>
        <h3>Пользователь: <i>${user.name}</i></h3>
        <#if user.isActive()>
            <!-- Button Row for Sell, Cart, Logout -->
            <div class="row button-row">
                <div class="col-md-4">
                    <a href="/account" class="btn btn-primary btn-block"> Аккаунт</a>
                </div>
                <#if user.isEmployee() == true>
                    <div class="col-md-4">
                        <a href="/employee/panel" class="btn btn-primary btn-block"> Панель работника</a>
                    </div>
                </#if>
                <#if user.isDeliveryman() == true>
                    <div class="col-md-4">
                        <a href="/delivery/panel" class="btn btn-primary btn-block"> Панель доставщика</a>
                    </div>
                </#if>
                <#if user.isAdmin() == true>
                    <div class="col-md-4">
                        <a href="/admin/panel" class="btn btn-primary btn-block"> Панель администратора</a>
                    </div>
                </#if>
                <div class="col-md-4">
                    <form action="/logout" method="post" class="d-inline">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Выйти" class="btn btn-danger btn-block"/>
                    </form>
                </div>
            </div>
        </#if>
    <#else>
        <a href="/login" class="btn btn-primary">Войти</a>
    </#if>
    <#if user.isActive()>
    <hr>
    <h3>Категории меню:</h3>
    <!-- Button Row for Categories -->
    <div class="row">
        <div class="col-md-4">
            <a href="/dish/selling" class="btn btn-info btn-block"><i class="fas fa-utensils"></i> Блюда</a>
        </div>
        <div class="col-md-4">
            <a href="/drink/selling" class="btn btn-success btn-block"><i class="fas fa-cocktail"></i> Напитки</a>
        </div>
        <div class="col-md-4">
            <a href="/product/shoppingCart" class="btn btn-secondary btn-block">
                <i class="fas fa-shopping-cart"></i> Корзина
            </a>
        </div>
    </div>
</#if>