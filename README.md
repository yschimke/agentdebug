# agentdebug
Java Agent to remote debug HTTP

```
$ ./gradlew installDist
$ ./build/install/agent/bin/agent  37817
```

Process 37817 will start printing out any headers or whatever you program it to do.

```
okurl https://httpbin.org/delay/20
RES: date: Mon, 15 Jun 2020 20:12:13 GMT
content-type: application/json
content-length: 329
server: gunicorn/19.9.0
access-control-allow-origin: *
access-control-allow-credentials: true

...
Program output
```
