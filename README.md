# demo-bonita-esb

This repository contains the materials to build a demo to show the integration of Bonita BPM with an ESB.

## Required components

  - Active MQ
  - Apache Camel
  - Alfresco
  - Bonita Studio 7.3.2

## Setup

#### Active MQ

[Here](http://activemq.apache.org/getting-started.html) is a useful link on how install Active MQ.

  - [Download](http://www.apache.org/dyn/closer.cgi?filename=/activemq/5.13.3/apache-activemq-5.13.3-bin.tar.gz&action=download) Active MQ
  - Unzip the archive in your favorite folder
  - From a command shell, change to the installation directory and run ActiveMQ as a daemon process:
    ```sh
    $ cd [activemq_install_dir]/bin
    $ ./activemq start
    ```
  - To check ActiveMQ is up and running, open a browser and go to http://localhost:8161/admin (default credentials: admin/admin)
  - Create a queue called bonitaQueue
    - In the administration console, go to Queues
    - In the text field, enter bonitaQueue and click on "Create"

#### Apache Camel
 - Go to camel-bonita folder in this project and run the command to build the web application:
     ```sh
    $ mvn clean package
    ```

 - Go to camel-web folder in this project and run the command to build the web application:
     ```sh
    $ mvn clean package
    ```
 - Deploy the file camel-web.war in an empty Tomcat
 - Startup Tomcat
 - To check camel-web is up and running, open a browser and go to http://localhost:8080/camel-web

#### Alfresco

There are different ways to get a instance of Alfresco server. In my case, I chose to use a Docker image that I run locally. You could also install Alfresco on your machine or use a cloud instance. In this chapter, I will describe the solution using Docker. 

  - Get Docker image: docker pull gui81/alfresco
  - Create a new container: docker run --name='alfresco-esb' -it --rm -p 32768:8080 -p 32771:445 -p 32770:7070 -p 32769:8009 -p 32775:21 -p 32774:137 -p 32773:138 -p 32772:139 gui81/alfresco
  - Access the Alfresco web client: http://192.168.99.100:32768/share
  - Login using the default credential: admin/admin
  - Create a new folder called "bonita" (Go to My Files, create new) 
  - Under "bonita" folder, create another folder called "claims-storage"
  
#### Bonita Studio

The version of the process (process/Claims-management-esb-1.0.bos) provided is 7.3.2 Community Edition. You can download the Bonita Studio [here](http://www.bonitasoft.com/downloads-v2).

  - Start the Studio
  - Import the bos file Claims-management-esb-1.0.bos
  - Click on Run to deploy the process in the local portal (http://localhost:8080/bonita)
  
#### Camel web app

The route camel are hosted by a web application, called camel-web in our case. 

  - To build the web application camel-web, go to camel-web folder and run: mvn clean install
  - Deploy the war file generated, located in the target folder, in a local Tomcat instance. I will suggest to just download a fresh Tomcat server for the test. You can download it from [here](https://tomcat.apache.org/download-80.cgi)
  
Note: As Bonita Portal, already run on the port 8080, please make sure that this Tomcat instance ran on a different port. By example, the port 8191. 

You need to configure two folders for the camel route to work:
  - A "bucket" folder where a Camel route will pick up files and push them to the JMS queue. Will call it BUCKET_FOLDER
  - A "received" folder where a Camel route will push files at the end of the demo. Will call it RECEIVED_FOLDER

Please create these folders on your local machine and then modify the file camel-web/src/main/webapp/WEB_INF/camel-config.xml to provide the real path for BUCKET_FOLDER and RECEIVED_FOLDER. Build and deploy camel-web after the changes. 
  
## Run the demo

  - Push the file test-files/Claims-Letter.docx to your BUCKET_FOLDER. The file should disappear immediately or after a few seconds. 
  - Go to Bonita Portal, log in as walter.bates. You should have new task ready to perform. 
  - Perform the task Verify Claims compliance. Click on Accepted.
  - A new task will be created: Process Claims. Attach the document test-files/Return_letter.docx as response letter and process it. 
  - Logout and log in as helen.kelly. 
  - A task Review claim should be present. Open it and click on Accept.  
  - Go to Alfresco, you should see a new folder under /bonita/claims-storage. This folder contains the response letter.
  - Go to your local folder RECEIVED_FOLDER, this folder should contains the response letter as well.
   
