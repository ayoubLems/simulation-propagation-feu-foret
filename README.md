# PropagationFeuForet

Ce projet simule la propagation d'un feu de forêt sur une grille 10x10. La forêt est représentée par une grille où chaque case peut avoir un état parmi :
- JamaisBrûlée (vert)
- EnFeu (rouge)
- RemplieDeCendre (gris)

Le feu se propage selon une probabilité définie dans un fichier de configuration.

## Fonctionnalités

- Initialisation d'une forêt à partir d'un fichier `input.txt`.
- Visualisation de la propagation du feu étape par étape.
- Mise à jour graphique dynamique de l'état des cases.

## Captures d'écran

### Grille initiale avec des cases en feu :
<img width="912" alt="Capture d’écran 2025-01-07 à 22 05 06" src="https://github.com/user-attachments/assets/1dd8827c-f93b-445b-b440-43f00a714e48" />


### Simulation terminée :
<img width="795" alt="Capture d’écran 2025-01-07 à 22 05 28" src="https://github.com/user-attachments/assets/afa05035-50aa-41bb-aca8-24684cbc5b52" />


## Format du fichier `input.txt`

Le fichier `input.txt` doit contenir :
1. Le nombre de lignes de la grille.
2. Le nombre de colonnes de la grille.
3. La probabilité de propagation (format : `0.4` en anglais ou `0,4` en français, selon la configuration régionale de votre système).
4. Une liste des cases initialement en feu, au format `ligne colonne` (exemple : `0 1` pour la case de la première ligne, deuxième colonne).

### Exemple :
```
10
10
0.4
0 1
0 6
1 8
2 1
2 9
3 4
4 6
6 9
7 1
7 3
9 7
```

## Exécution

1. Assurez-vous que le fichier `input.txt` est placé dans le dossier `resources` du projet.
2. Compilez et exécutez le projet.
3. Une fenêtre s'ouvrira pour afficher la grille et vous permettra de simuler la propagation du feu.

## Structure du projet

```
PropagationFeuForet
├── src
│   ├── main
│   │   ├── java
│   │   │   └── ma.annotation
│   │   │       ├── App.java        // Point d'entrée principal
│   │   │       ├── Foret.java      // Gestion de la logique de la forêt
│   │   │       └── ForetUI.java    // Interface graphique
│   ├── resources
│   │   └── input.txt               // Fichier de configuration
└── test
    ├── java
    │   └── ma.annotation
    │       ├── AppTest.java        // Tests pour l'initialisation
    │       └── ForetTest.java      // Tests pour la logique de la forêt
```

## Remarque importante

Le format de la probabilité (`0.4`) pourrait être incorrect en fonction de la configuration régionale de votre système. Par exemple :
- En anglais, le séparateur décimal est un point (`0.4`).
- En français, le séparateur décimal est une virgule (`0,4`).

Si votre système attend une virgule, il ne pourra pas interpréter correctement `0.4`. Assurez-vous d'utiliser le format adapté à votre configuration.

## Tests

Les tests unitaires sont situés dans le dossier `test/java/ma/annotation` :
- **AppTest** : Vérifie l'initialisation de la forêt à partir du fichier `input.txt`.
- **ForetTest** : Vérifie la logique de propagation du feu et les cases adjacentes.

Pour exécuter les tests, utilisez votre IDE ou un outil comme Maven.

## Dépendances Maven
```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
