# Server documentation

## Server structure

***

## Postman endpoints

All endpoints are available on Postman by clicking on the link below.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/9752892a1bb7a06b7994)

***

## Endpoint descriptions:

### Endpoints for Users:

**http://localhost/users/** +

- get all users in table users
```
@Path("/")
@GET
getAllUsers(@Context UriInfo uriInfo)
```
* get a specfik user by user id

```
@Path("/{userId}")
@GET
getUserById(@PathParam("userId") int userid, @Context UriInfo uriInfo)
```
* get a keyword from a specifik user by it's id
```
@Path("/{userId}/keyword")
@GET
getUserKeyword(@PathParam("userId") int userid, @Context UriInfo uriInfo)
```
* add a new user to the users table
```
@Path("/")
@POST
addUser(@RequestBody UserCreation userCreation, @Context UriInfo uriInfo)
```
<p background-color="red">Hallo</p>

```
@Path("/stock")
@POST
applyStock(@RequestBody UserStockCreation userStockCreation, @Context UriInfo uriInfo)
```

```
@Path("/keyword")
@POST
applyKeyword(@RequestBody UserKeywordCreation userKeywordCreation, @Context UriInfo uriInfo)
```

***

### Endpoints for Stocks:

**http://localhost/stocks/** +

```
@Path("/")
@GET
public Response getAllStocks(@Context UriInfo uriInfo)
```

```
@Path("/{stockId}")
@GET
public Response getStockById(@PathParam("stockId") int stockId, @Context UriInfo uriInfo)
```

```
@Path("/")
@POST
public Response addStock(@RequestBody StockCreation stockCreation, @Context UriInfo uriInfo)
```

***

### Endpoints for Keywords:

**http://localhost/keywords/** +

```
@Path("/")
@GET
public Response getAllKeywords(@Context UriInfo uriInfo)
```

```
@Path("/{keywordId}")
@GET
public Response getKeywordById(@PathParam("keywordId") int keywordId, @Context UriInfo uriInfo)
```

```
@Path("/")
@POST
public Response addKeyword(@RequestBody KeywordCreation keywordCreation, @Context UriInfo uriInfo)
```
***

