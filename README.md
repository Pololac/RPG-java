# Dungeons & Data — A Java Console RPG

🎮 Dungeons & Data is a simple text-based RPG game developed in Java as part of a programming project. The player controls a hero who battles an endless stream of randomly generated enemies in turn-based combat.

---

## 🚀 Features

- Turn-based combat system
- Random enemy generation (Goblin, Troll, Dragon)
- Hero abilities: basic attack, special power (mana), healing potion
- Score tracking with persistent file storage (`scores.txt`)
- Leaderboard displaying top players sorted by enemies defeated
- Exception handling:
    - Invalid menu input
    - Insufficient mana
    - No potions left

---

## 🗂 Technologies

- Java (JDK 17 recommended)
- IntelliJ IDEA (or any Java IDE)

---

## 📂 Project structure
````
src/
├── personnages/      # Hero, Enemy, Goblin, Troll, Dragon classes
├── services/         # Game and combat managers
├── scores/           # Score management and leaderboard
├── exceptions/       # Custom exceptions (invalid choices, mana, potions)
└── Main.java         # Application entry point
````

---

## ⚔ Gameplay

1. The player enters a name for their hero.
2. They choose to read the rules, start the game, or view the leaderboard.
3. During each fight, the player can:
    - Attack the enemy
    - Use a special power (requires mana)
    - Use a healing potion (limited per battle)
4. After each victory, a new enemy is generated.
5. The game continues until the hero dies.
6. The number of defeated enemies is saved to a file and displayed in the leaderboard.

---

## 📄 How to run

- Compile and run the project via your IDE or directly via terminal:
````
javac Main.java
java Main
````
---

## 🔥 Future improvements (possible extensions)

- Introduce XP that gives more attack & defense
- Multiple heroes with different abilities
- Create a Boss to defeat every 5 wins
- Difficulty levels

---

## 👨‍💻 Author

- Created as part of a Java programming training project

---

Enjoy the game and may your hero defeat many enemies!
