## Ресторанный проект 🌸 "RamenLoven" 🍜🍥🥢(๑ᵔ⤙ᵔ๑)

![LoveRamen](https://github.com/BadHard101/RamenLoven/assets/91153396/331f8d2c-ae0f-4079-b57f-7516fb0e6ba0)

Добро пожаловать в репозиторий проекта "RamenLoven"! Этот проект создан для управления рестораном "RamenLoven" с использованием Spring Framework и Java. Здесь вы найдете подробное описание функционала, технологий и возможностей приложения.

### О проекте

"RamenLoven" - это веб-приложение для управления рестораном, предоставляющее различные возможности для администрирования рестораном, управления персоналом и заказами, а также обеспечивающее удобный интерфейс для клиентов для просмотра меню и размещения заказов.

![MainPage](https://github.com/BadHard101/RamenLoven/assets/91153396/c555289b-b511-4ae6-be27-0619760d1d99)

## Функционал

1. **CRUD-операции:**
   - Разработан и интегрован функционал для управления данными приложения, такими как меню ресторана, сотрудники, пользователи, заказы и другие.

2. **Аутентификация и авторизация пользователей:**
   - Реализована система регистрации и входа в систему с обязательной валидацией данных и конфигурацией Spring Security.

3. **Реляционная база данных:**
   - Организовано автоматическое создание нормализованной БД (есть подключение к СУБД MySQL).

4. **Разделение ролей и безопасность приложния:**
   - Внедрено детализированное разделение ролей на администраторов, поваров, доставщиков и пользователей и другие меры безопасности с использованием Spring Security.

5. **Корзина и валидация заказов:**
    - Разработана корзины пользователей с валидацией наличия товаров и данных оформления заказа.

6. **Автоматизация статусов заказов:**
    - Разработана система автоматического изменения статусов заказов в реальном времени. Статус заказа автоматические изменяется в процессе работы поваров и доставщиков над заказом.

7. **История и отслеживание заказов:**
    - Организован механизм отслеживания и хранения истории заказов пользователей.

8. **Расширенный поиск данных:**
    - Внедрены меры для возможности расширенного поиска и фильтрации данных с использованием JPA.

9. **Панели управления:**
    - Созданы панели управления для работников и разработаны соответствующих энд-поинтов.

10. **Шаблонизация данных:**
    - Для динамической генерации HTML-страниц был использован шаблонизатор Freemarker.

11. **Пагинация и визуализация данных:**
    - Реализована пагинация при просмотре данных при помощи Spring Web.

12. **MVC-паттерн:**
    - Архитектура приложения организована в соответствии с паттерном проектирования Model-View-Controller (MVC).

## Скриншоты

### Пользователь

   - Корзина пользователя

![Cart](https://github.com/BadHard101/RamenLoven/assets/91153396/89b5ce61-edc2-425d-8fe1-c22019d36247)

   - Просмотр меню

![Menu](https://github.com/BadHard101/RamenLoven/assets/91153396/2e4ec361-d947-4f3a-b493-e7f10ed033a4)

   - Просмотр профиля и истории заказов

![Account](https://github.com/BadHard101/RamenLoven/assets/91153396/388fed30-b2c7-45c2-8905-97c9ae115f9f)

### Повар

   - Функционал принятия, готовности заказа

![Cook](https://github.com/BadHard101/RamenLoven/assets/91153396/0b00e52b-27ff-4d67-933a-ab604af0536d)

### Доставщик

   - Функционал принятия, подтверждение доставки заказа

![Delivery](https://github.com/BadHard101/RamenLoven/assets/91153396/d549dbd3-30ee-465a-aa05-56677e2787c7)

### Администратор

   - Возможные страницы панели управления

![AdminPanel](https://github.com/BadHard101/RamenLoven/assets/91153396/f473120e-bd5e-4efd-8dec-31d85ba51672)

   - Пример управления меню блюд (расширенный поиск, пагинация)

![DishPanel](https://github.com/BadHard101/RamenLoven/assets/91153396/10b86597-7865-4dbc-86aa-1fa593bce86a)

   - Создание/Изменение блюда/напитка

![EditDish](https://github.com/BadHard101/RamenLoven/assets/91153396/4fb33b55-a65f-4577-95dc-aeed7e50cb17)

   - Изменение роли пользователя

![RoleChange](https://github.com/BadHard101/RamenLoven/assets/91153396/67e5a55b-b9c0-48d4-83ca-fbe695ebd788)

   - Просмотр подробной информации по заказу

![OrderPage](https://github.com/BadHard101/RamenLoven/assets/91153396/2b0b529d-41f8-4eca-b7fe-8fc2741e7c12)

   - Изменение статуса заказа (при необходимости)

![StatusChange](https://github.com/BadHard101/RamenLoven/assets/91153396/6b29ba38-21ac-4a30-8197-8e52eb70df58)

### Система приложения

   - Невозможно добавить в заказ большее кол-во продукта, чем есть в БД

![CartLimit](https://github.com/BadHard101/RamenLoven/assets/91153396/395bb380-2f0d-46ad-b6bb-508c72100065)

   - Защита оформления невалидного заказа

![CartSecure](https://github.com/BadHard101/RamenLoven/assets/91153396/ff8bf103-fd68-47d4-ade7-dcfa04665a3a)

   - Автоматическая смена статуса заказов в процессе работы сотрудников с заказом

![AutomaticStatusChange](https://github.com/BadHard101/RamenLoven/assets/91153396/bbe2a66d-67e9-44e7-9bb7-620c5be14ba9)

   - И другие...

## Установка

Чтобы установить и запустить проект локально, выполните следующие шаги:

1. Клонируйте репозиторий на локальную машину.
2. Убедитесь, что у вас установлены Java и MySQL.
3. Импортируйте проект в вашу среду разработки.
4. Настройте конфигурацию базы данных в файле `application.properties`.
5. Запустите проект.
6. Перейдите по адресу: http://localhost:8080/

## Контакты

Если у вас есть вопросы или предложения, не стесняйтесь связаться по электронной почте: [BadHard101@yandex.ru](mailto:BadHard101@yandex.ru).

Благодарим вас за использование нашего приложения! 🍜🎉
