<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-4">Ресторан</h1>
    <hr>
    <h4 class="mt-4">Авторизация</h4> <br>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Email:</label>
            <input type="email" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if errorMessage??>
            <p style="color: red">${errorMessage}</p>
        </#if>
        <button type="submit" class="btn btn-primary">Вход</button>
    </form>
    <br>
    <a href="/registration">Зарегистрироваться</a>
</div>
</body>
</html>