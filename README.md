# IngenicoTest
web service for Ingenico


I have decided to implement my REST webservice via Tomcat 7.0
The code is written in Java.
The data will be saved in a file:
the file contains map with account number as a key and Account object as a value.
There are 3 main functionalities in the webservice, all of them pass parameters via the URL:
1.	http://localhost:8080/IngenicoWebService/rest/main/createaccount?accountNumber=3333&balance=10000
this will create account number 3333 with balance 10000
2.	http://localhost:8080/IngenicoWebService/rest/main/moneytransfer/?source=3333&destination=5678&amount=5000
this will transfer money from source account number 3333 to
destination account number 5678 with amount 5000
3.	http://localhost:8080/IngenicoWebService/rest/main/getaccounts
this will print all available accounts with their updated balance


Account number is a String, and balance or amount is type Double.
