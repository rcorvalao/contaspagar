#!/bin/bash


sudo docker run -itd --name rmcdb -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d rmccorp/rmcdb




# sudo docker exec -it 4bdbdb1a2ae8934a5d222f030ae3b1c6eb3c51c1655685380f83fd557c9553a7 bash
