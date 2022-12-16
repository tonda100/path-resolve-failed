# BUG [Wrong path matching when PathParam is used]

## Describe the bug

When I have two endpoints which both can match the requested path. One is with PathParam and second is exact match but for some reason the wrong one is used.

Requested url http://localhost:8080/books/image

Endpoints:
-  `/books/{id}`
-  `/books/image`

If the request is created by curl it works properly when it is by browser it does not work.
I suppose the reason is `@Produces(MediaType.APPLICATION_OCTET_STREAM)` annotation on second endpoint because when I remove annotation and add `Content-Type` header programatically it works correctly.


## Expected behavior

Request `http://localhost:8080/books/image` should invoke `/books/image` this endpoint.
I expect correct match even when Produces annotation is used.


## Actual behavior

Request `http://localhost:8080/books/image` invokes `/books/{id}` this endpoint.


## How to Reproduce?

1. Clone repository https://github.com/tonda100/path-resolve-failed
2. Run project
3. Invoke endpoint from curl `curl http://localhost:8080/books/image` and you get file
4. Invoke endpoint from browser http://localhost:8080/books/image and you got 404 because wrong endpoint is invoked.
