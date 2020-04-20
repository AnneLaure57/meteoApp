# Liste des tests unitaires

Les tests unitaires se trouve dans le dossier source, vous pouvez consulter le fichier `TestsUnitaires.java` depuis `src/test/java`. Veuillez vous assurer que vous pouvez utiliser `JUnit` sur votre IDE auquel cas référez vous à la documentation de votre IDE.

## Test sur testTemp()

</br> On cherche à vérifier si la température calculée avec la méthode *getTemp()* est bien égale à la température donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testTempDouble()

</br> On cherche à vérifier si la température calculée avec la méthode *getTempDouble()* est bien égale à la température donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testDate()

</br> On cherche à vérifier si la date calculée avec la méthode *addDays()* est bien égale au résultat trouvé sans la méthode. On utilise la méthode *assertFalse()* permet de vérifier que les deux résultats ne sont pas les mêmes, car le résultat attendu correspond à une date statique et l'autre donne la date du jour actuelle, donc si on ajoute 0 jours, alors les deux dates doivent être différentes.

## Test sur testTempSun()

</br> On cherche à vérifier si l'heure calculée avec la méthode *getTimeSun()* est bien égale à l'heure donnée sans la méthode. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testCity()

</br> On cherche à vérifier si la ville trouvée avec la méthode *getCity()* est bien la même ville que celle initialisée. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testCountry()

</br> On cherche à vérifier si le pays trouvé avec la méthode *getCountry()* est bien le même pays que celui initialisé. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.

## Test sur testApiKey()

</br> On cherche à vérifier si la ville trouvée avec la méthode *getApiKey()* est bien la même clé que celle initialisée. On utilise la méthode *assertEquals()* permet de vérifier que les deux résultats sont bien les mêmes.