# Server documentation

## Server structure

***

## Postman endpoints

All endpoints are available on Postman by clicking on the link below.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/9752892a1bb7a06b7994)

***

## Endpoint descriptions:

### Endpoints for Users:

```
http://localhost/users/ +
```

<p style="background-color:blue"> get all users in table users</p>

```
@Path("/")
@GET
getAllUsers(@Context UriInfo uriInfo)
```
<p style="background-color:blue">get a specfik user by user id</p>

```
@Path("/{userId}")
@GET
getUserById(@PathParam("userId") int userid, @Context UriInfo uriInfo)
```
<p style="background-color:blue">get all keywords from a specifik user with help of the users id</p>

```
@Path("/{userId}/keyword")
@GET
getUserKeyword(@PathParam("userId") int userid, @Context UriInfo uriInfo)
```
<p style="background-color:blue">add a new user to the users table</p>

```
@Path("/")
@POST
addUser(@RequestBody UserCreation userCreation, @Context UriInfo uriInfo)
```
<p style="background-color:blue">add stock to the stock table if stock not exitst and add stock and user id to users_stock table.</p>

```
@Path("/stock")
@POST
applyStock(@RequestBody UserStockCreation userStockCreation, @Context UriInfo uriInfo)
```
<p style="background-color:blue">add a keyword to the keyword table if keyword not exitst, and add keyword and user id to users_keyword table.</p>

```
@Path("/keyword")
@POST
applyKeyword(@RequestBody UserKeywordCreation userKeywordCreation, @Context UriInfo uriInfo)
```

***

### Endpoints for Stocks:

```
http://localhost/stocks/ +
```
<p style="background-color:blue">get all stocks from the stocks table</p>

```
@Path("/")
@GET
public Response getAllStocks(@Context UriInfo uriInfo)
```
<p style="background-color:blue">get a specifik stock from the stocks table by stock id</p>

```
@Path("/{stockId}")
@GET
public Response getStockById(@PathParam("stockId") int stockId, @Context UriInfo uriInfo)
```
<p style="background-color:blue">add a stock to the stocks table</p>

```
@Path("/")
@POST
public Response addStock(@RequestBody StockCreation stockCreation, @Context UriInfo uriInfo)
```

***

### Endpoints for Keywords:

```
http://localhost/keywords/ +
```
<p style="background-color:blue">get all keywords from the keyword table</p>

```
@Path("/")
@GET
public Response getAllKeywords(@Context UriInfo uriInfo)
```
<p style="background-color:blue">get a specfik keyword from the keywords table by keword id.</p>

```
@Path("/{keywordId}")
@GET
public Response getKeywordById(@PathParam("keywordId") int keywordId, @Context UriInfo uriInfo)
```
<p style="background-color:blue">add a keyword to the keywords table.</p>

```
@Path("/")
@POST
public Response addKeyword(@RequestBody KeywordCreation keywordCreation, @Context UriInfo uriInfo)
```
***
