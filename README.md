# CheapSprint


#### Cette application Android connectée (ainsi que [sa partie web](http://80.211.56.41:8008)) permet aux utilisateurs :
* D'énoncer à la voix une liste de course et de la sauvegarder
* De partager ses bon plans avec les autres pour faire les courses le moins cher possible

#### Comment ça marche ? 
* Entrez des informations sur les produits à très bon prix que vous avez vu en donnant :
	*  la ville où vous vous trouvez
	*  le magasin dans lequel vous avez trouvé les produits en question
	*  le nom des produits
	*  les prix correspondant aux produits
* Ces informations sont envoyées ensuite dans une base de donnée en ligne où se trouvent tous les autres bon plans
* Pour récupérer les meilleurs prix :
	* Vous devez créer une liste. Pour ce faire, il n'y a rien de plus simple : lisez-la à haute voix (speech-to-text)
	* Pour finir, appuyez sur le bouton "Détails" et la magie opère !
* Vous avez les moyens de consulter ainsi que d'ajouter des informations dans la base de donnée grâce à notre site web [CheapSprint](http://80.211.56.41:8008)

##
##### Fonctionnalité implémentée à 95% :
* Gestion de listes : du speech-to-text pour énoncer les produits à la sauvegarde en passant par la suppression de produits (ou de listes) et la réception des bons plans (il manque juste le fait de pouvoir spécifier dans quel ville on se trouve).
##### Fonctionnalités implémentées partiellement :
* L'envoi depuis le téléphone vers la base de donnée d'ojets {ville, magasin, nom_produit, prix} n'est pas fait, cependant la réception fonctionne quand on va dans une liste puis qu'on appuie sur le bouton "Détails". 
* Gestion de la base de donnée [côté web](http://80.211.56.41:8008/).

##### Fonctionnalité prévue mais non implémentée :
* Lecteur de codes à barres (fonctionnant grâce à une API de [Open Food Facts](https://fr.openfoodfacts.org/)).