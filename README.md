<h1>Meeting Calendar API</h1>
<br/>
<p>This is the backend part, Restfull API build with Java Spring (maven) as final project in Lexicon Växjö for fullstack developer focusing Java and React.</p>
<p>The project wraps upp larger parts of content covered during the course and time I put into this project was around 3-4 weeks. Roughly 2.2 weeks for frontend, 1.2 weeks for backend and 1 day to move project to Azure, however I had plenty of help from previous workshops and looking back over past lecture material.</p>
<br/>
<p>Main idea for this API is to provide and manage meetings to a front end application, additionally I added contact and login feature and moved this to the cloud as extra.<p/>
<br/>
<p>Key dependencies for the basics are Java Spring Boot, devtools, web, jpa, jdbc, mysql and lombok in this project</p>
<p>The login feature is done with Spring Security which was not part of this course however I wanted a way to login so I tried this and got it working however on the frontend side I faced issues regarding how to register "a login" so in order to make this work I came upp with an idea to simple have the application for one user "me" and by doing so I can create a user to db whereafter I can login by getting a authorization and in front end work from that. If I had more time/knowledge I would like to keep working on this in order to make it proper so I could have several users with own tables for meetings but since time is a limit and this is out of scope I choose to let it be this way.<p/>
<p>The mail feature is done with angus-mail and here Im passing data for my mailserver, and sending a mail composed from the front end form. My credentials for this are hidden.<p/>
<p>This Api was connected to MySQL at start but later I added new properties file in order to move it to Azure, this file is in Gitignore for obvious reasons. If you want to run this locally change spring.profiles.active=azure to spring.profiles.active=local in application.properties and fill in username and password for MySQL in local.properties.</p>
<br/>
<p>Cors was a recurring issue while building this, first of allowing Cors in HomeController, after adding Spring Security there was a new layer to be solved in the configuration and after deploying this to Azure there was another step to accept the front end part in settings -> API -> CORS.</p>
<p>All in all I had plenty of fun building this and have it running both local and deployed. To proceed I really would like to get the login feature work as intended with Spring Security, I guess I will take a step back and build a project or two with Spring security connected with a React application.</p>
<br/>
<br/>
<p>Happy coding!</p>
<p>Jari</p>
