A) Steps to run the project in default profile
- **Step 1**: Clone the repository shared(using the command below) or download the zipped version
**git clone https://github.com/amolksagar/transferregistration-service.git**
- Step 2:
- cd to the transferregistration-service folder
- Execute the command below
- mvn clean install
- Step 3:
- From the same location as defined in Step 2, execute the below command
- mvn spring-boot:run
- Step 4:
- Please open the postman collection.The services should be hosted at http://localhost:8181/transferregistrationservice/. For example the endpoint for Create Person Url is POST http://localhost:8181/transferregistrationservice/transfer-registration/v1/person
- Step 5:
- Open API has been used to define the specification and swagger tools have been for implementing the Open API specifications. There are multiple benefits of using Open API which I have detailed below in a seperate section
- Swagger URL : http://localhost:8181/transferregistrationservice/docs/swagger-ui.html
- The yaml file can be found at https://github.com/amolksagar/transferregistration-service/tree/master/src/main/resources/oas or you can find the same in the zip file of the code I have shared
- Step 6:
- The postman collection to test the multiple use cases has been provided too.
- Please run the postman collection named "Transfer Registration"


B) Steps to run the project in QA profile. Please note: I have defined another profile named "qa" which brings up the application at https and at 8443 port. I have used Self signed certificate for this.Detailed configuration can be found in the application.yml file.Also, I have secured the qa profile endpoint with Basic Auth. In production grade applications, we ideally use JWT tokens or OAUTH as authentication mechanism but for the purpose of this test,I have limited it to Basic Auth
Ideally the .p12 file can be stored in a secure storage like AWS Secret manager but for the purpose of this application I have kept it in the resources/keystore/qa folder. There are other mechanisms too like Istio service mesh which can deploy your application endpoints as https.Please note, the secure port and basic auth is configured only in the qa profile
- Step 1: Clone the repository shared(using the command below) or download the zipped version
- git clone https://github.com/amolksagar/transferregistration-service.git
- Step 2:
- cd to the transferregistration-service folder
- Execute the command below
- mvn clean install
- Step 3:
- From the same location as defined in Step 2, execute the below command
- mvn spring-boot:run -Dspring-boot.run.profiles=qa
- Step 4:
- Please open the postman collection.The services should be hosted at https://localhost:8443/transferregistrationservice/. For example the endpoint for Create Person Url is POST https://localhost:8443/transferregistrationservice/transfer-registration/v1/person
- Step 5:
-   - Swagger URL : https://localhost:8443/transferregistrationservice/docs/swagger-ui-qa.html
- The yaml file can be found at https://github.com/amolksagar/transferregistration-service/tree/master/src/main/resources/oas or you can find the same in the zip file of the code I have shared
- Step 6:
- The postman collection to test the multiple use cases has been provided too.
- The basic Auth details are adminUser/adminPassword
- Please run the postman collection named "Transfer Registration QA Profile"

Important Points:
- CustomExceptionHandler has been implemented which will customise the messages sent back in a particular format
- Basic Auth has been implemented
- Profile based logging has been implemented

Assumptions used while developing the services:
- Since the requirements specified that a DB is not required, I have used in memory java collections
- A Person is uniquely identified by his emailAddress
- A Vehicle is uniquely identified by its Registration Number
- Once a Vehicle has been linked to a Person, it first has to be unlinked before getting linked to another one
- A person does not need a licence to be linked to a Vehicle
- Creation of Person,Vehicle,Link and Unlink are seperate processes happening at different times
- While unlinking, the requesting persons email Id should match the email Id registered against the Vehicle during the Linking process

Testing Done:
- Units tests
- Manaual testing using Postman fo this test.In production grade applications we can use frameworks like Karate to write feature wise test cases and run them via Kenkins pipeline