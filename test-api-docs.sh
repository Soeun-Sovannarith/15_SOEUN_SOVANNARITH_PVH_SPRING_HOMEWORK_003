#!/bin/bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.arguments="--server.port=8089" &
PID=$!
sleep 15
curl -s http://localhost:8089/v3/api-docs > api-docs.json
kill $PID
