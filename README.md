# DeribitConnect

How to run the project:
1. install sbt
2. install docker
3. sbt docker:publishLocal will create local image for the service
4. docker-compose up will put under one container the deribit-connect service + PostgreSQL image used to store data of balance for the user

Additional way to do it: sbt run

In order to connect to deribit is required a API key and API secret.
Example for all 4 case can be found under https://github.com/pajarcupetre/DeribitConnect/blob/main/resouces/curls.sh
If we are passing additional paramer on getBalance useMain with true, will only report the balance from primary account. Default we are doing an aggregation of all.

[![@pajarcupetre's Holopin board](https://holopin.me/pajarcupetre)](https://holopin.io/@pajarcupetre)
