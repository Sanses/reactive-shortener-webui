## 01. How to Run IntelliJ on LocalPc
- A. https://github.com/Sanses/springboot-reactive-shorturl-webui.git
- B. git checkout Kubernetes
- C. docker run -d -p 6379:6379 --name redis redis redis-server --appendonly yes  --requirepass "redis"
- D. VMOptions: -server -Xmx1g -Xms1g -Dspring.profiles.active=local
- E. ENVParmeters: REDIS_HOST=localhost;REDIS_PORT=6379;REDIS_SSL_ENABLE=false;REDIS_PASSWORD=redis
- F. Execute Application 


## 02. How to Docker Build and Run on LocalPC
- A. docker build -t shorturl-app:1 .

- B. docker run -d -p 6379:6379 --name redis redis redis-server --appendonly yes  --requirepass "redis"

- C. docker run -d -p 80:8080 --link redis \
			-e JAVA_OPTS='-server -Xmx1g -Xms1g -Dspring.profiles.active=local' \
			-e REDIS_HOST=redis \
			-e REDIS_PORT=6379 \
			-e REDIS_SSL_ENABLE=false \
			-e REDIS_PASSWORD=redis \
			-e APPLICATIONINSIGHTS_CONNECTION_STRING="InstrumentationKey=" \
			--name shorturl-app shorturl-app:1
			
## 03. How to Run Kubernetes on LocalPC
- PreCondition : Kubernetes on DockerDesktop
- A. docker build -t {YOUR DOCKERHUB ACCOUNT}/shorturl-app:1 .
- B. docker push {YOUR DOCKERHUB ACCOUNT}/shorturl-app:1
- C. kubectl create secret docker-registry regcred --docker-server=https://index.docker.io/v1/ --docker-username={Your Dockerhub username} --docker-password={Your DockerHub PaToken} --docker-email={Your DockerHub Email}
- D. kubectl apply -f manifests/local/shorturl-app-redis-local.yml
- E. kubectl apply -f manifests/local/shorturl-app-config-local.yml
- F. kubectl apply -f manifests/local/shorturl-app-deployment-local.yml
- G. kubectl apply -f manifests/local/shorturl-app-service-local.yml

 
			  
