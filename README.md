# Database projekt:

**Dato: 02-juni-2021**


**Studerende:**

* Claus Kramath, cph-ck83@cphbusiness.dk
* Morten Feldt, cph-mf227@cphbusiness.dk
* Mads Wulff, cph-mn492@cphbusiness.dk
* Jörg Oertel, cph-jo130@cphbusiness.dk

*** 

<img 
src="./images/dsc_db.png" 
alt="EER Diagram"
height="40%" 
width="40%"
/>

***

## Installations instruktioner

### Kør Postgres, MangoDB og Neo4j med hjælp af Docker

For alle tre databaser, valgte vi at oprette en docker-compose-fil for nem implementering.
Vores docker-compose-fil kan køres enten i en bash-terminal eller i Intellij. 

1.  **Ind i Intellij:**

<img 
src="./images/rundocker.png" 
alt="docker Intellij file"
style="height: 70%; width:70%;"
/>

2. **Ind i bash terminalen:**


```
docker-compose up
```

**Remember to change the port number from 5432 to 5438!**

<img 
src="./images/docker_compose.png" 
alt="docker-compose file"
style="height: 70%; width:70%;"
/>


Inside the docker-compose file, under volumes, we tell docker to copy variuos sql scripts into the docker-container. Docker runs the scripts in an alphabetically order, and therefore we have controll over which script is going to be executed first and which one last.


***

## Dokumentation

* [**Java Server dokumentation**](SERVER.md)
* [**Postgres dokumentation**](POSTGRES.md)
* [**MongoDB dokumentation**](MONGODB.md)
* [**Neo4j dokumentation**](NEO4J.md)

***


