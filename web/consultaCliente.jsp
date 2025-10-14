<%-- 
    Document   : consultaCliente
    Created on : 12 oct. 2025, 19:36:07
    Author     : USUARIO
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consulta de Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Consulta de Cliente</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <c:choose>
        <c:when test="${not empty cliente}">
            <table class="table table-bordered">
                <tr><th>ID Cliente</th><td>${cliente.idCliente}</td></tr>
                <tr><th>Apellidos</th><td>${cliente.apellidos}</td></tr>
                <tr><th>Nombres</th><td>${cliente.nombres}</td></tr>
                <tr><th>Dirección</th><td>${cliente.direccion}</td></tr>
                <tr><th>DNI</th><td>${cliente.DNI}</td></tr>
                <tr><th>Teléfono</th><td>${cliente.telefono}</td></tr>
                <tr><th>Móvil</th><td>${cliente.movil}</td></tr>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning text-center">
                No se encontró ningún cliente con ese ID.
            </div>
        </c:otherwise>
    </c:choose>

    <div class="mt-4 text-center">
        <!--ELIMINE EL MODIFICAR PORQUE EL PROFESOR NO LO SOLICITO-->
        <a href="ClienteControlador?Op=Listar" class="btn btn-secondary">Volver</a>
    </div>
</div>

</body>
</html>
