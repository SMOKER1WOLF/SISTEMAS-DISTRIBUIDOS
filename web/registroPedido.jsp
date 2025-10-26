<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Pedido</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Registrar Nuevo Pedido</h2>

    <%-- Muestra mensajes de éxito o error --%>
    <c:if test="${not empty mensaje}">
        <div class="alert alert-success">${mensaje}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="PedidoControlador" method="post" class="card p-4 shadow-sm">
        <input type="hidden" name="Op" value="Registrar">

        <div class="mb-3">
            <label for="idCliente" class="form-label">Cliente</label>
            <select id="idCliente" name="idCliente" class="form-select" required>
                <option value="">Seleccione un cliente</option>
                <c:forEach var="cliente" items="${listaClientes}">
                    <option value="${cliente.idCliente}">${cliente.nombres} ${cliente.apellidos}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="fechaPedido" class="form-label">Fecha del Pedido</label>
            <input type="date" id="fechaPedido" name="fechaPedido" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="montoTotal" class="form-label">Monto Total</label>
            <input type="number" id="montoTotal" name="montoTotal" class="form-control" step="0.01" required>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">Registrar Pedido</button>
            <a href="Index.jsp" class="btn btn-secondary">Volver al Inicio</a>
        </div>
    </form>
</div>

</body>
</html>
