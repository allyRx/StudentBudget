
# 💰 Student Budget Control

> Application de gestion budgétaire pour étudiants avec supervision parentale.

## 🧠 Description

Cette application permet à un **étudiant** de saisir ses dépenses mensuelles et à ses **parents** de suivre l’utilisation de l’argent qu’ils envoient. Le but est d’inculquer une gestion responsable de l’argent, tout en fournissant une **traçabilité claire**, des **statistiques visuelles**, et des **rapports PDF mensuels**.

---

## 🚀 Fonctionnalités principales

### ✅ Authentification & Sécurité
- Inscription avec rôle (étudiant ou parent)
- Connexion sécurisée avec JWT
- Vérification de l’email via un code de validation
- Gestion des rôles et autorisations

### 👨‍👩‍👦‍👦 Gestion des comptes
- Un parent peut inviter un étudiant (lien parent-étudiant)
- Un étudiant peut être lié à un seul parent

### 💸 Suivi budgétaire
- Le parent définit le **budget mensuel** de l’étudiant
- L’étudiant enregistre chaque **dépense** (libellé, montant, catégorie, date)
- Visualisation mensuelle des dépenses
- Reste du budget visible en temps réel

### 📊 Statistiques et reporting
- Graphique circulaire et linéaire des dépenses par mois
- Filtrage par catégorie/date
- Génération de rapport **PDF mensuel** des dépenses
- Export de données

### 📤 Notification
- Notification par mail lors de la création du compte
- Notification optionnelle au parent en cas de dépassement de budget (à venir)

---

## 🧱 Architecture technique

- Backend : **Spring Boot 3** + **Spring Security**
- Base de données : **PostgreSQL**
- Email : **smtp4dev** (en développement)
- Interface admin : **Adminer**
- Conteneurisation : **Docker Compose**
- Tests : **JUnit**, **Testcontainers** (à venir)
- Méthodologie : **Agile**, **MVP**

---
## Installation
 ```
- git clone https://github.com/allyRx/StudentBudget.git
- cd StudentBudget
- dcker-compose up -d
  ```
#Prerequis 
- IDE
- docker

## 🧪 Endpoints principaux

| Méthode | Endpoint                      | Rôle     | Description                          |
|---------|-------------------------------|----------|--------------------------------------|
| POST    | `/api/auth/register`          | Tous     | Créer un compte                      |
| POST    | `/api/auth/login`             | Tous     | Connexion + récupération du token    |
| POST    | `/api/auth/verify-email`      | Tous     | Vérification du code reçu par email  |
| GET     | `/api/users/me`               | Tous     | Infos utilisateur connecté           |
| POST    | `/api/budget`                 | Parent   | Créer un budget pour un étudiant     |
| GET     | `/api/budget`                 | Étudiant | Voir son budget actuel               |
| POST    | `/api/expenses`               | Étudiant | Ajouter une dépense                  |
| GET     | `/api/expenses/monthly`       | Étudiant | Liste des dépenses du mois           |
| GET     | `/api/expenses/report/pdf`    | Étudiant | Télécharger le PDF mensuel           |

