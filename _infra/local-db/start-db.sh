#!/bin/bash

docker run -v `pwd`/data:/var/lib/postgresql/data:delegated -p 127.0.0.1:5432:5432 -e "POSTGRES_PASSWORD=password" postgres:11-alpine
