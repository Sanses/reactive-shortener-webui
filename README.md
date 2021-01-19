##00. REFFENCE
 - https://wiki.iisanse.com/display/PS/03-05.+Spring+Boot+App+with+Application+Insights

##01. Pre Condition
- Create Azure Application Insights

##02. Setting Agent
- ApplicationInsights-agent/AI-Agent.xml

##03. How to Docker Build and Run on LocalPC
- docker build -t shorturl-app:1 .

- docker run -d -p 6379:6379 --name redis redis redis-server --appendonly yes  --requirepass "redis"

- docker run -d -p 80:8080 --link redis \
			-e JAVA_OPTS='-server -Xmx1g -Xms1g -Dspring.profiles.active=local' \
			-e REDIS_HOST=redis \
			-e REDIS_PORT=6379 \
			-e REDIS_SSL_ENABLE=false \
			-e REDIS_PASSWORD=redis \
			-e APPLICATIONINSIGHTS_CONNECTION_STRING="InstrumentationKey=" \
			--name shorturl-app sooabia/shorturl-app:1


 
			  
