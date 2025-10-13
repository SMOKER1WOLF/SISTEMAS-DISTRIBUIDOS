<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import ="java.util.List"%>
<%@page import ="Entidades.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List <Cliente> Lista = (List<Cliente>) request.getAttribute("Lista");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de clientes</title>
        <link rel="stylesheet" type="text/css" href="css/styles_lista.css">
    </head>
    <body>
        <h1>Lista de clientes</h1>
        <table>
            <tr>
                <th>Id_Cliente</th>
                <th>Apellidos</th>
                <th>Nombres</th>
                <th>DNI</th>
                <th colspan="3">Acciones</th>
            </tr>
            <c:forEach items="${Lista}" var="campo">
                <tr>
                    <td>${campo.id}</td>
                    <td>${campo.apellidos}</td>
                    <td>${campo.nombres}</td>
                    <td>${campo.DNI}</td>
                    <td><a href="UsuarioControlador?Op=Consultar&Id=${campo.id}">Consultar</a></td>
                    <td><a href="UsuarioControlador?Op=Modificar&Id=${campo.id}">Modificar</a></td>
                    <td><a href="UsuarioControlador?Op=Eliminar&Id=${campo.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
