Autoren: Timon Stronczek, Justus Pfaue, Bonnie Jo Mayer


## Frameworks:
- com.google.code.gson:gson:2.7 (JSON-Handling)
- com.squareup.okhttp3:okhttp:3.14.9 (Client-Anfragen)
- javafx (GUI)

## Bibliotheken:
- UUID
- util.*
- management.*
- ...

## GUI Funktion
- account creation
- account deletion
- login
- logout
- display of market inventory
- display of user inventory
- display of user money 
- buying a custom amount of a selected item
- selling a custom amount of a selected item
- add a new product with a specified quantitiy to the market
- *cheat* yourself some money for that sweet Wolf of Wall Street - Vibe

## Rest-API
- /user/register - register a user with a post request {"mail":"MAIL","password":"PASSWORD"}
- /user/login - login with a post request {"mail":"MAIL","password":"PASSWORD"}
- /user/logout - logout with a delete request {"token": "TOKEN"}
- /user/removeUser - remove a user with a delete request {"token": "TOKEN"}
- /user/addMoney - addMoney to the user with a post reqeuest {"token": "TOKEN","value":"VALUE"}
- /user/balance - get the current balance with a post reqeuest {"token": "TOKEN"}
- /user/depot - get the items in the depot with a post reqeuest {"token": "TOKEN"}
- /market/products - get the items in the market with a post reqeuest {"token": "TOKEN"}
- /market/addProductToMarket - add a product to the market with a post reqeuest {"token": "TOKEN","productname":"PRODUCTNAME","count":42}
- /market/sell - sell a product to the market with a post reqeuest {"token": "TOKEN","productname":"PRODUCTNAME","count":42}
- /market/buy - buy a product to the market with a post reqeuest {"token": "TOKEN","productname":"PRODUCTNAME","count":42}