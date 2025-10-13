<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Semana 07</title>
        <link rel="stylesheet" type="text/css" href="css/styles_index.css">
    </head>

    <header>
        <h2>Bienvenido: ${usuarioLogueado.nombres} - ${usuarioLogueado.permisos} </h2>
        <nav>   
            <a href="UsuarioControlador?Op=CerrarSesion">Cerrar sesión</a>
        </nav>
    </header>

    <body>
        <div class="main-content">
            <div class="container">
                <h1>Menú de Clientes</h1>
                <p><a href="ClienteControlador?Op=Listar">Listar Clientes</a></p>
                <p><a href="UsuarioControlador?Op=Nuevo">Nuevo Cliente</a></p>
            </div>
        </div>
    </body>
</html>
