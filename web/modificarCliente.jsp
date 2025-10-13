<%-- 
    Document   : modificarCliente
    Created on : 12 oct. 2025, 20:01:55
    Author     : USUARIO
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Modificar Cliente</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty mensaje}">
        <div class="alert alert-success">${mensaje}</div>
    </c:if>

    <c:choose>
        <c:when test="${not empty cliente}">
            <form action="ClienteControlador" method="get" class="card p-4 shadow-sm">
                <input type="hidden" name="Op" value="Modificar">
                <input type="hidden" name="idCliente" value="${cliente.idCliente}">

                <div class="mb-3">
                    <label class="form-label">Apellidos</label>
                    <input type="text" name="apellidos" class="form-control" value="${cliente.apellidos}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Nombres</label>
                    <input type="text" name="nombres" class="form-control" value="${cliente.nombres}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Dirección</label>
                    <input type="text" name="direccion" class="form-control" value="${cliente.direccion}">
                </div>

                <div class="mb-3">
                    <label class="form-label">DNI</label>
                    <input type="text" name="DNI" class="form-control" value="${cliente.DNI}" maxlength="8">
                </div>

                <div class="mb-3">
                    <label class="form-label">Teléfono</label>
                    <input type="text" name="telefono" class="form-control" value="${cliente.telefono}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Móvil</label>
                    <input type="text" name="movil" class="form-control" value="${cliente.movil}">
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Modificar</button>
                    <a href="ClienteControlador?Op=Listar" class="btn btn-secondary">Volver</a>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning text-center">No se encontró ningún cliente con ese ID.</div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>

