# store-management-tool
Store management tool contains the following operations:
POST /product -> used in order to insert a new product into store
GET /product -> used in order to retrieve a product; it is filtered by barcode, which is an unique field in DB; 
             -> returns error if not found or too many products found
            
Unit tests are implemented only for POST /product 