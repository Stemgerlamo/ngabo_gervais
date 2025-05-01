#!/bin/bash

while true; do
    echo
    echo "=============================="
    echo "   Welcome to Project Hub"
    echo "=============================="
    echo "1. Land Management"
    echo "2. Mission Management"
    echo "3. Nursery Management"
    echo "4. Exit"
    echo "------------------------------"

    read -p "Enter your choice (1-4): " choice

    case $choice in
        1)
            echo
            echo "▶ Starting Land Management..."
            echo "------------------------------"
            java Land.LandRegistry
            ;;
        2)
            echo
            echo "▶ Starting Mission Management..."
            echo "------------------------------"
            java MissionManagementSystem.Main
            ;;
        3)
            echo
            echo "▶ Starting Nursery Management..."
            echo "------------------------------"
            java Nursel.Main
            ;;
        4)
            echo
            echo "👋 Exiting Project Hub. Goodbye!"
            break
            ;;
        *)
            echo "❌ Invalid choice. Please enter 1-4."
            ;;
    esac

    echo
    read -p "🔁 Press Enter to return to the menu..."
    clear
done
