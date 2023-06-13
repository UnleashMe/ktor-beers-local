# ktor-beers-local
This is a copycat REST API I've created with Ktor for personal use in my android app. The original API was created by https://github.com/AlexanderParmuzin and it has all the logic 
from there. But it's been too slow and I wanted to try and make it with Kotlin so here it is.

## Launch
To use this API simply copy this link git@github.com:UnleashMe/ktor-beers-local.git and in Intellij IDEA go to File -> New -> Project from version control -> Past the link into URL
Then wait for build process and you'll be able to launch it. The server will run on http://localhost:1611

# Documentation

Base url: http://localhost:1611

## GET

- /beverages : Gets all beverages found in database
- /beverages/{id} : Gets one beverage(by UID) found in database. Example: /beverages/16d347e4-f4fd-4132-a831-656631fbf3f9
- /snacks: Gets all snacks found in database
- /snacks/{id}: Gets one snack(by UID) found in database. Example: /snacks/16d347e4-f4fd-4132-a831-656631fbf3f9

## POST

- /beverages/add-beverage : Creates a new beverage

Body:

    - name: String (required): The name of the beverage
    - category: String (optional): Category of the beverage. Example: (Сидр/пиво/безалкогольное пиво)
    - type: String (required): The type of the beverage. Example: (Разливное/бутылочное)
    - price: Float (required): The price of the beverage. Example: (90) - в рублях
    - description: String (required): The description of the beverage
    - volume: Float (optional): Volume of the beverage. Example: (0,5) - в литрах
    - alcPercentage: Float (required): Alcohol percentage of the beverage. Example: (6,7) - в процентах
    - tags: String (optional): Tags to draw attention. Example: (Отечественное,мужской напиток).
    - isAvaliable: Boolean (optional): Is beverage available. Example: (true/false)
    - salePercentage: Float (optional): Product's discount. Example: (40) - в процентах
    - imagePath: String (optional): Image's URL. Image sent as form-data with a key beverageImage.
    
- snacks/add-snack: Creates a new snack

Body:

    - name: String (required): The name of the snacks
    - type: String (required): The type of the snacks. Example: (Рыба/мясо/чипсы)
    - price: Float (required): The price of the snacks. Example: (73,6) - в рублях за 100г
    - description: String (required): The description of the snacks
    - weight: Float (optional): Snack's weight. Defalt value - 100g.
    - tags: String (optional): Tags to draw attention. Example: (Соленое,жирное).
    - imagePath: String (optional): Image's URL. Image sent as form-data with a key beverageImage.
    
## POST

- /beverages/{id} :Updates beverage(by UID) with data passed into body. Example: /beverages/16d347e4-f4fd-4132-a831-656631fbf3f9

Body:

    - name: String (required): The name of the beverage
    - category: String (optional): Category of the beverage. Example: (Сидр/пиво/безалкогольное пиво)
    - type: String (required): The type of the beverage. Example: (Разливное/бутылочное)
    - price: Float (required): The price of the beverage. Example: (90) - в рублях
    - description: String (required): The description of the beverage
    - volume: Float (optional): Volume of the beverage. Example: (0,5) - в литрах
    - alcPercentage: Float (required): Alcohol percentage of the beverage. Example: (6,7) - в процентах
    - tags: String (optional): Tags to draw attention. Example: (Отечественное,мужской напиток).
    - isAvaliable: Boolean (optional): Is beverage available. Example: (true/false)
    - salePercentage: Float (optional): Product's discount. Example: (40) - в процентах
    - imagePath: String (optional): Image's URL. Image sent as form-data with a key beverageImage.
    
- /snacks/{id} : Updates snack(by UID) with data passed into body. Example: /snacks/16d347e4-f4fd-4132-a831-656631fbf3f9
    
Body:

    - name: String (required): The name of the snacks
    - type: String (required): The type of the snacks. Example: (Рыба/мясо/чипсы)
    - price: Float (required): The price of the snacks. Example: (73,6) - в рублях за 100г
    - description: String (required): The description of the snacks
    - weight: Float (optional): Snack's weight. Defalt value - 100g.
    - tags: String (optional): Tags to draw attention. Example: (Соленое,жирное).
    - imagePath: String (optional): Image's URL. Image sent as form-data with a key beverageImage.
    
## DELETE

   - /beverages/{id}: Deletes beverage(by UID) from database. Example: /beverages/16d347e4-f4fd-4132-a831-656631fbf3f9

   - /snacks/{id}: Deletes snack(by UID) from database. Example: /snacks/16d347e4-f4fd-4132-a831-656631fbf3f9
