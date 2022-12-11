# Дипломный проект профессии «Тестировщик»
Данный проект представляет собой тестирование веб-сервиса 
покупки тура "Путешествие дня - Марракeш" (предоставленного 
в виде приложения). Приложение предлагает купить тур по 
определённой цене с помощью двух способов: 

* обычная оплата по дебетовой карте;
* выдача кредита по данным банковской карты.

Приложение в собственной СУБД сохраняет информацию о том, каким 
способом был совершён платёж и успешно ли он был совершён
(при этом данные карт сохранять не допускается).

## Начало работы

Для запуска тестов на вашем ПК должно быть установлено следующее ПО:

* IntelliJ IDEA
* Git
* Docker Desktop
* Google Chrome (или другой браузер)


## Запуск приложения

1. Запустить IntelliJ IDEA и склонировать репозитории QA-diplom-project

```git clone https://github.com/2078Stasyan/QA-diplom-project```

2. Запустить docker container (для запуска контейнеров с MySql, PostgreSQL и Node.js использовать 
команду docker-compose up -d --build, чтобы образ не пересобирался каждый раз необходимо убрать флаг -d --build)

```docker-compose up```

3. Дождаться запуска контейнеров
4. В терминале IntelliJ IDEA запустить SUT:

* *с использованием БД MySQL командой:*

```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```

* *с использованием БД PostgreSQL командой:* 

```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar```

## Запуск автотестов

В терминале IntelliJ IDEA запустить автотесты командой:

* *для конфигурации БД MySql:*

```./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"```

* *для конфигурации БД PostgreSQL:*

```./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"```

## Запуск отчетов

В терминале IntelliJ IDEA запустить отчеты командой:

```./gradlew allureReport```

## Остановка работы приложения

1. В терминале IntelliJ IDEA остановить SUT:

```CTRL+C```

2. Остановка работы контейнеров:

```docker-compose down ```

3. Закрыть IntelliJ IDEA
