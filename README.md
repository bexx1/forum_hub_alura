<h1 align="center">Forum Hub Challenge</h1>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17+-blue.svg">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-3.6.3-blue.svg">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.3.1-brightgreen.svg">
  <img alt="PostgreSQLQL" src="https://img.shields.io/badge/MySQL-8.0.37-blue.svg">
  <img alt="Status do Projeto" src="https://img.shields.io/badge/status-Concluído-green">
</p>

<p align="justify">This project was a challenge proposed by the Oracle team in partnership with Alura. It works like a forum where, after you log in with an account, you can post questions that you have.</p>

## How to use the api

Clone the repository
    
  ```bash
  git clone https://github.com/bexx1/forum_hub_alura.git
  ```

Configure the MYSQL database:
   - Create a database named "forum_hub"
   - In the file application.properties, `src/main/resources/application.properties`, you'll find [environment variables](https://vercel.com/docs/projects/environment-variables). <br/>
      - A datasource url varieble {URL_MYSQL_FORUMHUB}; 
      - A datasource username ${USER_MYSQL}; 
      - A datasource password ${PASSWORD_MYSQL};
      - And a api security token secret ${JWT_SECRET:123456}.
  If the environment variable isnt created the secret used will be the 123456 combination.<br/>
     You can either add those to your pc, setting your own information, or replace them with your info directly
     (I don't recomend this option if you want to post your project after finished because it will leak your infos but if you only wanna have fun it's much simpler).
  
Add a User
  - In the table users a user must be created. Use INSERT INTO forum_hub.users VALUES (id, 'email', 'passwod', 'name'); to create a user. The password must be encrypted by the <a href="https://bcrypt-generator.com/#google_vignette">BCrypt</a> format.

## Requests
After you already have an user and the database ready to go you'll need to log in the server. 
  - The path '/login' is the first you'll need to go. Send a post request that have a body of a json that includes the email and password, in this time it can't be encrypted, must be the correct password.
   It'll return a token. This will be needed to complete all the following api requests. You will need to add the token to the authorization header, without it the requests will receive a 403 forbidden message.
  - The path '/topics' can receive 4 request types: get, post, put and delete.
    - Get: To get all the topics the only thing needed is the token in the authorization header.
    - Post: A json is mandatory in this request. It must contain the title, message and course infos. 
    - Put: Here a json very similar to the post one needs to be sent. It can contain the id, title, message and course infos. Only pass what you want to change. The id is mandatory but it cannot be changed.
    - Delete: To delete a topic you'll need to pass in the path the id of the topic. Ex: /topics/1 - will exclude the topic of id 1.
  - The path '/topics/{id}' when used with the method get returns the details of the topic that corresponds to that id.
