# Commerce application

## Authors

- [@tulaleandro](https://github.com/tulaleandro)

## Installation

To run the project use the profile dev. This one will allow spring boot to execute the schema.sql and data.sql file. In 
those files we have pre define data to test the API.
The project will be able to handle generic exception (check controller advice class)

## Usage/Examples

```curl
curl --location 'http://localhost:8080/prices?brand=ZARA&inputDate=2020-06-15T11%3A00%3A00.000Z&productId=3455'

Response for this specific scenario 

[
    {
        "productId": 3455,
        "brandId": 1,
        "startDate": "2020-06-18T08:00:00-03:00",
        "endDate": "2020-06-18T08:00:00-03:00",
        "priority": 1,
        "price": 34.5
    }
]

```