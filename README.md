# WordCounter Application

## Build application
mvn clean install

## Start up application
mvn spring-boot:run

## Testing

Sample curl request to count words :

curl --location 'http://localhost:8080/wordcounter?words=the%20house%20on%20the%20hill'

Sample Response: 

{
    "wordCountMap": {
        "the": 2,
        "hill": 1,
        "house": 1,
        "on": 1
    }
}