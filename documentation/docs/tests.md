# Liste des tests unitaires

Les tests unitaires se trouve dans le dossier source, vous pouvez consulter le fichier `TestsUnitaires.java` depuis `src/test/java`. Veuillez vous assurer que vous pouvez utiliser `JUnit` sur votre IDE auquel cas référez vous à la documentation de votre IDE.

## Test sur testTemp()

</br> On cherche à vérifier si la température calculée avec la méthode *getTemp()* est bien égale à la température donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes. Pour cet exemple, on vérifie que 286.16 vaut bien bien, une fois convertie, 13.01.

## Test sur testTempDouble()

</br> On cherche à vérifier si la température calculée avec la méthode *getTempDouble()* est bien égale à la température donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes. Pour cet exemple, on vérifie que 286.14 vaut bien bien, une fois convertie, 12.99.

## Test sur testTempDouble1()

</br> Il s'agit du même test au dessus, à la place de la valeur à tester, il s'agit d'une valeur entière, on teste avec 290 et on vérifie que le résultat est bien égale à 16.85 (il se peut que la requête JSON contient des nombres entiers, la fonction a été adapté pour pouvoir lire les doubles ainsi que les integers).

## Test sur testDate()

On cherche à vérifier si l'la date calculée avec la méthode *getDate()* est bien égale à la date donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testTempSun()

</br> On cherche à vérifier si l'heure calculée avec la méthode *getTimeSun()* est bien égale à l'heure donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testCity()

</br> On cherche à vérifier si la ville trouvée avec la méthode *getCity()* est bien la même ville que celle initialisée. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testCountry()

</br> On cherche à vérifier si le pays trouvé avec la méthode *getCountry()* est bien le même pays que celui initialisé. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testApiKey()

</br> On cherche à vérifier si la ville trouvée avec la méthode *getApiKey()* est bien la même clé que celle initialisée. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testTimer()

</br> On cherche à vérifier si le booléen retourné avec la méthode *stateTimer()*correspond à celui attendu. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testStateCity()

</br> On cherche à vérifier si le booléen retourné avec la méthode *stateCity()*correspond à celui attendu. On utilise la méthode *assertTrue()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testStateRefresh()

</br> On cherche à vérifier si le booléen retourné avec la méthode *stateRefresh()*correspond à celui attendu. On utilise la méthode *assertNotNull()* permet de vérifier que les deux résultats sont bien les mêmes.