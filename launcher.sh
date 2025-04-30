#!/bin/bash

echo "Lets Start the Projects!"
echo "1. Land Management"
echo "2. Mission Management"
echo "3. Nursery Management"
read -p "Enter your choice (1-3): " choice

case "$choice" in
    1)
        echo "Starting Land Management..."
        cd Land
        javac -d . $(find . -name "*.java")
        java Land.LandRegistry
        ;;
    2)
        echo "Starting Mission Management..."
        cd MissionManagementSystem
        javac -d . $(find . -name "*.java")
        java MissionManagementSystem.Main
        ;;
    3)
        echo "Starting Nursery Management..."
        cd Nursel
        javac -d . $(find . -name "*.java")
        java Nursel.Main
        ;;
    *)
        echo "Invalid choice. Please try again."
        ;;
esac