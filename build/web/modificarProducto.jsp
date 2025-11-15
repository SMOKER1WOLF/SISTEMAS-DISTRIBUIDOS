<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Producto</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Modificar Producto</h2>

    <form action="ProductoControlador" method="post" class="card p-4 shadow-sm">
        <input type="hidden" name="op" value="actualizar">

        <div class="mb-3">
            <label class="form-label">ID Artículo</label>
            <input type="text" name="idArticulo" class="form-control" value="${producto.idArticulo}" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Nombre del Producto</label>
            <input type="text" name="nombre" class="form-control" value="${producto.nombre}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Cantidad</label>
            <input type="number" name="cantidad" class="form-control" value="${producto.cantidad}" min="0" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Precio</label>
            <input type="number" step="0.01" name="precio" class="form-control" value="${producto.precio}" min="0" required>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            <a href="ProductoControlador?op=listar" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

</body>
</html>
