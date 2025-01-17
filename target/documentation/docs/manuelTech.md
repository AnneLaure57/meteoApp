#La documentation technique:

## Les outils :

</br> Pour ce projet j'ai travaillé avec les outils suivants :

* Scene Builder :outil permettant de réaliser des interfaces JavaFX (version 8).
* Eclipse : IDE pour la programmation Java (jdk 1.8_0241).
* Maven & les plugins nécessaires pour générer les fichiers archives et le fichier binaire (inclus avec cette documentation).
* ObjeAid UML Diagramm : est un outil permettant de créer des diagrammes à partir d'Eclipse.

## Le diagramme de classe :

</br>![Image not found](images/diagrammeClasse.png "applciation")

* Au vue de la qualité de la photo, le diagramme est fournie avec les fichiers sources sous le nom de `diagramm_class.ucls`.

### Explications

Dans le diagramme, il y a les relations suivantes :

* Weather : association avec la classe MeteoClent et la classe Result.
* List : associations avec les classe Wind, Weather, Rain, Clouds, Sys et Main.
* Result : associations avec les classes Weather, Wind, Clouds, Coord, Sys et Main.
* Example : associations avec les classes Weather, List, City et Main.
* City : association avec la classe Coord.

## Design Patterns 

Afin de tester de nouvelles choses, j'ai utilisé le design pattern MVC (Modèle - Vue - Controller).

## Les classes :

En plus de cette documentation, les classes ont été commenté avec le plugin permettant la javadoc.

### App.java 

Cette classe est la classe principale permettant de lancer l'interface (Baignoire.fxml). Il y a deux méthodes :

* start() : permettant de charger le fichier *.fxml*, de modifier le titre de la fenêtre (setTitle()), de spécifier le scène qui va être utilisé (setScene()), d'afficher la scène (commande show()). La taille de la fenêtre n'est pas modifiable (avec setResizable()), ceci évitant de modifier la taille de la fenêtre et l'affichage a été réalisé de la manière la plus simple possible afin de pouvoir l'afficher sur tous les types d'écran (15 pouces, 17 pouces, 19 pouces et inférieur).

* main() : qui permet de charger la fenêtre avec la commande *launch(args)* .

### Model - MeteoClient.java

La classe **MeteoClient** contient 6 paramètres :
	* WEBSERVICE (String),
	* lang (String),
	* apiKey (String),
	* city (String),
	* country (String).

Il y a plusieurs constructeurs ainsi que les getters et setters.

* buildRequest : permet de construire une requête pour le jour actuel.

* getJsonWeatherByCityName : permet de se connecter à l'api pour obtenir le résultat de la requête pour un jour. cette requête va nous permettre de générer les classes pour accéder aux contenus de la requête.

* getWeatherByCityName : permet construire la requête et d'accéder aux informations grâce à la classe `Result`.

* buildRequestFor5 : permet de construire une requête pour les prochains jours.

* getWeatherByCityNameFor5 : permet construire la requête et d'accéder aux informations grâce à la classe `Example`.

* getJsonWeatherByCityNameFor5 : permet de se connecter à l'api pour obtenir le résultat de la requête pour les prochins jours. cette requête va nous permettre de générer les classes pour accéder aux contenus de la requête.


### Controller - Controller.java

* initialize() :  est la fonction qui s'exécute lorsque la fenètre FXML se créée. Lors de l'initialisation, on affiche le contenu des labels et des Images en modifiant le contenu avec les méthodes *setText()* et *setImage()*.

* diplay (ActionEvent e) : pour chaque MenuItem du menu "Affichage", on cherche à savoir si le menuItem est sélectionné (utilisation de isSelected()) alors si le label est visible (utilisation de isVisible()) alors on le cache ainsi que son image avec la méthode *setVisible()*. Pour déclencher la méthode, on utilise la méthode *setOnAction()*.

* setCityResearch (ActionEvent e) : permet de d'afficher les prévisions pour une ville contenu dans le menu "sélectionner". On récupère le label du menuItem et on lance la méthode *checkCityName()*.

* addCityList (ActionEvent e) : si le TextField n'est pas vide, alors on ajoute via le menuItem "ajouter" la ville saisit dans le TextField au menu "sélectionner". Pour récupérer le contenu du textField, on utilise la méthode "getText()" puis pour l'ajouter on l'ajoute au menu concerné comme ceci :

** select.getItems().add(newMenu); **

On ajoute également ce menuItem au menu "supprimer", pour pouvoir le retirer de la liste personnalisée. Au moment où la ville est ajoutée, on lui donne la possibilité de faire une recherche de prévisions pour la ville en question avec les méthodes *setOnAction()* et *setCityResearch()*.

* addIntoDelete (String nameCity) : cette methode permet de créer une nouvel menuItem pour le menu "supprimer" (en règle générale, il n'est pas possible d'affecter un noeud enfant à deux parents différents, il faut en créer un nouveau), on l'ajoute au menu, puis lorsque celui-ci est sélectionné, cela va entrainer la suppression du menu "supprimer" mais aussi du menu "sélectionner". Pour y arriver on utilise la méthode "setOnAction()" dans laquelle on a définit une boucle qui va parcourir le menu select et s'il contient la ville à supprimer alors on la retire avec la méthode *remove()* comme ceci :

** select.getItems().remove(i); **

* exitScene (ActionEvent e) : permet de quitter la scène et de stopper le timer si celui-ci est en cours et n'a pas été stoppé avec la méthode *stopAct()*.

* stopAct (ActionEvent e) : permet de stopper le timer si celui-ci est en cours, on affiche alors un message afin d'avertir l'utilisateur. Pour stopper le timer, on utilise la méthode "cancel()".

* clean (ActionEvent e) : permet de réinitialiser l'ensemble de l'interface.

* hideMinMax () : cette méthode est utilisé dans la méthode *display()*, elle permet de cacher l'image des températures Max et Min si les deux sont invisibles sinon si l'un des deux est encore affichée, alors l'image reste visible.

* checkTextFieldCity () : cette méthode permet de vérifier si le textField n'est pas vide (renvoie un message d'erreur sinon) si on récupère le contenu et on appelle la méthode "checkCityName()".

* checkCityName (String city) : on crée une nouvelle instance de la classe MeteoCLient et on lui passe dans le contructeur la ville. Si le résultat aboutit à une requête non null alors on appelle la méthode "displayGUI()" sion on affiche un message d'erreur via l'interface avec le label `result`. On met à jour l'action en modifiant le label `lastUpdate` avec la méthode *getDate()*.

* valider (ActionEvent e) : cette méthode est utilisé pour déchencher l'action lorsque l'on appuie sur le bouton 'Valider'. Cette méthode appelle `checkTextFieldCity()` qui va appeller d'autres méthodes, s'il y a une erreur, on l'affiche en console.

* getTemp (Float nb, Boolean temperature) : cette méthode permet de convertir du Farhenheit en Celsius. Dans cette méthode, la nombre entré est un float. 

* getTempDouble (Double nb, Boolean temperature) : cette méthode permet de convertir du Farhenheit en Celsius. Dans cette méthode, la nombre entré est un double. 

* getDate () : cette méthode permet de récupéré la date du jour et d'afficher l'heure pour indiquer la dernière mise à jour.

* addDays (int nbDays) : permet d'obtenir les dates des jours suivants le jour actuel et de les afficher dans les 'conteneurs' pour les prévisions des jours suivants.

* getTimeSun (long time) : permet d'obtenir sous format d'heures et minutes, le coucher et le lever du soleil.

* displayGUI (Result res) : à partir de la requête, on va pouvoir récupérer depuis la classe Result, les informations de la requête tel que le vent, les températures, la pression etc. On affiche également les îcones en lien avec les données qui sont dans le repertoire `/resources/images/`.

* getIcon (int nbIcon, JSONObject obj) : permet de récupérer les îcones pour les prévisions des jours suivants et de retourner une string contenant le nom de l'icône. On y accède via l'objet JSON depuis la liste `list`, on recherche dans cette liste, une autre liste contenant les données dont le nom de l'icône nommé `weather`, puis on cherche l'identifiant et on stocke l'objet `icon` dans une string.

* getDesciption (int nbDesc, JSONObject obj) : permet de récupérer les îcones pour les prévisions des jours suivants et de retourner une string contenant la description. On y accède via l'objet JSON depuis la liste `list`, on recherche dans cette liste, une autre liste contenant les données dont la description nommé `weather`, puis on cherche l'identifiant et on stocke l'objet `description` dans une string.

* getTempMaxMin (int nbDesc, JSONObject obj, String temp_string) : permet de récupérer les îcones pour les prévisions des jours suivants et de retourner un double contenant la température. On y accède via l'objet JSON depuis la liste `list`, on recherche dans cette liste, un objet `main`qui va permettre de retrouner un double à partir de s'il s'agit de la température max, min, ressentie.

* displayPrevGUI ()  : permet d'afficher les prévisions des prochains jours. On utilise les méthodes *addDays()*, *getTempDOuble()*, *getTempMaxMin()*, *getDescription()*, *getIcon()*.

#### Exemple :

* date2.setText("J+1 : " + addDays(1)); *
* temp2.setText(getTempDouble(getTempMaxMin(8,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(8,obj,"temp_max"), true) + "°C"); *
* descrip2.setText(getDesciption(8,obj)); *
* imgIcon2.setImage(new Image("images/" + getIcon(8,obj) +".png")); *

* stateTimer ()  : retourne l'état du timer (booléen).

* stateCity ()  : retourne un booléen pour indiquer si le textField devant récupérer la ville est vide ou pas.

* stateRefresh ()  : retourne un booléen pour indiquer si le textField devant récupérer le nombre est vide ou pas.

* reload (ActionEvent event)  : permet de démarrer un timer qui va récupérer un nombre en minutes qui va être converti en millisecondes. On déclenche alors un thread (une tâche ou TimerTask), qui va rechargé les informations de l'interface, mettre à jour la maj tout les X minutes. Auquel cas, on retourne une Exception avec le message d'erreur (nombre invalide, saisir les deux champs etc). Afin que l'interface ne lague pas, on utilise la méthode *runLater()* qui permet de rafraîchir plus tard l'interface et de ne pas créer d'exception lié au Thread.

### JSON

* À partir de la requête JSON (getJsonWeatherByCityName() et getJsonWeatherByCityNameFor5()), on peut générér les classes qui nous permettent d'accéder directement aux élements sans avoir besoin de parser en JSON la requête (j'ai dû réalisé quand même la manoeuvre pour les prévisions des jours suivants). Nous obtenons les classes suivantes :
</br>
</br>
* City
* Clouds
* Coord
* Example
* List
* Main
* Rain
* Result
* Sys
* Weather
* Wind

Ces classes comportent essentiellement des getters et des setters permettant d'accéder aux contenus de la requête, comme par exemple :

Dans la classe Result, on peut d'accéder au contenu de Main avec la méthode *getMain()* depuis cette méthode, récupérer par exemple l'humidité avec la méthode *getHumidity()*.

## View - Window.fxml 

</br> Il s'agit d'une fenêtre *.fmxl* crée à partir du logiciel **SceneBuilder** depuis **Eclipse**.

</br>![Image not found](images/scene.png "applciation")

Afin de positionner les élements, j'ai utilisé une VBox, un SplitPane et des AnchorPanes.

Les différents éléments de l'interface :

* Menubar comprenant 3 menus : affichage, ma liste, paramètres.
** Affichage : humidité (MenuItem), pression (MenuItem), vent (MenuItem), température Max (MenuItem), température Min (MenuItem), ressentie (MenuItem), lever (MenuItem), coucher (MenuItem).
** Ma liste : sélectionner (Menu), ajouter (MenuItem), supprimer (Menu).
** Paramètres : nettoyer (MenuItem), actualisation arrêt (MenuItem), quitter (MenuItem).
* Button `Valider` : permet d'avoir les prévisions pour la ville renseignée.
* Button `Actualiser` : permet d'actualiser toutes les X minutes l'interface.
* TextField `city` : permet de renseigner la ville.
* TextField `refresh` : permet de renseigner le nombre de minutes.
* label `lastUpdate` : permet de retourner la date et l'heure de la dernière action.
* label `result` : retourne un message pour indiquer si la recherche s'est bien passé ou un message d'erreur.

### Pour les prévisions du jour

* label `cityCountry` : affiche la ville et le pays à partir du TextField.
* label `temperature` : affiche la température.
* label `description` : affiche la description de la météo.
* label `tempMax` : affiche la température maximale.
* label `tempMin` : affiche la température minimale.
* label `humidity` : affiche le taux d'humidité.
* label `wind` : affiche la vitesse du vent (en m/s).
* label `sunrise` : affiche l'heure de lever du soleil.
* label `sunset` : affiche l'heure du coucher du soleil.
* label `pressure` : affiche la pression en hPa.
* label `alterTemp` : affiche la température ressentie.

* ImageView `imgMinMax` : image en lien avec les températures max et min.
* ImageView `imgTemp` : image en lien avec l'icon de la requête.
* ImageView `imgWind` : image en lien avec le vent.
* ImageView `imgSunrise` : image en lien avec le lever du soleil.
* ImageView `imgSunset` : image en lien avec le coucher du soleil.
* ImageView `imgWeather` : image en lien avec l'humidité.
* ImageView `imgAlter` : image en lien avec la température ressentie.
* ImageView `imgPres` : image en lien avec la pression.

### Pour les prévisions sur 4 jours :

Il s'agit de 4 Anchorpanes avec chacun contenant :

* label `dateX` : la date du jour +X.
* label `tempX` : la température du jour +X.
* ImageView `imgIconX` : l'icon du jour +X.
* label `descrpX` : la description de la météo +X.

## Le POM XML 

</br> Le pom.xml contient le modèle objet pour ce projet. Il contient toutes les informations importantes sur ce projet. Il permet de référencer le groupId, artifactId, version, nom, description, etc. Mais également d'y ajouter des plugins et dépendances pour compiler les ressources du projet permettant d'obtenir le *.bat* ou encore la possibilité de générer des archives (.zip,.rar) avec un *.Jar*.

* Dans le POM.XML, l'encondage est définie en UTF-8, défini dans `properties` :

* ![Image not found](images/pom.png "applciation")

* Dans le POM.XML, la version de Java, définie dans `dependencies` :

* ![Image not found](images/pom1.png "applciation")

Les dépendances ci-dessus permettent de lire et récupérer les informations des requêtes JSON.

* ![Image not found](images/pom2.png "applciation")

* Ce plugin permet de générer automatiquement la distribution binaire du projet, dans le dossier *scripts* à la racine du projet. Cette génération est fixée à l'étape package de `Maven`.

* ![Image not found](images/pom3.png "applciation")