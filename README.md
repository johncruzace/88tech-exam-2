# 88tech-exam-2

### Please implement a program using Java's polymorphic features.
- Used an entity called PersistableEntity on domain objects as superclass.

### Mini Bank API

Users created: (Username,Password,Role)

(**teller**,password,TELLER)

(**user1**,password,ACCOUNT)

(**user2**,password,ACCOUNT)

## TELLER API - /teller

**GET** /user - *Retrieves all users created.*

**GET** /user/**{id}** - *Retrieves details of a single user. id is a username.*

**POST** /user/ - *Creates a new user.*
``` 
 {
       "userName":"user3",
       "accountName":"USER 3",
       "role":"ACCOUNT"
}
```
**DELETE** /user/ - *Deletes an existing user
``` 
 {
       "userName":"user3"
}
```

**POST** /user/**{id}**/bank-account - *Opens a new bank account for the user. id is a username. amount is the opening balance of the account*
``` 
 {
       "amount":30000
}
```

**DELETE** /user/**{id}**/bank-account - *Opens a new bank account for the user. id is a username.*


## TRANSACTION API - /transaction
**GET** /**{id}**/history - *Retrieves the transaction history of the account. id is a username.*

**POST** /**{id}**/deposit - *Creates a Deposit Bank Transaction. id is a username.*
```
 {
     "amount":3000
}
```

**POST** /**{id}**/withdraw - *Creates a Withdraw Bank Transaction. id is a username.*
```
 {
     "amount":3000
}
```

**POST** /**{id}**/transfer - *Creates a Transfer Bank Transaction between two accounts. id is a username.*
```
 {
     "usernameToTransfer":"user1",
     "amount":12000
}
```

