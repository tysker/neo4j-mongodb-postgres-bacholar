[Home](README.md)
# Postgres documentation

### Run postgres with help of Docker

We choose a postgres docker image, for easy deployment. Inside the docker-compose file, under volumes, we tell docker to copy variuos sql scripts into the docker-container. Docker runs the scripts in an alphabetically order, and therefore we have controll over which script is run first and whih one last.

To run the docker-compose file, that can be done in two ways. Either in Intellij or otherwise in an terminal.

 open a terminal on docker-compose and run:

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

### Database structure

<img 
src="./images/eer_diagram.png" 
alt="eer diagram"
style="height: 70%; width:70%;"
/>

### Views

### Stored procedures and functions

### Triggers

### Roles
