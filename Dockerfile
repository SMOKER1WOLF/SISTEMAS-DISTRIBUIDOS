FROM tomcat:9.0-jdk17-temurin

# Limpiar ROOT original
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiar todo lo generado por Ant dentro de ROOT
COPY build/web/ /usr/local/tomcat/webapps/ROOT/

# Copiar clases compiladas
COPY build/web/WEB-INF/classes/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Copiar librerías (JARs)
COPY build/web/WEB-INF/lib/ /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/
