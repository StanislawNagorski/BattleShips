# BattleShips
Javowa zabawa nad klasyczną kartkową grą w statki

Właściwości gry:
-
- umożliwia grę ze znajomym lub z przeciwnikiem komputerowym (todo)
- po zatopieniu program automatycznie zaznacza wszystkie pola w okolicy jako trafione
- domyślne ustawienia statków: 

        -  1 czteromasztowy, 2 trzymasztowe,
           3 dwumasztowe, 4 jednomasztowe (edycja todo)
        - statki muszą być w jednej linii (edycja todo)
        - statki diagonalne są zakazane.

Błąd zbyt dużej ilości statków AI:
 -
 - ~~odizolowałem listę avaliableNavPoints, na czystym randomie błąd się powtarza~~
 - ~~uprościłem pętle while~~
 - stworzyć testy jednostkowe do każdej ilości statków
 - ??? wieczór z debuggerem


To Do:
-
- ~~warunki zwycięstwa~~
- wydzielić warunki filtra edgescase to osobnej metody
- ~~stworzyć logikę gry dla dwóch graczy~~
- ~~zabezpieczyć input pprzed wprowadzeniem navigacji odwrotnie 7c i c7~~
- zaimplementować  AI
- stworzyć edycję zasad z pliku tekstowego

