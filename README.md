# Farkas-Rich-rd-Martin-Vizsgaremek

Automatizált tesztek futtatásához szükséges, hogy a gépen telepítve legyen:
    - Google Chrome
    - Maven

Futtatás lépései:

    1. Nyissunk egy parancssort.
    2. Navigáljunk a projekt mappába (Farkas-Rich-rd-Martin-Vizsgaremek)
    3. Tesztek futtatása: 'mvn clean test' parancs.
    4. Jelentés készítése: 'mvn allure:serve' parancs.
    5. Jelentés készítése fájlba: 'mvn allure:report' parancs.
    (target/site/allure-maven/index.html)