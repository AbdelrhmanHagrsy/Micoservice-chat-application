
# Chat Service Setup Guide [Abdelrahman Adel]

## Instructions to Set Up and Run the Project

1. Run the Docker Compose File
Start by running the attached Docker Compose file to initialize all required services.

2. Create a New Kafka Topic called 'chat-messages-topic' with two partition steps :

* Open a terminal or command prompt.

* docker ps
    
* docker exec -it <kafka-container-name-or-id> /bin/bash

* /usr/bin/kafka-topics --create --topic chat-messages-topic --bootstrap-server localhost:9092 --partitions 2 --replication-factor 1

3. Start All Services
Ensure all necessary services are up and running.

4. Run Two Chat Service Instances

* Build the chat service:  mvn clean install

* Navigate to the `target` folder of your project in the terminal.

* java -jar -Dspring.profiles.active=instanceOne <your-jar-name>.jar

* java -jar -Dspring.profiles.active=instanceTwo <your-jar-name>.jar
   
Note : The code is designed to run with *two instances* of the chat service. Follow these steps:


5. Locate HTML Files In the `chat-service` project directory, navigate to: `src/main/resources/static`

* You will find two HTML files that simulate the chat functionality.

6. Test the Chat Application
Open both HTML files in your browser to test the chat service functionality between the two instances.
