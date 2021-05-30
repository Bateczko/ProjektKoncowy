# ProjektKoncowy
CL_Projekt_koncowy

## Warsztaty z automatyzacji testów ZADANIA zaliczeniowe
### Zadanie warsztatowe 1 - Selenium WebDriver + Cucumber

Utwórz użytkownika manualnie na stronie https://prod-kurs.coderslab.pl/.
Możesz wykorzystać tymczasowego maila do utworzenia użytkownika https://10minutemail.com/.

Napisz skrypt, który:

1. Będzie logować się na tego stworzonego użytkownika,
2. Wejdzie klikając w kafelek Addresses po zalogowaniu (adres, na którym powinniśmy się znaleźć to: https://prod-kurs.coderslab.pl/index.php?controller=addresses ),
3. Kliknie w + Create new address,
4. Wypełni formularz New address - dane powinny być pobierane z tabeli Examples w Gherkinie (alias, address, city, zip/postal, code, country, phone),
5. Sprawdzi czy dane w dodanym adresie są poprawne.

Dodatkowe kroki dla chętnych:

1. Usunie powyższy adres klikając w "delete",
2. Sprawdzi czy adres został usunięty.

###Zadanie warsztatowe 2 (dowolny sposób)

Napisz skrypt, który:

1.  Zaloguje się na tego samego użytkownika z zadania 1,
2. Wybierze do zakupu Hummingbird Printed Sweater (opcja dodatkowa: sprawdzi czy rabat na niego wynosi 20%),
3. Wybierze rozmiar M (opcja dodatkowa: zrób tak żeby można było sparametryzować rozmiar i wybrać S,M,L,XL),
4. Wybierze 5 sztuk według parametru podanego w teście (opcja dodatkowa: zrób tak żeby można było sparametryzować liczbę sztuk),
5. Dodaj produkt do koszyka,
6. Przejdzie do opcji - checkout,
7. Potwierdzi address (możesz go dodać wcześniej ręcznie),
8. Wybierze metodę odbioru - PrestaShop "pick up in store",
9. Wybierze opcję płatności - Pay by Check,
10. Kliknie na "order with an obligation to pay", 
11. Zrobic screenshot z potwierdzeniem zamówienia i kwotą.


Dodatkowe kroki dla chętnych:

1. Wejdź w historię zamówień i detale (najpierw kliknij w użytkownika zalogowanego, później kafelek),
2. Sprawdź czy zamówienie znajduje się na liście ze statusem "Awaiting check payment" i kwotą taką samą jak na zamówieniu dwa kroki wcześniej.
    


