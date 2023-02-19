## **Introduction**

This is a Java Spring Boot REST API for a library application with two tables: books and category. The API allows users to perform CRUD (Create, Read, Update, Delete) operations on these tables.

The API utilizes PostgreSQL for data storage.

**Installation**

To use this API, you'll need to have Java and PostgreSQL installed on your machine.

Clone the repository to your local machine
Open the project in your preferred IDE
Update the application.properties file with your PostgreSQL database credentials
Build the project using mvn clean install
Run the application using mvn spring-boot:run

### Usage

Once the application is running, you can use an HTTP client like Postman to interact with the API.

The following endpoints are available:

**Books**

GET /books Retrieves all books in the database.

POST /books - Adds a new book to the database.

DELETE /books/ - Deletes a book by ID.


PUT /books/ - Updates a book by ID.

**Category**

GET /category - Retrieves all categories in the database.


POST /category - Adds a new category to the database.

PUT /category/ - Updates a category by ID.

DELETE /category/ - Deletes a category by ID.

Request and Response Formats
GET Requests
For GET requests, the API will return a JSON array of objects.

POST and PUT Requests
For POST and PUT requests, the API expects JSON data in the request body.

Here's an example of a POST request body for adding a new book:



`{
"title": "The Great Gatsby",
"author": "F. Scott Fitzgerald",
"category_id": "uufi-458-9595"
}`

DELETE Requests

For DELETE requests, the API will return a success message if the record was deleted successfully.

**Conclusion**

That's a simple documentation for your library app. Implementation of the favourite books aspect was not included in this and the reason was that it would involve creating a user table which is outside the scope of this implementation.