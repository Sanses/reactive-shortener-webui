##00. REFFENCE
 - https://wiki.iisanse.com/display/PS/03-05.+Spring+Boot+App+with+Application+Insights

##01. Pre Condition
- Create Azure Application Insights

##02. Edit connectionString
- ApplicationInsights-agent/applicationinsights.json
  
##03. Setting Agent
- ApplicationInsights-agent/AI-Agent.xml

##04. Setting JVM Environment variable 
- -javaagent:path/to/applicationinsights-agent-3.0.0.jar


- docker build -t shorturl-app:0.9 .

- docker run -d -p 8080:8080 -e JAVA_OPTS='-server -Xmx1g -Xms1g -Dspring.profiles.active=dev' \
			  -e REDIS_HOST="${REDIS_HOST}" \
			  -e REDIS_PORT=${REDIS_PORT} \
			  -e REDIS_SSL_ENABLE=${REDIS_SSL_ENABLE} \
			  -e REDIS_PASSWORD="${REDIS_PASSWORD}" \
			  -e APPLICATIONINSIGHTS_CONNECTION_STRING="${APPLICATIONINSIGHTS_CONNCTION_STRING}" \
			  shorturl-app:0.9
 
			  
