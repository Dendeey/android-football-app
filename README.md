# Mini-app Recherche de Joueurs de Foot ⚽️

Ce projet est une mini-application Android réalisée en Kotlin, basée sur l'architecture MVVM.  
Elle permet de rechercher des joueurs de football grâce à l’API Football (https://www.api-football.com/), avec une interface traduite français/anglais.

## Fonctionnalités

- Page d’accueil avec barre de recherche de joueurs
- Liste de résultats dynamique
- Page détail joueur avec infos supplémentaires
- Internationalisation (français et anglais)
- Architecture moderne MVVM (Model – View – ViewModel)
- Appels réseau à l’API Football

## Organisation du code

com.example.android_football_app
├─ model # Les classes de données (ex: Player)
├─ repository # L’accès aux données et à l’API
├─ viewmodel # La logique de présentation (ViewModels)
└─ view # Les Activities/Fragments (interface utilisateur)

## Lancer le projet

1. Ouvre le projet dans Android Studio (Meerkat ou supérieur)
2. Configure une API Key pour [api-football.com](https://www.api-football.com/)
3. Lance l’émulateur ou branche un appareil Android
4. Compile et amuse-toi !

## Auteur

David SIM

---

> Projet réalisé dans le cadre d’un projet étudiant.