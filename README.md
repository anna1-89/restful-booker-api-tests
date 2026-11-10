## ДЕМО-Проект по автоматизации тестирования API
### Сайт <a target="_blank" href="https://restful-booker.herokuapp.com/">restful-booker</a>

<p align="center">
<img title="restful-booker" src="images/screens/cover.png">
</p>


## :rocket: Содержание:

- Технологии и инструменты
- Список методов, на которые были реализованы тесты
- Запуск тестов (сборка в Jenkins)
- Allure-отчет
- Интеграция с Allure TestOps


## :rocket: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure2.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://qameta.io/"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>


## :rocket:  Список методов

- [x] CreateToken - авторизация (получение токена)
- [x] CreateBooking - создание нового бронирования
- [x] GetBooking - получение информации по бронированию
- [x] UpdateBooking - обновление информации по бронированию: полное обновление и частичное обновление
- [x] DeleteBooking - удаление информации по бронированию

---

###  Локальный запуск :

Пример командной строки:
```bash
gradle clean test
--continue
-DadminUsername=username пользователя
-DadminPassword=пароль пользователя  
```

###  Удаленный запуск :

```
clean test
--continue
-DadminUsername=username пользователя
-DadminPassword=пароль пользователя 
```

---

###  :rocket:  JENKINS:
Открыть <a target="_blank" href="https://jenkins.qa.guru/job/42_annap_restful_booker_api_tests/">проект в Jenkins</a> и нажать **Собрать сейчас**

![This is an image](images/screens/jenkins.png)

---

###  Отчет в Allure report

Allure Report — это популярный инструмент с открытым исходным кодом от компании Qameta Software для визуализации результатов автоматизированного тестирования. Он преобразует «сухие» технические логи из автотестов в наглядные и интерактивные HTML-отчёты.

### **Что показывает Allure Report::**

- *Статусы и дерево тестов: сколько тестов прошло успешно (passed), сколько упало (failed) или сломалось (broken), сгруппированные по пакетам, фичам или уровням*
- *Детали шагов: пошаговый сценарий выполнения каждого теста*
- *Вложения (Attachments): скриншоты ошибок, сетевые логи, видеозаписи или JSON-ответы с сервера*
- *История запусков (Trends): графики изменения стабильности тестов от сборки к сборке (помогают выявлять плавающие или нестабильные тесты — flaky tests)*
- *Окружение (Environment): информация о версии ОС, браузере, стенде и параметрах запуска*

### *Главная страница*

![This is an image](images/screens/allureReport.png)

### *Тест-кейсы*

![This is an image](images/screens/allureTests.png)

![This is an image](images/screens/allureTestsBehavior.png)

### *Графики*

![This is an image](images/screens/allureDiagramm.png)

---

## :rocket: Интеграция с Allure TestOps

Открыть <a target="_blank" href="https://allure.qa.guru/launch/57057/">проект в Allure TestOps</a>

TestOps Тестирование + Операции) — это методология и практика управления, автоматизации и масштабирования процессов тестирования в разработке ПО по аналогии с тем, как DevOps объединяет разработку и эксплуатацию.


### **Главные задачи и принципы TestOps:**

- *Интеграция в CI/CD: Встраивание тестов (ручных и автоматизированных) прямо в конвейеры сборки и доставки ПО*
- *Единое пространство: Объединение тест-кейсов, результатов прогонов, логов, дефектов и требований в одной системе (например, с помощью платформ вроде ТестОпс или Allure TestOps)*
- *Работа с автотестами: Масштабирование запуска тестов, управление тестовым окружением и сбор отчетов в реальном времени*
- *Аналитика и метрики: Поиск и устранение «нестабильных» (flaky) тестов, оценка покрытия и прозрачность релизов для бизнеса и разработчиков*
- *Сотрудничество: Тесная связь между QA-инженерами, разработчиками, DevOps-специалистами и менеджерами*

### *Результаты тестов*

![This is an image](images/screens/testopsTestCases.png)

### *Обзор*

![This is an image](images/screens/testopsTestBoards.png)
