# store-management-tool

Store management tool contains the following operations:

# POST /product 

-> used in order to insert a new product into store

-> unit tests are implemented for this operation

# GET /product

-> used in order to retrieve a product; it is filtered by barcode, which is an unique field in DB; 

-> returns error if not found or too many products found

# PUT /product/{barcode}

-> it is used to change price of a product based on its barcode

-> first identify if the product exists, then update the price



## Insomnia project with tests is exported and can be find in /test/resources
