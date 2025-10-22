<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registrar nuevo cliente</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <h2 class="text-center mb-4">Registrar nuevo cliente</h2>
            <c:if test="${not empty mensaje}">
                <div class="alert alert-success mt-3 text-center">${mensaje}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3 text-center">${error}</div>
            </c:if>
            <form action="ClienteControlador" method="post" class="card p-4 shadow-sm">
                <input type="hidden" name="Op" value="Nuevo">
                <div class="mb-3">
                    <label class="form-label">Apellidos</label>
                    <input type="text" name="apellidos" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Nombres</label>
                    <input type="text" name="nombres" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Dirección</label>
                    <input type="text" name="direccion" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">DNI</label>
                    <input type="text" name="DNI" class="form-control" maxlength="8">
                </div>

                <div class="mb-3">
                    <label class="form-label">Teléfono</label>
                    <input type="text" name="telefono" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">Móvil</label>
                    <input type="text" name="movil" class="form-control">
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                    <a href="Index.jsp" class="btn btn-secondary">Volver</a>
                </div>
            </form>

        </div>

    </body>
</html>