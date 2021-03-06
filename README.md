
# Spring Boot Sample with Token based authentication    

  #### Store management module   (Requires Basic auth)
    
>##### Headers
>   Accept:application/json   
>   Content-Type:application/json   
>   Authorization:Basic YWRtaW46cGFzc3dvcmQ= 

| method   | request                              | description                  |  
|--------  |----------------------------------    |--------------------------    |  
| POST     | http://localhost:8080/store          | Creates new store            |  
| GET      | http://localhost:8080/store/{id}     | Fetch single store by id     |  
| GET      | http://localhost:8080/store     | Fetch all stores             |  
  


  #### Product management module  (Requires StoreId and StoreKey as headers)  
  
>##### Headers  
>   Accept:application/json  
>   Content-Type:application/json  
>   X-Auth-StoreId:5ebfddd1-553c-4ec1-8dc5-61886bfd8374  
>   X-Auth-StoreKey:300b20a8-7508-4c6b-a9e2-d466be4890f7

  
  
| method   | request                              | description                  |  
|--------  |----------------------------------    |--------------------------    |  
| POST     | http://localhost:8080/product          | Creates new product            |  
| GET      | http://localhost:8080/product/{id}     | Fetch single product by id     |  
| GET      | http://localhost:8080/product     | Fetch all products             |
| PUT      | http://localhost:8080/product     | Update product             |



  #### ApiKey refresh  (Requires Basic auth)
  
>##### Headers  
>   Accept:application/json  
>   Content-Type:application/json  
>   Authorization:Basic YWRtaW46cGFzc3dvcmQ= 

  
  
| method   | request                              | description                  |  
|--------  |----------------------------------    |--------------------------    |   
| GET      | http://localhost:8080/store/{storeId}/refresh     | Refresh existing ApiKey     | 


## API Documentation by swagger
http://localhost:8080/swagger-ui.html