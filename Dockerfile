# Imagen base con Tomcat 9 + JDK 17
FROM tomcat:9.0-jdk17-temurin

# Limpiar la aplicación ROOT por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiar archivos JSP y recursos
COPY web/ /usr/local/tomcat/webapps/ROOT/

# Copiar clases compiladas del proyecto Ant
COPY build/web/WEB-INF/classes/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Copiar librerías (si tienes .jar dentro de WEB-INF/lib)
COPY web/WEB-INF/lib/ /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

# Exponer puerto
EXPOSE 8080

# Iniciar Tomcat
CMD ["catalina.sh", "run"]
