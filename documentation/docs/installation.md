#Éxécuter l'application 

##Pré-requis

Pour pouvoir utiliser cette application, que vous soyez sur Linux, MAC OS ou Windows, vous devez installer ou posséder <a href="https://www.oracle.com/java/technologies/javase-jdk8-downloads.html">JAVA JDK8</a>.

##Distribution source

### Depuis le fichier source

Il faut télécharger la distrubution source qui a été déposé sur Arche `TP3-CHARLES-0.0.1-SNAPSHOT-project.zip.`

Voici l'arborescence du projet, une fois dézippé :

</br>![Image not found](images/conf.png "install")

### Depuis GitHub

Pour récupérer les fichiers sources depuis le dépôt GIT, il vous faut d'abord cloner ou télécharger sous format `zip` :

#### Pour cloner en SSH

</br>![Image not found](images/git_ssh.png "install")

#### Pour cloner en HTTP

</br>![Image not found](images/git_http.png "install")

Il ne vous reste plus qu'à importer votre projet depuis un IDE tel que `Eclipse` ou `IntellJ` par exemple. 

###Arborescence

* `scripts/bin` : contient les fichiers exécutables (distribution binaire) ;

* `src/main/java/fr/ul/miage/` : contient le code Java (voir Manuel technique).

###Executer le programme

* Lancer un utilitaire de commandes :
	* Sous Windows : une invite de commandes
	* Sous Linux/Unix/Mac  : un terminal
* À l'aide de la commande *cd*, rendez-vous dans le répertoire où se trouve le *.bat*, ou ouvrez directement l'utilitaire dans le répertoire,
* Executer la commande **app.bat** sous Windows,
	* la fenêtre de l'application s'ouvrira.
* Executer la commande la commande **java -jar** avec le *.jar* sous Linux/Mac OS,
	* la fenêtre de l'application s'ouvrira.

## Démonstration

### Sur Windows :

</br>![Image not found](images/install.png "install")

Ouverture de l'application :

</br>![Image not found](images/install2.png "install")

Vous pouvez également (marche en tout cas sur Windows), double-cliquez sur le fichier **app.bat**, l'application s'ouvrira.

### Sur Linux/Mac OS:

#### Exécuter le fichier app avec la commande `sh`
</br>![Image not found](images/install3.png "install")

Ouverture de l'application :

</br>![Image not found](images/install4.png "install")

## En cas de problème :

Si vous êtes un utilisateur Mac ou Linux, vérifier que vous avez bien les droits sur le fichier pour le lancer. Voici un lien qui explique comment le faire pour :

### MAC

Pour Mac : <a href="https://www.alvinpoh.com/how-to-make-and-run-batch-files-in-terminal-in-mac-osx/">Solution Mac</a>

### LINUX

Pour Linux : <a href="https://stackoverflow.com/questions/17015449/how-do-i-run-sh-or-bat-files-from-terminal">Solution LINUX</a>