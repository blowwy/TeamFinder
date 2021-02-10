backend api for TeamFinder App
Allows to store information about fields, players and teams

1. Create new database and user;
2. In resources/application.properties file put in
    database url after 'spring.datasource.url='
    username after 'spring.datasource.username='
    user password after 'spring.datasource.password='
    
3. send POST query with json body to localhost:8080/signup 
    json consists of username, password and email
    
4. login using POST query with json body to localhost:8080/login
    json consists of username and password
    
   In Authorization header u will get jwt token concatinated with string 'Bearer'

5. For now use Bearer Token authorization type for any request to application api
