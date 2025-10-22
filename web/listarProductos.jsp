<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Lista de Productos</h2>

    <!-- 🔍 Formulario de búsqueda -->
    <form action="ProductoControlador" method="get" class="d-flex mb-4 justify-content-center">
        <input type="hidden" name="op" value="buscar">
        <input type="text" name="texto" class="form-control w-50 me-2" 
               placeholder="Buscar por ID o nombre" required>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>

    <!-- Mensaje cuando no se encuentran resultados -->
    <c:if test="${empty listaProductos}">
        <div class="alert alert-warning text-center" role="alert">
            No se encontraron productos que coincidan con la búsqueda.
        </div>
    </c:if>

    <!-- 📋 Tabla de productos -->
    <c:if test="${not empty listaProductos}">
        <table class="table table-striped table-hover shadow-sm">
            <thead class="table-primary text-center">
                <tr>
                    <th>ID Artículo</th>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody class="text-center">
                <c:forEach var="p" items="${listaProductos}">
                    <tr>
                        <td>${p.idArticulo}</td>
                        <td>${p.nombre}</td>
                        <td>${p.cantidad}</td>
                        <td>${p.precio}</td>
                        <td>
                            <!-- Botón Modificar -->
                            <a href="ProductoControlador?op=obtener&id=${p.idArticulo}" 
                               class="btn btn-warning btn-sm me-2">
                                Modificar
                            </a>

                            <!-- Botón Eliminar -->
                            <a href="ProductoControlador?op=eliminar&id=${p.idArticulo}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('¿Seguro que deseas eliminar este producto?');">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- 🔁 Botones de acción -->
    <div class="text-center mt-4">
        <a href="ProductoControlador?op=listar" class="btn btn-outline-secondary me-2">
            Mostrar todos
        </a>
        <a href="Index.jsp" class="btn btn-secondary">Volver al Menú</a>
    </div>
</div>

</body>
</html>
