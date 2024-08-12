Biblieasy
===

##Getting Started

### Prerequisites
This project uses external library called (JCE) Java Cryptography Extension. I use this library to secure my properties file, so that I can use {cipher} to encrypt all the sensitive information.
If you use OpenJDK11 you can skip this step. JDK 9 and later offer the stronger cryptographic algorithms by default. 
Otherwise, you can download in the link below, or you can find it in the project file.

* [JCE](https://www.oracle.com/java/technologies/javase-jce-all-downloads.html)


#### Installing:  
1. You basically need to do is navigate to your JDK installation path 
2. place local_policy.jar and US_export_policy.jar under jre/lib/security.
3. Copy and paste C:\Program Files\jdkx.xx.\jre\lib\security direction to both Path
    User variables and System variables
   

### How does it work?
```aidl
POST http://localhost:8080/encrypt
```

Body: RAW, text

write down the password you want to encrypt.

### decrypte:

```aidl
POST http://localhost:8080/decrypt
```

Body: RAW, text

paste the encrypted password you got from encrypt result.

### Running the test

Once start the application, the data will be added automatically. The testing data is in the main application or Application.java 

## Important
Every user has roles, and depending on role, he can only read(get), delete, update(put), or write(post).
And every testing the authorization is required.

### How to authorize work in postman?

There are two ways to authorize yourself by using Headers or Authorization tab.
####Headers: 

KEY: `Authorization`

VALUE: `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb3JheWEiLCJyb2xlcyI6WyJBRE1JTiJdLCJpc3MiOiIvYXBpL2xvZ2luIiwiZXhwIjoxNjM3OTQ1Mjg0fQ.ZistRrFDLeDZWnfPwy-TAr6Q_a5k1juiDWVrQmaP1cE` 

#### Authorization: 
 
basic auth: username+password

`username: soraya,
password: password` 

NOTE: this user is an admin, and has all permissions.
### Testing on Postman

You can test each endpoint by using postman
### To get the token o login

```aidl
POST http://localhost:8080/api/login
```
Body: x-www-form-urlencoded, KEY, VALUE

`
username  soraya,
password  password
`


### To refresh Token
```aidl
POST http://localhost:8080/api/token/refresh
```

Headers: 
Authorization  Bearer+one-space+token

### Add a new user
```aidl
POST http://localhost:8080/api/user/save
```
Body: raw, JSON

`{
"name": "Konan konan",
"username": "konan",
"password": "password"
}`

NOTE: the password will be encrypted in mysql db.

###Add a new book
```aidl
POST http://localhost:8080/api/book/save
```
Body: raw, JSON

`{   
"title": " Clean Code – A Handbook of Agile Software Craftsmanship",
"author": "Robert Cecil Martin, a.k.a. Uncle Bob",
"publisher": "Pearson Education",
"edition": "1st Edition"  
}`

### Add a new role
```aidl
POST http://localhost:8080/api/role/save
```

Body: raw, JSON

`{  
"name": "SUPER ADMIN"
}
`

### Add row to user
```aidl
POST http://localhost:8080/api/role/addtouser
```

Params: KEY VALUE

`
userId 4
id     1
`

Add role 1 (admin) to user 4

### Add a loan(borrow)
```aidl
POST http://localhost:8080/api/borrowBook
```
Params: KEY VALUE

`
bookId     2,
userId     3,
loanDate   2022-01-01,
returnDate 2022-01-15
`

### Add a reservebook
```aidl
POST http://localhost:8080/api/reserveBook
```
Params: KEY VALUE

`
bookId    2,
userId    2,
reservationDate  2022-01-24
`

### Delete a user
```aidl
DELETE http://localhost:8080/api/delete/user/4
```

NOTE: 4 is the user id that we are going to delete.

### Delete a book
```aidl
DELETE http://localhost:8080/api/delete/book/4
```

NOTE: 4 is the book id that we are going to delete.

### Delete a loan
```aidl
DELETE http://localhost:8080/api/delete/loan
```

Params: Key Value
`
bookId 1,
userId 1
`

### Delete a book reservation
```aidl
DELETE http://localhost:8080/api/delete/reservation
```
Params: Key Value
`
bookId 2,
userId 2
`

### Delete a role
```aidl
DELETE http://localhost:8080/api/delete/role/4
```

NOTE: 4 is the book id that we are going to delete.

### Update user 
```aidl
PUT http://localhost:8080/api/update/user/3
```

NOTE: 3 is the user id that we are going to update.

Params: Key Value

HINT: you can choose to update between name, username, password

### Update book
```aidl
PUT http://localhost:8080/api/update/book/9
```
NOTE: 9 is the book id that we are going to update.

Available params: title, author, edition, publisher

### Update loan
```aidl
PUT http://localhost:8080/api/update/loan
```
Params: Key, Value

NOTE: bookId, UserId is mandatory

Available params: returnDate, loanDate

### Update reservation
```aidl
PUT http://localhost:8080/api/update/reservation
```

Params: Key, Value

NOTE: bookId, UserId is mandatory

Available params: reservationDate

### Update Role
```aidl
http://localhost:8080/api/update/role/1
```
Params: Key, Value

NOTE: 1 is the role id that we are going to update. Here you can update param "name".


## Filters using get method

### Get user by username
```aidl
http://localhost:8080/api/user
```
Params: Key, Value

Available params: username

### Get book by title, author, edition, publisher
```aidl
GET http://localhost:8080/api/search/book
```
Params: Key, Value

Available params: title, author, edition, publisher

### Get reservation using bookId and userId 
```aidl
GET http://localhost:8080/api/reservations
```
Params: Key, Value

Required params: bookId, userId 

### Get loan using bookId and userId
```aidl
GET http://localhost:8080/api/loans
```
Params: Key, Value

Required params: bookId, userId 

### Get all Roles
```aidl
GET http://localhost:8080/api/roles
```


### Get all users
```aidl
http://localhost:8080/api/users
```

## Pagination

### Book Pagination
```aidl
http://localhost:8080/api/book/pageable
```
Params: Key, Value

Required params: page, size

### User pagination
```aidl
http://localhost:8080/api/user/pageable
```

Params: Key, Value

Required params: page, size
