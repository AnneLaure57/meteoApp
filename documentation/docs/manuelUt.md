# Liste des fonctionnalités

## Interface de l'application Météo

</br>![Image not found](images/interface1.png "interface")

### 1) Chercher les prévisions pour une ville (en France)

En rentrant le nom d'une ville, vous pouvez obtenir les prévisions du jour ainsi que celles des 4 prochains jours (l'API ne permet d'avoir que les prévisions des 5 prochains jours en version gratuite). Peu importe l'écriture (majuscule ou minucule), tant que la ville est bien écrite, la recherche se déroulera sans problème, cliquez ensuite sur le bouton *Valider*, auquel cas, vous aurez un message d'erreur.

** Pour les villes françaises, vous n'avez pas besoin de renseigner obligatoirement le pays (FR). **

</br>![Image not found](images/interface2.png "interface")

Résultat :

</br>![Image not found](images/interface3.png "interface")

** Si vous souhaitez rechercher une autre ville dans un autre pays, vous devez en plus renseigner le pays, exemple : ville : Tokyo, pays : JP. **

Ville internationale :

![Image not found](images/interface23.png "interface")

#### Mise à jour faite le 07/05/2020 :

Il est désormais possible de sauvegarder et charger sa liste personnalisée. C'est-à-dire, s'il s'agit de votre première utilisation, vous n'aurez pas de fichier mais il sera généré automatiquement lors de votre premier ajout (vous insérez une ville sachant que `Paris,FR` est un exemple afin de montrer à l'utilisateur comment utiliser l'application). Dans le cadre d'un projet professionnel, il ne doit pas y avoir de choix impossable à l'utilisateur, cet onglet servant d'aide pour ce TP.
Le chargement des données sauvegardées est automatique lors du lancement de l'application, il en va de même pour la sauvegarde (lors d'un ajout ou d'une suppresion).

Concernant le code couleur des messages de l'application:

* <span style="color: #26B260">Vert</span> : l'action s'est déroulée sans problème, par exemple vous avez fait une recherche.
* <span style="color: #0C1BFB">Bleu</span>  : message pour avertir l'utilisateur d'un changement, par exemple vous avez supprimé un élément de votre liste.
* <span style="color: #FB0C0C">Rouge</span>  : erreur avec l'application, par exemple vous n'avez pas de sauvegarde existante en lien avec votre application.

Si vous avez ce message :
</br>![Image not found](images/save4.png "interface")

**Pas de panique**, c'est juste que vous n'avez pas encore de fichier de sauvegarde, il sera alors créé lors de vos ajouts.

Un fois votre ville insérée , vous aurez alors ce message :
</br>![Image not found](images/save1.png "interface")

Si vous supprimez une ville de votre liste, vous aurez alors ce message :
</br>![Image not found](images/save3.png "interface")

### 2) Rafraîchir pour obtenir les prévisions de la ville toutes les X minutes

Il est possible de demander un rafraîchissement de l'interface toutes les X minutes.

#### Rentrer un nombre :

</br>![Image not found](images/interface3.png "interface")

#### Cliquez sur le bouton "Actualiser" :

</br>![Image not found](images/interface4.png "interface")

#### Et c'est parti !

Une fois que vous avez cliqué sur le bouton, un nouveau message s'affichera en vous indiquant qu'une nouvelle actualisation est en cours.

</br>![Image not found](images/interface5.png "interface")

La date de mise à jour sera actualisée toutes les X minutes.

![Image not found](images/interface6.png "interface")

### Stopper l'actualisation

Dans la barre de navigation, cliquez sur "Paramètres" > "Actualisation arrêt" pour stopper le timer. Vous aurez alors un message pour vous dire que vous avez bien stoppé l'actualisation comme ci-dessous :

![Image not found](images/interface7.png "interface")

#### Exemple :

![Image not found](images/interface8.png "interface")

### Nettoyer l'interface

Dans la barre de navigation, cliquez sur "Paramètres" > "Nettoyer" pour remettre à 0 l'application. Vous aurez un message en bleu pour vous avertir qu'il n'y a aucune recherche car tout a été réinitialisé.

![Image not found](images/interface9.png "interface")

### Recharger 

Si vous n'avez pas encore fermé l'application et que vous souhaitez restaurer la dernière version (version au moment de l'ouverture de l'application), vous pouvez cliquer sur "Paramètres" > "Recharger". Si votre application est déjà à jour, vous aurez alors ce message :

![Image not found](images/save2.png "interface")

### 3) Personnaliser l'affichage 

Vous pouvez à tout moment, vous pouvez dans le menu "Affichage" et définir les options à afficher :

![Image not found](images/interface10.png "interface")

* Remarque : pour que l'image de thermomètre disparraisse, il faut que le températures max et min soient "Disable" toutes les deux.

#### Exemple :

![Image not found](images/interface11.png "interface")

#### Exemple pour les températures "MinMax" :

![Image not found](images/interface12.png "interface")

### 4) La liste personnalisée

Dans le menu "Ma liste", il y a 3 options qui sont :

* Sélectionner
* Ajouter
* Supprimer

`Sélectionner` : permet depuis la liste de faire une recherche des prévisions météo d'une ville.

`Ajouter` : ajouter une ville dans la liste depuis le / les champs de texte. Lorsque vous ajoutez une ville / une ville internationale, celle-ci sera sauvegardé dans un fichier texte, qui sera rechargé automatique lors du redélarrage de votre application.

`Supprimer`: supprimer une ville / une ville internationale de la liste personnalisée. Lorsque vous supprimez une ville / une ville internationale, les villes seront alors actualisées lors de votre prochain chargement.

#### Sélectionner

![Image not found](images/interface13.png "interface")

![Image not found](images/interface14.png "interface")

#### Ajouter

![Image not found](images/interface15.png "interface")

** Comme expliquer plus haut, il n'est pas nécessaire de renseigner le pays, pour les villes en France, par contre si vous souhaitez ajouter une autre ville, il faut renseigner le pays comme ci-dessous:**

![Image not found](images/interface16.png "interface")

#### Supprimer

![Image not found](images/interface17.png "interface")

* Remarque : si une ville est supprimée, elle est supprimée définitivement de la sélection, il est possible de l'ajouter à nouveau.

![Image not found](images/interface18.png "interface")

## Les messages d'erreurs

Voici-ci dessous quelques exemples de messages d'erreurs.

### Pour actualiser :

![Image not found](images/interface19.png "interface")

![Image not found](images/interface21.png "interface")

![Image not found](images/interface22.png "interface")

* Remarque : le message s'active si les champs sont vides ou incorrectes. Si l'entrée pour le nombre de minutes n'est pas un entier alors l'actualisation n'aura pas lieu.

### Pour valider :

![Image not found](images/interface20.png "interface")

### Pour la selection :

![Image not found](images/interface25.png "interface")

* Remarque : si une ville n'existe pas mais qu'elle est ajoutée à la liste, un message d'erreur sera retourné à l'utilisateur afin de l'avertir que sa recherche ne sera pas valide. Pour l'exemple ci-dessus, on a ajouté Dallas sans renseigner le pays (US).

### Pour le rechargement des données :

![Image not found](images/save5.png "interface")

Celà signifie qu'il est impossible de sauvegarder car le fichier n'existe pas ou a été supprimé. Vous devez alors relancer l'application.

