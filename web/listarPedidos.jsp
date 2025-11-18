<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Pedidos</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/styles_lista.css">
        <a href="Index.jsp" > Inicio </a>
</head>
<body class="bg-light">
<div class="container mt-5">

    <h2 class="text-center mb-4">Lista de Pedidos</h2>

    <!-- Si no hay pedidos -->
    <c:if test="${empty listaPedidos}">
        <div class="alert alert-warning text-center">
            No hay pedidos registrados.
        </div>
    </c:if>

    <!-- Tabla de pedidos -->
    <c:if test="${not empty listaPedidos}">
        <table class="table table-striped table-hover shadow-sm">
            <thead class="table-primary text-center">
                <tr>
                    <th>ID Pedido</th>
                    <th>ID Cliente</th>
                    <th>Fecha</th>
                    <th>Monto Total</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody class="text-center">
                <c:forEach var="p" items="${listaPedidos}">
                    <tr>
                        <td>${p.idPedido}</td>
                        <td>${p.idCliente}</td>
                        <td>${p.fechaPedido}</td>
                        <td>S/. ${p.montoTotal}</td>
                        <td>${p.estado}</td>

                        <td>
                            <a href="PedidoControlador?Op=Eliminar&id=${p.idPedido}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('¿Seguro que deseas eliminar este pedido?');">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </c:if>

    <div class="text-center mt-4">
        <a href="Index.jsp" class="btn btn-secondary">Volver al Menú</a>
    </div>

</div>
</body>
</html>