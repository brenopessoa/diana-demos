# couchbase-demo

A diana project with Java SE using Document API with MongoDB as driver implementation.

![Couchbase Project](https://github.com/JNOSQL/jnosql-site/blob/master/assets/img/logos/couchbase.png)


**Couchbase**: Couchbase Server, originally known as Membase, is an open-source, distributed multi-model NoSQL document-oriented database software package that is optimized for interactive applications.


To run this project a Cassandra instance is required, so you can use either a local instalation or using Docker.


## Manual instalation

Follow the instructions in: https://www.couchbase.com/get-started-developing-nosql


## Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/mongo
1. Run docker command
1. `docker run -d --name couchbase-instance -p 8091-8094:8091-8094 -p 11210:11210 couchbase`



## Run the code

With a Couchbase instance running go to the class **App** and have fun.
