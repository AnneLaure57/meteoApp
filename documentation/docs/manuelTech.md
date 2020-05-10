#La documentation technique

## Les outils :

</br> Pour ce projet j'ai travaillé avec les outils suivants :

* Scene Builder : outil permettant de réaliser des interfaces JavaFX (version 8).
* Eclipse : IDE pour la programmation Java (jdk 1.8_0241).
* Maven & les plugins nécessaires pour générer les fichiers archives et le fichier binaire (inclus avec cette documentation).
* ObjeAid UML Diagramm : est un outil permettant de créer des diagrammes à partir d'Eclipse.

## Le diagramme de classe :

</br>![Image not found](images/diagrammeClasse.png "applciation")

* Au vue de la qualité de la photo et du nombres de classes, le diagramme est fournie avec les fichiers sources sous le nom de `diagramm_class.ucls`.

### Explications

Dans le diagramme, il y a les relations suivantes :

* **Weather** : associations avec la classe MeteoClent et la classe Result.
* **List** : associations avec les classe Wind, Weather, Rain, Clouds, Sys et Main.
* **Result** : associations avec les classes Weather, Wind, Clouds, Coord, Sys et Main.
* **Example** : associations avec les classes Weather, List, City et Main.
* **City** : association avec la classe Coord.

## Design Patterns 

Afin de tester de nouvelles choses, j'ai utilisé le design pattern MVC (Modèle - Vue - Controller).

## Les classes :

En plus de cette documentation, les classes ont été commentées avec le plugin permettant la javadoc.

### App.java 

Cette classe est la classe principale permettant de lancer l'interface (Baignoire.fxml). Il y a deux méthodes :

* **start()** : permettant de charger le fichier *.fxml*, de modifier le titre de la fenêtre (setTitle()), de spécifier le scène qui va être utilisé (setScene()), d'afficher la scène (commande show()). La taille de la fenêtre n'est pas modifiable (avec setResizable()), ceci évitant de modifier la taille de la fenêtre et l'affichage a été réalisé de la manière la plus simple possible afin de pouvoir l'afficher sur tous les types d'écran (15 pouces, 17 pouces, 19 pouces et inférieur).

* **main()** : qui permet de charger la fenêtre avec la commande *launch(args)* .

### Model - MeteoClient.java

La classe **MeteoClient** contient 6 paramètres :

* WEBSERVICE (String),
* lang (String),
* apiKey (String),
* city (String),
* country (String).

Il y a plusieurs constructeurs ainsi que les getters et setters.

* **buildRequest()** : permet de construire une requête pour le jour actuel.

* **getJsonWeatherByCityName()** : permet de se connecter à l'API pour obtenir le résultat de la requête pour le jour actuel. Cette requête va nous permettre de générer les classes pour accéder aux contenus de la requête.

* **getWeatherByCityName()** : permet construire la requête et d'accéder aux informations grâce à la classe `Result`. La langue a également été modifié afin d'avoir les noms des villes et pays en français.

* **buildRequestFor5()** : permet de construire une requête pour les prochains jours.

* **getWeatherByCityNameFor5()** : permet construire la requête et d'accéder aux informations grâce à la classe `Example`.

* **getJsonWeatherByCityNameFor5()** : permet de se connecter à l'API pour obtenir le résultat de la requête pour les prochins jours. Cette requête va nous permettre de générer les classes pour accéder aux contenus de la requête.


### Controller - Controller.java

* **initialize()** :  est la fonction qui s'exécute lorsque la fenètre FXML se créée. Lors de l'initialisation, on affiche le contenu des labels et des Images en modifiant le contenu avec les méthodes *setText()* et *setImage()*. On appelle également la fonction *loadDataA()*, qui permet de charger automatique les villes qui ont été sauvegardées dans la liste personnalisée.
<br /><br />
* **diplay(ActionEvent e)** : pour chaque MenuItem du menu "Affichage", on cherche à savoir si le menuItem est sélectionné (utilisation de isSelected()) alors si le label est visible (utilisation de isVisible()) alors on le cache ainsi que son image avec la méthode *setVisible()*. Pour déclencher la méthode, on utilise la méthode *setOnAction()*.
<br /><br />
* **setCityResearch(ActionEvent e)** : permet de d'afficher les prévisions pour une ville contenu dans le menu "sélectionner". On récupère le label du menuItem et on lance la méthode *checkCityName()*.
<br /><br />
* **addCityList(ActionEvent e)** : si le TextField ville n'est pas vide, on vérifie si celui du pays est vide, s'il est vide on récupère le texte avec la méthode *getText()* sinon on concat la string récupérée depuis le TextField ville et celui du pays afin de n'en former plus qu'une seule :
<br />
** nameResearch = nameCity + ',' + nameCountry; **
<br />
On  utilse la méthode *addIntoDelete(string)* pour l'ajouter au menu "supprimer". Afin d'éviter les doublons, on parcours la liste de menuItems et on vérifie que le dernier élément est bien différent de celui qu'on souhaite ajouter auquel cas, le nouveau menuItem ne sera pas créer sinon on l'ajoute au menu "sélectionner". Pour récupérer le contenu du textField, on utilise la méthode "getText()" puis pour l'ajouter on l'ajoute au menu concerné comme ceci :
```
for (int i = 0; i <= select.getItems().size() ; ++i) {
	//If the last element isn't not the same the new insert
	if (stateMenu(select,i,nameResearch) == true) {
		//LOG.info(newMenu.getText() + " on ajoute dans select");
		select.getItems().add(newMenu);
	}
}
```
On ajoute également ce menuItem au menu "supprimer", pour pouvoir le retirer de la liste personnalisée. Au moment où la ville est ajoutée, on lui donne la possibilité de faire une recherche de prévisions pour la ville en question avec les méthodes *setOnAction()* et *setCityResearch()*.
<br />

* **stateMenu(Menu menuCheck,int i, String newAddResearch)** : cette fonciton retourne un booléen, initialisé à faux (false). On stock le dernier élément de la liste du menu (select ou delete). Si l'élement est bien différent du dernier qui a été ajouté, alors on retourne true, sinon cela reste à false.
<br /><br />

* **addIntoDelete(String nameCity)** : cette methode permet de créer une nouvel menuItem pour le menu "supprimer" (en règle générale, il n'est pas possible d'affecter un noeud enfant à deux parents différents, il faut en créer un nouveau), on l'ajoute au menu (même principe que pour l'ajouter au menu "sélectionner" avec vérification qu'il n'y ai pas de doublons). Lorsque celui-ci est sélectionné, cela va entrainer la suppression du menu "supprimer" mais aussi du menu "sélectionner". Pour y arriver, on utilise la méthode "setOnAction()" dans laquelle on a définit une boucle qui va parcourir le menu select et s'il contient la ville à supprimer alors on la retire avec la méthode *remove()* comme ceci :
<br />
** select.getItems().remove(i); **
<br /><br />
On réalisé alors une nouvelle sauvegarde de la liste afin de mettre à jour la liste.

* **saveDataD(Menu menuToSave)** : cette methode permet de sauvegarder après la suppression d'un item du menu. Tout d'abord, nous avons défini une variable *path* de type String qui contient le chemin relative du fichier nommé `backup.txt`. On crée ensuite un fichier avec la possibilité de réecrire ce fichier (attribut false) comme ceci :
```
FileWriter writer = new FileWriter(file, false);
 BufferedWriter bufferedWriter = new BufferedWriter(writer);
 for (int i = 0; i < menuToSave.getItems().size(); i++) {
	bufferedWriter.write(menuToSave.getItems().get(i).getText());
	bufferedWriter.newLine();
 }
 bufferedWriter.close();
```
 Chaque ligne du menu (le contenu d'un menuItem est récupéré avec la commande getText()) sera lue et écrite dans le fichier. On affiche un message afin d'indiquer si la liste a bien été mise à jour ou non.
<br /><br />

* **saveData(MenuItem menuItemToSave)** : cette methode permet de sauvegarder après l'ajout d'un item dans le menu select. Tout d'abord, nous avons défini une variable *path* de type String qui contient le chemin relative du fichier nommé `backup.txt`. On crée ensuite un fichier mais celui-ci ne sera pas réécrit à chaque fois, car si l'on rajoute une nouvelle ville alors que l'application a été fermé, cela va conserver uniquement la nouvelle qui vient d'être ajouté, la selection précédente sera alors perdue. C'est pourquoi on met l'attribue *true*. 
```
FileWriter writer = new FileWriter(file, true);
BufferedWriter bufferedWriter = new BufferedWriter(writer);
bufferedWriter.write(menuItemToSave.getText());
bufferedWriter.newLine();
bufferedWriter.close();
```
Le contenu du menuItem (le texte) sera alors écrit dans le fichier à la suite du reste.On affiche un message afin d'indiquer si l'ajout c'est bien passé ou pas.
<br /><br />

* **loadData(ActionEvent event)** : cette methode permet de charger lorsque l'on clique sur "Recharger". Pour cela, on va lire le fichier qui a été sauvegardé avec les méthodes précédentes. Si le fichier existe, alors on crée un tampon permettant la lecture, tant que l'on a pas lu toutes les lignes du fichier alors on récupère le contenu de la ligne, on en crée un nouveau menu item que l'on ajoute aux menus select et deleteMenu (via la méthode addIntoDelete).
```...
FileReader reader = new FileReader(path);
BufferedReader bufferedReader = new BufferedReader(reader);
String line;
if (!reload) {
	while ((line = bufferedReader.readLine()) != null) {
    	reload = true;
    	MenuItem newM = new MenuItem(line);
    	...
    }
}
bufferedReader.close();
```
On affiche un message afin d'indiquer si les données ont été chargé ou si la liste est déjà à jour.

* **loadDataA()** : cette méthode réalise exactement la même chose que ce qui a été expliqué au dessus, sauf que celle-ci est appelé au démarrage de l'application, afin que l'utilisateur puisse avoir accès tout suite à sa liste, les données seront déjà chargées.
<br /><br />

* **exitScene(ActionEvent e)** : permet de quitter la scène et de stopper le timer si celui-ci est en cours et n'a pas été stoppé avec la méthode *stopAct()*.
<br /><br />
* **stopAct(ActionEvent e)** : permet de stopper le timer si celui-ci est en cours, on affiche alors un message afin d'avertir l'utilisateur. Pour stopper le timer, on utilise la méthode "cancel()".
<br /><br />

* **clean(ActionEvent e)** : permet de réinitialiser l'ensemble de l'interface.
<br /><br />
* **hideMinMax()** : cette méthode est utilisée dans la méthode *display()*, elle permet de cacher l'image des températures Max et Min si les deux sont invisibles sinon si l'un des deux est encore affichée, alors l'image reste visible.
<br /><br />
* **checkTextFieldCity()** : cette méthode permet de vérifier si le textField n'est pas vide (renvoie un message d'erreur sinon) si on récupère le contenu et on appelle la méthode "checkCityName()".
<br /><br />
* **checkCityName(String city)** : on crée une nouvelle instance de la classe MeteoCLient et on lui passe dans le contructeur la ville. Si le résultat aboutit à une requête non null alors on appelle la méthode "displayGUI()" sion on affiche un message d'erreur via l'interface avec le label `result`. On met à jour l'action en modifiant le label `lastUpdate` avec la méthode *getDate()*.
<br /><br />
* **valider(ActionEvent e)** : cette méthode est utilisée pour déchencher l'action lorsque l'on appuie sur le bouton 'Valider'. Cette méthode appelle `checkTextFieldCity()` qui va appeller d'autres méthodes, s'il y a une erreur, on l'affiche en console.
<br /><br />
* **getTemp(Float nb, Boolean temperature)** : cette méthode permet de convertir du Farhenheit en Celsius. Dans cette méthode, la nombre entré est un float. 
<br /><br />
* **getTempDouble(String nb, Boolean temperature)** : cette méthode permet de convertir du Farhenheit en Celsius. Dans cette méthode, la nombre entré est une string (permet d'éviter le cas où la température non convertie est un entier plutôt qu'un double). 
<br /><br />
* **getDate()** : cette méthode permet de récupérer la date du jour et d'afficher l'heure pour indiquer la dernière mise à jour.
<br /><br />
* **addDays(int nbDays)** : permet d'obtenir les dates des jours suivants le jour actuel et de les afficher dans les 'conteneurs' pour les prévisions des jours suivants.
<br /><br />
* **getTimeSun(long time)** : permet d'obtenir sous format heures et minutes, le coucher et le lever du soleil.
<br /><br />
* **displayGUI(Result res)** : à partir de la requête, on va pouvoir récupérer depuis la classe Result, les informations de la requête tel que le vent, les températures, la pression etc. On affiche également les îcones en lien avec les données qui sont dans le repertoire `/resources/images/`.
<br /><br />
* **getIcon(int nbIcon, JSONObject obj)** : permet de récupérer les îcones pour les prévisions des jours suivants et de retourner une string contenant le nom de l'icône. On y accède via l'objet JSON depuis la liste `list`, on recherche dans cette liste, une autre liste contenant les données dont le nom de l'icône nommé `weather`, puis on cherche l'identifiant et on stocke l'objet `icon` dans une string.
<br /><br />
* **getDesciption(int nbDesc, JSONObject obj)** : permet de récupérer les îcones pour les prévisions des jours suivants et de retourner une string contenant la description. On y accède via l'objet JSON depuis la liste `list`, on recherche dans cette liste, une autre liste contenant les données dont la description nommé `weather`, puis on cherche l'identifiant et on stocke l'objet `description` dans une string.
<br /><br />
* **getTempMaxMin(int nbDesc, JSONObject obj, String temp_string)** : permet de récupérer les îcones pour les prévisions des jours suivants et de retourner une string contenant la température. On y accède via l'objet JSON depuis la liste `list`, on recherche dans cette liste, un objet `main`qui va permettre de retrouner un double à partir de s'il s'agit de la température max, min, ressentie.
<br /><br />
* **displayPrevGUI()**  : permet d'afficher les prévisions des prochains jours. On utilise les méthodes *addDays()*, *getTempDOuble()*, *getTempMaxMin()*, *getDescription()*, *getIcon()*.
** Exemple : **
```
date2.setText("J+1 : " + addDays(1)); *
temp2.setText(getTempDouble(getTempMaxMin(8,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(8,obj,"temp_max"), true) + "°C"); *
descrip2.setText(getDesciption(8,obj)); *
imgIcon2.setImage(new Image("images/" + getIcon(8,obj) +".png")); *
``` 
* **stateTimer()** : retourne l'état du timer (booléen).
<br /><br />
* **stateCity()** : retourne un booléen pour indiquer si le textField devant récupérer la ville est vide ou pas.
<br /><br />
* **stateRefresh()** : retourne un booléen pour indiquer si le textField devant récupérer le nombre est vide ou pas.
<br /><br />
* **actualise(ActionEvent event)** : permet de démarrer un timer qui va récupérer un nombre en minutes qui va être converti en millisecondes. On déclenche alors un thread (une tâche ou TimerTask), qui va rechargé les informations de l'interface, mettre à jour la maj tout les X minutes. Auquel cas, on retourne une Exception avec le message d'erreur (nombre invalide, saisir les deux champs etc). Afin que l'interface ne lague pas, on utilise la méthode *runLater()* qui permet de rafraîchir plus tard l'interface et de ne pas créer d'exception lié au Thread.

### JSON

* À partir de la requête JSON (getJsonWeatherByCityName() et getJsonWeatherByCityNameFor5()), on peut générér les classes qui nous permettent d'accéder directement aux élements sans avoir besoin de parser en JSON la requête (j'ai dû réalisé quand même la manoeuvre pour les prévisions des jours suivants). Nous obtenons les classes suivantes :
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
* Affichage : humidité (MenuItem), pression (MenuItem), vent (MenuItem), température Max (MenuItem), température Min (MenuItem), ressentie (MenuItem), lever (MenuItem), coucher (MenuItem).
* Ma liste : sélectionner (Menu), ajouter (MenuItem), supprimer (Menu).
* Paramètres : nettoyer (MenuItem), actualisation arrêt (MenuItem), quitter (MenuItem).
* Button `Valider` : permet d'avoir les prévisions pour la ville renseignée.
* Button `Actualiser` : permet d'actualiser toutes les X minutes l'interface.
* TextField `city` : permet de renseigner la ville.
* TextField `country` : permet de renseigner le pays.
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
</br>![Image not found](images/pom.png "applciation")

* Dans le POM.XML, la version de Java, définie dans `dependencies` :
</br>![Image not found](images/pom1.png "applciation")

* Les dépendances ci-dessus permettent de lire et récupérer les informations des requêtes JSON.
</br>![Image not found](images/pom2.png "applciation")
* Celles ci-dessus sont utilisés pour la JAVADOC ainsi que pour l'utilisation des outils pour la lecture et l'écriture des fichiers (*java.io*).
</br></br>
* les plugins permet de générer automatiquement la distribution binaire du projet, dans le dossier *scripts* à la racine du projet. Cette génération est fixée à l'étape package de `Maven`.
</br>![Image not found](images/pom3.png "applciation")

## Améliorations possibles

* Optimisation du code (avec d'autres design patterns);
* embellir l'interface (se baser sur les applications mobiles);
* faire du refactoring.