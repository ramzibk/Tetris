# 🎮 Tetris (Java)

**Tetris** (Java) is a classic game I implemented in **Java** during my second year of a Bachelor's in Computer Science as an **Erasmus student at the University of Liège, Belgium**.
This project demonstrates **object-oriented design, game loop implementation, collision detection, and basic graphics animation** using Java Swing.
It was built as **a hands-on learning exercise** to deepen my understanding of object-oriented programming, game mechanics, event-driven programming, and UI rendering in Java, and to strengthen my problem-solving and software development skills.

---

## 🧠 Overview

This repository contains a playable Tetris clone written in Java. It includes:

* Game loop and timing
* Shape generation & rotation
* Collision detection
* Line clearing
* Simple UI with keyboard controls

This project is suitable for learning how game logic works in Java and understanding key programming principles like event handling and rendering.

---

## 📁 Tech Stack

| Category    | Technologies |
| ----------- | ------------ |
| Language    | Java         |
| Graphics    | Java Swing   |
| Build / Run | JDK          |

---

## 📦 Prerequisites

To compile and run this project, you need:

* **Java Development Kit (JDK)** – version 11 or higher

---

## ▶️ Running the Game

### 1. Clone the repository

```bash
git clone https://github.com/ramzibk/Tetris.git
```

### 2. Navigate into the project folder

```bash
cd Tetris
```

### 3. Compile the source code

```bash
javac -d bin src/*.java
```

*(If you use an IDE like IntelliJ or Eclipse, you can import the project and run the Main.java class directly.)*

### 4. Run the game

```bash
java -cp bin Main
```

---

## 🕹 Controls

| Action       | Key   |
| ------------ | ----- |
| Move Left    | ← / A |
| Move Right   | → / D |
| Rotate Piece | ↑ / W |
| Drop Piece   | ↓ / S |
| Quit Game    | Esc   |

---

## 📌 How It Works

This game uses a classic Tetris approach:

* Tetromino shapes are defined and rotated
* Game loop controlled by timing and key input
* Lines are cleared when fully filled
* Score is tracked based on completed lines

The UI is built with **Java Swing**, and rendering is handled in the game loop inside a Swing panel.

---

## 📜 Project Structure

```
.
├── src/                # Java source files
├── bin/                # Compiled classes (created after build)
├── README.md           # This file
```

---

## 📈 Learnings & Highlights

* Java graphics with Swing
* Game loop implementation
* Shape rotation algorithms
* Event handling and keyboard input
* Simple collision detection logic

---

## 🙌 Contributions

This is a personal project — contributions are welcome! You can help by:

* Adding levels or difficulty scaling
* Improving UI and visuals
* Adding sound effects and music
* Creating a score leaderboard

To contribute:

1. Fork this repository
2. Create a feature branch
3. Submit a pull request

---

## 📝 License

Feel free to use and adapt this project. If you choose to add a license, include it here (e.g., MIT, Apache 2.0).
