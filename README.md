# Store Simulation Project

## Overview
The Store Simulation Project is a Java-based application that simulates a basic store interaction environment. It provides functionality for players to buy and sell items using a simple console interface. The project is structured into three main classes: `Player`, `Store`, and `Escrow`, each responsible for handling different aspects of the store simulation.

## Features

### Store
- **Manage Inventory**: Add and remove items from the store inventory.
- **Display Inventory**: Show all items available for purchase in the store.
- **Buy and Sell Items**: Facilitate transactions where players can buy or sell items.
- **Enter and Exit**: Handle players entering and exiting the store.

### Player
- **Manage Money**: Each player has an account balance used for buying and selling items.
- **Manage Inventory**: Players can acquire or relinquish items.
- **Buy Using Escrow**: Players can initiate a purchase transaction that's held in escrow until finalized.
- **Sell Using Escrow**: Players can initiate a selling transaction where items are held in escrow until the transaction is finalized.

### Escrow
- **Manage Transactions**: Temporarily hold money and items during escrow transactions.
- **Finalize Transactions**: Complete or cancel transactions based on certain conditions.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) installed on your machine.

### Running the Application
1. **Compile the Java Files**: Navigate to the project directory and compile the Java files using: javac Main.java Player.java Store.java Escrow.java Item.java
2. **Run the Application**: Start the application by running: java Main


### How to Use
- **Enter the Store**: On the main menu, select the option to enter the store.
- **Buy an Item**: Choose to buy an item, select from the available inventory, and confirm the purchase.
- **Sell an Item**: Choose to sell an item from your inventory.
- **View Inventories**: At any point, view the contents of the store's or the player's inventory.
- **Exit the Store**: When done, select the option to exit the store.

## License
This project is open source and available under the [MIT License](LICENSE.md).


