# Career Information and Recruitment Portal

**Disclaimer: This is a web application project developed to meet academic curriculum requirements (semester 6 - mini project, B.Tech Information Technology, University of Calicut).**

This web application demonstrates use of Spring boot, Spring Security and MongoDB to build an effective platform for colleges, students, alumni and recruiters to connect with each other. Colleges can use this platform efficiently to help their students and alumni find more campus hiring opportunities. Recruiters can use this platform for hiring students and alumni. All job applications are custom sorted by default so that candidates that best match a job according to the skills on their profile stay on top. A chat feature is available for recruiters to communicate job/hiring related info to students or alumni. Students and alumni can use the various personalisation features and build an attractive profile. They can also enjoy personalised job suggestions based on the skills mentioned in their profile. There is also a recommendation feature which allows colleges, recruiters and alumni to recommend students and (other) alumni they know of. There are general info pages available for a few engineering branches to guide students in choosing their career.

Four administrator accounts are configured by default for this application. All college and recruiter registrations on the platform has to be approved by the administrators before they can get full access to the platform. Similarly, the account of alumnus has to be approved by his/her college. Students cannot register themselves. Student accounts must be created by their colleges; hence, their accounts are approved by default.

### Websites
- Project website: https://jincy-p-janardhanan.github.io/Career-Information-and-Recruitment-Portal/
- Deployment website: https://career-info-n-recruitment.herokuapp.com

## Languages, Frameworks, Database and Other Technologies
- Java
- Spring Boot
- Spring Security
- MongoDB
- Thymeleaf
- Websockets (STOMP)
- HTML
- CSS
- Javascript
- JQuery

## Packages
An executable JAR file for the latest build of the application is available from the releases section of this repository. It can be downloaded and run on http://localhost:8080 .
To run the JAR file from command line, use:
```
java -jar Career-Information-and-Recruitment-Portal-0.0.1.jar
```
from the downloaded folder.

### Pre-requisites
To be able to run the application, you should have JRE (version 1.8 or higher) installed on your system.

### Known Issues

- None.

## Cloud Deployments

- App deployed on Heroku. Available at: https://career-info-n-recruitment.herokuapp.com/
### Known Issues for Heroku Deployment
- File upload fails - requires Amazon S3 (or other hosting services) to host uploaded images.

## Demo

 Feel free to view a complete demo of our application at: https://drive.google.com/file/d/1GHfJgFGIVuGlzfqIt6tNYS6HUuCRl6fJ/view?usp=sharing

## Additional Notes

- `application.properties` is not included in the source available here and is kept as a git-secret since it contains sensitive information like database and gmail passwords. To build the application from source, include a `application.properties` file in `src/main/resources` folder with the following content layout.

```
# MongoDB configuration
spring.data.mongodb.uri= your-mongodb-connection-uri

# Email configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= your-email@gmail.com
spring.mail.password= your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000
spring.mail.properties.mail.smtp.starttls.enable=true

# session configuration
server.servlet.session.timeout=20m
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true
server.error.whitelabel.enabled=false

# servlet multipart file request configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=25MB

```
- Also, to configure AWS S3 in your application, include the following in your environment variables

```
AWS_ACCESS_KEY_ID = your-aws-accessID
AWS_SECRET_ACCESS_KEY = your-aws-secret-key
AWS_REGION = your-aws-region
S3_BUCKET_NAME = your-bucket-name
```
For more info, see AWS [official documentation](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html).

- Since this is an academic project, comments have also been included for better understanding of a topic or area
- The WYSIWYG tool RocketCake was used to generate most of the HTML and CSS files.


## Contributors

- @jincy-p-janardhanan - Jincy P Janardhanan
- @alkadas99 -  Alka Bhagavaldas K
- @aleena124 - Aleena Sunny
- @AmiAshz - Ameena Shirin
