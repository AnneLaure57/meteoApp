# Liste des Fonctionnalités

## Interface de l'application Météo

</br>![Image not found](images/interface1.png "interface")

### 1) Chercher les prévisions pour une ville (en France)

En rentrant le nom d'une ville, vous pouvez obtenir les prévisions du jour plus ceux des 4 prochains jours (l'api ne permet d'avoir que les prévisions des 5 prochains jours en version gratuite). Peu importe l'écriture (Majuscule ou minucule), tant que la ville est bien écrite, la recherche se déroulera sans problème, cliquez ensuite sur le bouton *Valider*. 

** Pour les villes françaises, vous n'avez pas besoin de renseigner obligatoirement le pays (FR). **

** Si vous souhaitez rechercher une autre ville dans un autre pays, vous devez en plus renseigner le pays, exemple : ville : Dallas, pays : US. **

</br>![Image not found](images/interface2.png "interface")

Ville internationale :

![Image not found](images/interface23.png "interface")

### 2) Rafraîchir pour obtenir les prévisions de la ville toutes les X minutes

Il est possible de demander un rafraîchissement de l'interface toutes les X minutes.

#### Rentrer un nombre :

</br>![Image not found](images/interface3.png "interface")

#### Cliquez sur le bouton "Actualiser" :

</br>![Image not found](images/interface4.png "interface")

#### Et c'est parti !

</br>Une fois que vous avez cliqué sur le bouton, un nouveau message s'affichera en vous indiquant qu'une nouvelle actualisation est en cours.

</br>![Image not found](images/interface5.png "interface")

La date de mise à jour sera rafraîxhi toutes les X minutes.

![Image not found](images/interface6.png "interface")

### Stopper l'actualisation

</br> Dans la barre de navigation, cliquez sur "paramètres" > "Actualisation arrêt" pour stopper le timer. Vous aurez alors un message pour vous dire que vous avez bien stoppé l'actualisation comme ci-dessous :

![Image not found](images/interface7.png "interface")

#### Exemple :

![Image not found](images/interface8.png "interface")

### Nettoyer l'interface

</br> Dans la barre de navigation, cliquez sur "paramètres" > "Nettoyer" pour remettre à 0 l'application. Vous aurez alors un message pour vous dire que vous avez bien stoppé l'actualisation comme ci-dessous :

![Image not found](images/interface9.png "interface")

### 3) Personnaliser l'affichage 

</br> Vous pouvez à tout moment, vous pouvez dans le menu "Affichage" et définir les options à afficher :

![Image not found](images/interface10.png "interface")

* Remarque : pour que l'image de thermomètre disparraisse, il faut que le températures max et min soient "Disable" toutes les deux.

#### Exemple :

![Image not found](images/interface11.png "interface")

#### Exemple pour les températures "MinMax" :

![Image not found](images/interface12.png "interface")

### 4) La liste personnalisée

</br> Dans le menu "Ma liste", il y a 3 options qui sont :

* Sélectionner
* Ajouter
* Supprimer

`Sélectionner` : permet depuis la liste de faire une recherche des prévisions météo d'une ville.

`Ajouter` : ajouter une ville dans la liste depuis un champ de texte.

`Supprimer`: supprimer une ville de la liste personnalisée.

#### Sélectionner

![Image not found](images/interface13.png "interface")

![Image not found](images/interface14.png "interface")

#### Ajouter

![Image not found](images/interface15.png "interface")

** Comme expliquer plus haut, il n'est pas nécessaire de renseigner le pays, pour les villes en France, par contre si vous souhaitez ajouter une autre ville, il faut renseigner le pays comme**

![Image not found](images/interface16.png "interface")

#### Supprimer

![Image not found](images/interface17.png "interface")

* Remarque : si une ville est supprimée, elle est supprimée définitivement de la sélection, il est possible de l'ajouter à nouveau.

![Image not found](images/interface18.png "interface")

## Les messages d'erreurs

Voici-ci dessous quelques exemples de messages d'erreurs.

### Pour actualiser :

![Image not found](images/interface20.png "interface")

![Image not found](images/interface21.png "interface")

![Image not found](images/interface22.png "interface")

* Remarque : le message s'active si les champs sont vides ou incorrectes. Si l'entrée pour le nombre de minutes n'est pas un entier alors l'actualisation n'aura pas lieu.

### Pour valider :

![Image not found](images/interface19.png "interface")

### Pour la séléction :

![Image not found](images/interface25.png "interface")

* Remarque : si une ville n'existe pas mais qu'elle est ajoutée à la liste, la recherche n'aura pas lieu quand même.