Capturing Aggregate Repository's Event using Mongo DB's Change Stream

When an instruction or command is invoked on a domain aggregate, an event can be dispatched to notify a state change. Event subscribers can interpret the event and carry out whatever business logic is needed. Mongo DB provides a change data capture feature called change stream that will dispatch an event after manipulating the documents.

```sh
curl --location 'http://localhost:8080/book' \
--header 'Content-Type: application/json' \
--data '{
    "author" : "JK Rowling",
    "title" : "Chamber of Secrets"
}'
```