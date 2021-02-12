# 88tech-exam-2

Please implement a program using Java's polymorphic features.
- Used an entity called PersistableEntity on domain objects as superclass.

###Mini Bank API

Users created: (Username,Password,Role)

(teller,password,TELLER)

(user1,password,ACCOUNT)

(user2,password,ACCOUNT)

TELLER API

GET /teller/account - Retrieves all account created.

GET /teller/account/{id} - Retrieves details of a single account.

POST /teller/account/ - Creates a new account.
```
 {
       "userName":"user3",
       "accountName":"USER 3",
       "role":"ACCOUNT"
}
```


