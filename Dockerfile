FROM tomcat:9.0-jdk17-temurin

# Eliminar la aplicación ROOT de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiar TODO el contenido que NetBeans genera en build/web/
COPY build/web/ /usr/local/tomcat/webapps/ROOT/