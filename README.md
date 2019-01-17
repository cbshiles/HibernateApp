read-me

Requirements:
Mysql (8?)
Java (7-8?)

How to use:
Install requirements (see above)
Clone this repository.
Change configurations in resources directory. 
	Especially database credentials.
	Also make sure to run on an open port.
Run sql file.
Run tests (recommended for a new environment, otherwise shouldn't be needed)
Make your app

Eclipse way:
File > Open Projects from File System > select directory cloned into
Install Spring Tools 4 plugin (might have to reinstall eclipse if its not listed in the marketplace)
Right clock on project > Maven > Update Project

Command line:
mvn clean install -U
mvn spring-boot:run

Authorization:
https://support.google.com/cloud/answer/6158849?hl=en#authorized-domains
Authorize domain w/ google 
Register app w/ google

Todos:
Set up way to switch environments

