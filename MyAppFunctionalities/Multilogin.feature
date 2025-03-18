Feature: Login functionality
   This is the login functionality to be engineered using DDT
   
Scenario Outline: Login validation for different username and password

Given User on login pages 												

When User types "<username>" and "<password>"		

And User clicks the Login button								

Then User lands on some page	

Examples: 

|						username				|									password					|
|						user1						|									pass1							|
|						user2						|									pass2							|
|						practice				|							SuperSecretPassword!	|