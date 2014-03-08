This project shows you how to setup a simple odata4j inmemory producer.
Main focus lies on create, read, update and delete (CRUD) of an entity. 
Due to complexity no database connection is implemented all changes are 
stored temporarily in SimpleInMemoryProducer.

### Example
Our OData Service manages products. A product contains a key and a description.

Product:

- Key: IA40001
- Description: USB2 Adapter 

### Details

OData Service "simple.svc":

- EntitySet "Products"
- Entity "Product"
- Property "Key"
- Property "Description"

http://<url>:<port>/SimpleOData4JProducer/simple.svc/Products?$format=json

### Option 1
Start web server in eclipse

### Option 2
Start web server stand alone and deploy WebContent folder as own webapp