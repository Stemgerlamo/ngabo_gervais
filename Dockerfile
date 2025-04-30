FROM openjdk:11-slim

# Create a working directory
WORKDIR /usr/src/app

# Copy all project directories into the container
COPY src/Land/ Land/
COPY src/MissionManagementSystem/ MissionManagementSystem/
COPY src/Nursel/ Nursel/
COPY Launcher.sh .

# Make the script executable
RUN chmod +x Launcher.sh

# Set the default command
CMD ["./Launcher.sh"]