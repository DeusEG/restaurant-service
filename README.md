#  Restaurant service
## Инструкция по запуску приложения
1. Скачать проект.
2. Создать новую базу данных с именем restaurantBD, данные для создания таблиц и заполнения базы находятся в папке database в файлах schema.sql и data.sql соответственно.
3. Далее необходимо добавить имя, пароль от базы данных, а также token бота в переменные окружения проекта.
Чтобы это сделать нужно перейти в Run/Edit Configurations... После этого нажать Modify options и выбрать пункт "Environment variables". В нём добавить переменные.
4. Адрес телеграм бота: https://t.me/restaurant_deus_bot.
5. Адрес для работы с сервисом http://localHost:8080.
### Переменные должны иметь имена:  
- USERNAME - для имени пользователя
- PASSWORD - для пароля
- token - для токена телеграм бота


## Роли пользователей:
##### MODER
Просматривает и удаляет пользователей
##### ADMIN
Просматривает и удаляет комментарии в своём ресторане
##### USER 
Просматривает списки ресторанов, комментарии ресторана, оставляет к ним комментарии, делает бронирования.
Может посмотреть свой профиль и изменить имя и пароль.

## Данные пользователей ресторана:
1. MODER - логин: vvv пароль: vvv 
2. ADMIN - логин: qqq пароль: aaa 
3. ADMIN - логин: qqq1 пароль: aaa 
4. USER - логин: aaa пароль: aaa 




