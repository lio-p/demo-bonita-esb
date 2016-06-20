# demo-bonita-esb

This repository contains the materials to build a demo to show the integration of Bonita BPM with an ESB.

## Required components

  - Active MQ
  - Apache Camel

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

 - Go to camel-web and run the command to build the web application:
     ```sh
    $ mvn clean package
    ```
 - Deploy the file camel-web.war in an empty Tomcat
 - Startup Tomcat
 - To check camel-web is up and running, open a browser and go to http://localhost:8080/camel-web


## Testing

In that version, the Camel app listen to the queue "bonitaQueue". To test the integration between ActiveMQ and Camel, we will send a message in the bonitaQueue and check that Camel is consuming the message from the queue.
-  Log in the Active MQ Administration console: http://localhost:8161/admin
- Go to Queues, locate the queue "bonitaQueue" and click on "send"
- Leave the default options and click on send
- Go to your tomcat folder and open the log file catalina.out. You should see debug messages that indicate the message has been consumed. In addition of that, you should see in the ActiveMQ Administration console that the message has been consumed. 
