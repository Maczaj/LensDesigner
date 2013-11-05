LensDesigner
============
### Temat: ZS.AE3. Interaktywny projektant soczewek.

Soczewka będzie opisana za pomocą 2n liczb określających odległość odpowiednich punktów od osi soczewki. Liczbę punktów oraz współczynnik załamania światła soczewki określi użytkownik przez rozpoczęciem symulacji. W ramach algorytmu ewolucyjnego zmianie podlegać będą odległości punktów od osi soczewki, przy czym wartość początkowa będzie jednakowa dla wszystkich punktów. Jednym z parametrów wejściowych będzie również dokładność skupiania promieni świetlnych. Kolejnym argumentem będzie liczba równoległych, jednakowo oddalonych od siebie promieni świetlnych kierowanych na soczewkę.

Po każdej iteracji algorytmu ewolucyjnego wykonywana będzie symulacja przebiegu wiązek świetlnych w czasie rzeczywistym, tj. oglądający symulację będzie mógł na bieżąco obserwować przebieg wiązek świetlnych najlepszej soczewki w aktualnej generacji. Ocena „jak dobrze” skupiają się promienie przy danej konfiguracji soczewki przeprowadzana będzie za pomocą sumy kwadratów odległości punktów przecięcia dowolnych dwóch promieni od punktu wybranego przez użytkownika. W przypadku gdy wynik spełni warunek zdefiniowany przez użytkownika w parametrze dokładność, symulacja zostanie przerwana. Do określenia przebiegu promienia zostanie wykorzystany następujący wzór:

    sin(alpha)/sin(beta) = n2/n1

alfa, beta,n1, n2 – parametry odpowiadające tym pokazanym na rysunku. Za jedną ze stałych n1 i n2 w zależności od tego czy to pierwsze bądź drugie załamanie, będziemy przyjmować za 1, gdyż zakładamy umieszczenie soczewki w powietrzu, a drugie za podany przez użytkownika współczynnik załamania światła soczewki.
