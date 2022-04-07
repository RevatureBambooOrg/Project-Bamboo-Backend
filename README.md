# Project Bamboo

## Project Description

Project Bamboo is a RESTful web service intended to give users the ability 
to study Java full stack developer programming concepts. This tool is intended
to be universally and easily accessible while also being divisible and precise
so users can study specific subjects at any given time.

## Technologies Used

* Angular - Version 11
* Spring MVC
* PostgreSQL database hosted on a RDS on an EC2
* Googledrive API
* AWS S3
* AWS Pipeline

## Features

List of features ready and TODOs for future development
* Registration and login
* Flashcard creation
* Flashcard search
* Flashcard Viewing

To-do list:
* Linking drive to user accounts
* Allowing multiple choice questions
* Keep a score on users
* Allow comments on individual cards

## Getting Started

- git clone git@github.com:RevatureBambooOrg/Project-Bamboo.git
- git clone git@github.com:RevatureBambooOrg/Project-Bamboo-Backend.git

On angular side:
In root folder
- npm install 
- change your database url in the Enviroment files
- ng serve -o to run on localhost OR:
- ng build --prod
- Copy the files in the dist folder and put it in your S3 bucket
- Done

On Spring side:
In application.properties 
- change spring.datasource.url to your database, should look similar to: jdbc:postgresql://"YOUR AWS NAME HERE".rds.amazonaws.com:5432/postgres
- change spring.datasource.username to your username
- change spring.datasource.password to your password
- Run as Spring boot app to run on localhost OR:
- In your AWS EC2 
- git clone from your copy
- run "mvn clean package"
- sudo service docker start
- sudo docker build -t myapp:auto .
- sudo docker run -d -p 5000:5000 myapp:auto


## Usage

- Register for an account, and then login with that account.
- Search for a concept to study (like Java or angular)
- Click the flip card to see the back, or click left or right to go to the next cards
- Click Create a flashcard and fill out the forms to add a card


## Contributors

- Antonio Ragusa
- Caleb Kirschbaum
- David Cordle
