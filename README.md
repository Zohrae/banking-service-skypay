# Banking Service - Skypay

## Description
Système bancaire simple en Java 21 avec :
- Dépôt et retrait
- Relevé de compte (ordre antichronologique)
- Gestion des exceptions (montants invalides, solde insuffisant)

## Prérequis
- Java 21
- Maven
- IDE (IntelliJ, Eclipse, VS Code)

## Installation & Test
```bash
git clone https://github.com/Zohrae/banking-service-skypay.git
cd banking-service-skypay
mvn clean test
```

Structure

```bash

src/
 ├─ main/java/com/skypay/banking/
 │   ├─ Account.java
 │   ├─ Transaction.java
 │   └─ exceptions/
 │       ├─ InvalidAmountException.java
 │       └─ InsufficientFundsException.java
 └─ test/java/com/skypay/banking/
     └─ AccountTest.java
pom.xml
.gitignore
```
