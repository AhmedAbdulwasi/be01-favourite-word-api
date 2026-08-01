# Favourite Word API
An API to find the Internet's favourite word in the English Language! Features include: GET a word, GET Vote Stats, POST to vote on a word

## Why?
I've found simple experimental websites to be intriguing and I've began to love Spring Boot and backend from my RBC Internship. I thought of creating my own experimental website to find what is the Internet's favourite word, explore my interest in backend and Spring Boot while at the same time improve my vocabulary. Additionally, I've used AI to help me break down the project into tickets to pick up and serve as a peer programmer furthering my knowledge in AI code assistant tools such as Github Copilot.


## How To Use
1. Create database:
   CREATE DATABASE favourite_word_db;

2. Update application.properties with your MySQL username/password.

3. Run the app:
   `mvn spring-boot:run`

4. Test API (Use Postman):
   - GET /api/health
   - POST /api/words/submit
   - GET /api/words/stats

## Languages and Technologies
- Spring Boot
- MySQL
- API Used: https://dictionaryapi.dev/

## Quick Demo
https://github.com/user-attachments/assets/849c79da-2388-4cda-8c8e-9733c315b384




