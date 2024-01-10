# Weekly Reflection

Document how your week in Ent Java went. You may use whatever format suits you, as long as you share how much time you worked and provide a general overview of how things went: achievements and struggles.

### Week 4 work

10/12/2016 - 14 hours

Completed all Checkpoint 1 items for the project. Built the basic functionality for the project including setting up test DB and hibernate.

### Week 5 work

- 16 hours

Added a one-to-many relationship in my project. Added testing for the Daos' methods and created a generic dao as well. Added Javadoc comments to all methods. Struggled on getting Hibernate annotations to work correctly. After hours of difficulties I finally figured it out with the help of the weekly videos.

### Week 6 work

-20 hours

Used Elastic Beanstalk and RDS to host my project on AWS. Struggled a lot with getting my JDBC connection to work when hosting the application through AWS services. Learned about RDS and using it to communicate with databases.

### Week 7 work

- 13 hours

Created and configured user pool for my indie project. Deployed my indie project as a Cognito app. I struggled with generating the needed certificate quite a bit. Seeing as I am working off of a Windows machine, I was not able to use the 'openssl version' because it wasn't installed on my machine. This led me down quite a rabbit hole. I figured I had the option of either using a package manager to install OpenSSL, or the alternate which involved connecting to an EC2 instance in a running environment by using the EB CLI's eb ssh command. This meant I needed to install the EB CLI. I strugged with this a bit. Installing the EB CLI meant that I also needed to install Python as the installation requires using some python commands. I then needed to install virtualenv as virtualenv is a requirement for installing the EB CLI also. This was in addition to having to add virtualenv and pip to the Path variable. So once this was complete I was then able to install the EB CLI. My new issue was that I still couldn't seem to get the eb command to work. Leading me to believe that something might've been wrong with my installation. EB CLI was installed, as was Python and virtualenv. Anyway it was at this point after hours of struggling that I finally realized the certificate and its associated files were posted for us Windows users! Awesome!!

### Week 8 work

-10 hours

In order to get the relevant data for my indie project I needed to call 2 seperate APIs. The first API allowed me to get the GPS coordinates of a user's zip code. This information (coordinates) were then fed to the 2nd API which was then able to provide me with the weather data that I needed. This was the easy part. I then went on to struggle with how to correctly create the corresponding Java classes from the API's response. This was mostly due to the fact that the JSON object that was being returned had other JSON objects that were nested inside of it. At first I tried feeding RoboPojo the nested objects on their own although this seemed to cause more issues than it solved. I eventually opted for feeding RoboPojo the entire JSON response after figuring this is probably the best approach for what the assignment is asking. Fingers crossed!