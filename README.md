# To-Do List application
This application is used to manage tasks like read, update, create and delete them.
Here defined REST API to communicate with it.

## Environment setup
Firstly you need to start the docker and up services Redis and Postgres for this application,

```bash
  docker-compose up
```

When containers are started you can run the application.

## Important notes
Don't change annotation processor settings order in maven for mapstruct and lombok. 
Mapstruct needs to get from Lombok already generated getters and setters for classes.

## Cache structure
If you need you can change cache evict policy, default is 10 minutes. For additional info
check RedisConfig class for more details about application eviction policy.