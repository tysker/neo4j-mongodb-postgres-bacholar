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

### Installation Instructions

### Run postgres with help of Docker

We choose a postgres docker image, for easy deployment. Inside the docker-compose file, under volumes, we tell docker to copy variuos sql scripts into the docker-container. Docker runs the scripts in an alphabetically order, and therefore we have controll over which script is going to be executed first and which one last.

To run the docker-compose file, that can be done in two ways. Either in Intellij or otherwise in a bash terminal.

 open a terminal on docker-compose and write:

```
docker-compose up
```

**Remember to change the port number from 5432 to 5438!**

<img 
src="./images/docker_compose.png" 
alt="docker-compose file"
style="height: 70%; width:70%;"
/>

***

***

* [**Java Server documentation**](SERVER.md)
* [**Postgres documentation**](POSTGRES.md)
* [**MongoDB documentation**](MONGODB.md)
* [**Neo4j documentation**](NEO4J.md)

***

Data:
- Artikler
- User relation

Functions:
- graph queries
- cluster, replica (horizontal scaling)

**Postgres:**
- Docker?
Data: 
- User (username, password, search history)
- Aktie tabel
- Søgeord

Functions:
- grant users, trigger, stored procedures, view

**Server:**

