<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registrar Pedido</title>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/styles_pedidos.css">
        <link rel="stylesheet" type="text/css" href="css/styles_lista.css">
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

                <section class="datosCliente">

                    <!-- Fila 1 -->
                    <div class="fila1">
                        <div class="mb-3 cliente">
                            <label for="idCliente" class="form-label">Cliente</label>
                            <select id="idCliente" name="idCliente" class="form-select" required>
                                <option value="">Seleccione un cliente</option>
                                <c:forEach var="cliente" items="${listaClientes}">
                                    <option 
                                        value="${cliente.idCliente}"
                                        data-nombres="${cliente.nombres}"
                                        data-apellidos="${cliente.apellidos}"
                                        data-direccion="${cliente.direccion}"
                                        data-telefono="${cliente.telefono}">
                                        ${cliente.idCliente} - ${cliente.nombres} ${cliente.apellidos}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3 nombres">
                            <label for="nombres" class="form-label">Nombres</label>
                            <input type="text" id="nombres" class="form-control" readonly>
                        </div>
                    </div>

                    <!-- Fila 2 -->
                    <div class="fila2">
                        <div class="mb-3 direccion">
                            <label for="direccion" class="form-label">Dirección</label>
                            <input type="text" id="direccion" class="form-control" readonly>
                        </div>

                        <div class="mb-3 telefono">
                            <label for="telefono" class="form-label">Teléfono</label>
                            <input type="text" id="telefono" class="form-control" readonly>
                        </div>
                    </div>

                    <!-- Fila 3 -->
                    <div class="fila3">
                        <div class="mb-3 fecha">
                            <label class="form-label">Fecha: ${fechaVisible}</label>
                            <input type="hidden" name="fechaPedido" value="${fechaVisible}">
                        </div>

                        <div class="mb-3 npedido">
                            <label class="form-label">N° pedido: ${codigoPedido}</label> 
                            
                        </div>
                    </div>

                </section>

                    <table class="table" id="tablaItems">
                        <thead>
                            <tr>
                                <th>Acciones</th>
                                <th>Item</th>
                                <th>Descripción</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th>IGV</th>
                                <th>Sub Total</th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- Filas dinámicas -->
                        </tbody>

                        <tfoot>
                            <tr>
                                <td colspan="7" class="text-center">
                                    <a href="#" class="agregarItem btn btn-primary">+ Nuevo Item</a>
                                </td>
                            </tr>
                        </tfoot>
                    </table>

                </section>

                <section class="cuenta">
                    <div class="mb-3">
                        <label class="form-label">SubTotal: </label><br>
                        <label class="form-label">IGV: </label><br>
                        <label class="form-label">Total: </label><br>
                        <input type="hidden" id="montoTotalHidden" name="montoTotal" value="0">
                    </div>
                </section>

                <div class="modalProducto" id="modalProducto">
                    <div class="modal-content">
                        <h5>Agregar Producto</h5>

                        <div class="mb-3 producto">
                            <label for="idArticulo" class="form-label">Producto</label>
                                <select id="modalIdArticulo" name="idArticulo" class="form-select" required>
                                    <option value="">Seleccione un producto</option>
                                    <c:forEach var="pro" items="${listaProductos}">
                                        <option 
                                            value="${pro.idArticulo}"
                                            data-nombre="${pro.nombre}"
                                            data-stock="${pro.cantidad}"
                                            data-precio="${pro.precio}">
                                            ${pro.idArticulo} - ${pro.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                        </div>

                        <div class="mb-3 nombre">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" id="modalnombre" class="form-control" readonly>
                        </div>

                        <div class="mb-3 stock">
                            <label for="stock" class="form-label">Stock</label>
                            <input type="text" id="modalstock" class="form-control" readonly>
                        </div>

                        <div class="mb-3 cantidad">
                            <label for="cantidad" class="form-label">Cantidad</label>
                            <input type="number" id="modalcantidad" class="form-control" min="1" value="1">
                        </div>

                        <div class="mb-3 precio">
                            <label for="precio" class="form-label">Precio</label>
                            <input type="number" id="modalprecio" class="form-control" step="0.01">
                        </div>

                        <div class="acciones">
                            <a href="#" id="btnAgregar" class="btn btn-success">Agregar</a>
                            <a href="#" id="btnCerrar" class="btn btn-secondary">Cerrar</a>
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Registrar Pedido</button>
                    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
                </div>
            </form>
        </div>

    </body>
</html>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script>
    //PARA VER LLENAR LOS DATOS CON SOLO EL ID Y TNER UNA PREVISTA DE DATOS
    $(document).ready(function () {
        // Inicializar Select2
        $('#idCliente').select2({
            width: '100%',
            placeholder: 'Seleccione un cliente'
        });

        // Variables para guardar el cliente seleccionado
        let selectedOption = null;

        // Al seleccionar un cliente (clic)
        $('#idCliente').on('change', function () {
            const option = $(this).find(':selected');
            selectedOption = option;
            if (option.val()) {
                $('#nombres').val(option.data('nombres') + " " + option.data('apellidos'));
                $('#direccion').val(option.data('direccion'));
                $('#telefono').val(option.data('telefono'));
            } else {
                $('#nombres, #direccion, #telefono').val('');
            }
        });

        // Detectar hover sobre opciones del menú Select2
        $(document).on('mouseenter', '.select2-results__option', function () {
            const optionValue = $(this).attr('id')?.split('-').pop();
            const realOption = $('#idCliente').find('option[value="' + optionValue + '"]');
            if (realOption.length) {
                $('#nombres').val(realOption.data('nombres') + " " + realOption.data('apellidos'));
                $('#direccion').val(realOption.data('direccion'));
                $('#telefono').val(realOption.data('telefono'));
            }
        });

        // Cuando el menú se cierra, restaurar los datos reales
        $('#idCliente').on('select2:close', function () {
            if (selectedOption && selectedOption.val()) {
                $('#nombres').val(selectedOption.data('nombres') + " " + selectedOption.data('apellidos'));
                $('#direccion').val(selectedOption.data('direccion'));
                $('#telefono').val(selectedOption.data('telefono'));
            } else {
                $('#nombres, #direccion, #telefono').val('');
            }
        });
    });
</script>

<script>    
    // Lógica para agregar items y calcular totales
    document.addEventListener('DOMContentLoaded', function () {
        // Referencias del modal
        const modal = document.getElementById('modalProducto');
        const btnAbrir = document.querySelector('.agregarItem');
        const btnCerrar = document.getElementById('btnCerrar');
        const selectProducto = document.getElementById('modalIdArticulo');

        // Abrir modal
        btnAbrir.addEventListener('click', (e) => {
            e.preventDefault();
            modal.style.display = 'flex';
        });

        // Cerrar modal
        btnCerrar.addEventListener('click', (e) => {
            e.preventDefault();
            modal.style.display = 'none';
        });

        // Cerrar clic afuera
        window.addEventListener('click', (e) => {
            if (e.target === modal) modal.style.display = 'none';
        });

        // Al seleccionar un producto → llenar campos
        selectProducto.addEventListener('change', function () {
            const selected = this.options[this.selectedIndex];

            document.getElementById("modalnombre").value = selected.getAttribute("data-nombre") || "";
            document.getElementById("modalstock").value = selected.getAttribute("data-stock") || "";
            document.getElementById("modalprecio").value = selected.getAttribute("data-precio") || "";
        });

        const btnAgregar = document.getElementById('btnAgregar');
        const tablaItems = document.getElementById('tablaItems').getElementsByTagName('tbody')[0];
        let itemCounter = 1;
        const IGV_RATE = 0.18; // 18%

        btnAgregar.addEventListener('click', function (e) {
            e.preventDefault();

            const selectProducto = document.getElementById('modalIdArticulo');
            const selectedOption = selectProducto.options[selectProducto.selectedIndex];

            if (!selectedOption.value) {
                alert('Por favor, seleccione un producto.');
                return;
            }

            const idArticulo = selectedOption.value;
            const nombre = selectedOption.getAttribute('data-nombre');
            const precio = parseFloat(document.getElementById('modalprecio').value);
            const cantidad = parseInt(document.getElementById('modalcantidad').value);
            const stock = parseInt(selectedOption.getAttribute('data-stock'));

            if (cantidad > stock) {
                alert('La cantidad solicitada supera el stock disponible.');
                return;
            }

            if (isNaN(precio) || precio <= 0 || isNaN(cantidad) || cantidad <= 0) {
                alert('La cantidad y el precio deben ser números positivos.');
                return;
            }

            const subTotal = precio * cantidad;
            const igv = subTotal * IGV_RATE;

            const newRow = tablaItems.insertRow();
            newRow.insertCell().innerHTML = `<a href="#" class="btn btn-danger btn-sm btn-eliminar">Eliminar</a>`;
            newRow.insertCell().innerText = itemCounter++;
            newRow.insertCell().innerText = nombre;
            newRow.insertCell().innerText = precio.toFixed(2);
            newRow.insertCell().innerText = cantidad;
            newRow.insertCell().innerText = igv.toFixed(2);
            newRow.insertCell().innerText = subTotal.toFixed(2);

            // Hidden inputs
            const hiddenIdArticulo = document.createElement('input');
            hiddenIdArticulo.type = 'hidden';
            hiddenIdArticulo.name = 'idArticulo';
            hiddenIdArticulo.value = idArticulo;
            newRow.appendChild(hiddenIdArticulo);

            const hiddenCantidad = document.createElement('input');
            hiddenCantidad.type = 'hidden';
            hiddenCantidad.name = 'cantidad';
            hiddenCantidad.value = cantidad;
            newRow.appendChild(hiddenCantidad);

            const hiddenPrecio = document.createElement('input');
            hiddenPrecio.type = 'hidden';
            hiddenPrecio.name = 'precio';
            hiddenPrecio.value = precio;
            newRow.appendChild(hiddenPrecio);

            actualizarTotales();
            modal.style.display = 'none'; // Use the 'modal' variable here
        });

        tablaItems.addEventListener('click', function (e) {
            if (e.target.classList.contains('btn-eliminar')) {
                e.preventDefault();
                e.target.closest('tr').remove();
                actualizarTotales();
            }
        });

        function actualizarTotales() {
            let granSubTotal = 0;
            let granIgv = 0;

            for (let row of tablaItems.rows) {
                // Ensure row.cells[6] and row.cells[5] exist before accessing innerText
                const subTotalText = row.cells.length > 6 ? row.cells[6].innerText : "0";
                const igvText = row.cells.length > 5 ? row.cells[5].innerText : "0";

                const subTotal = parseFloat(subTotalText);
                granSubTotal += isNaN(subTotal) ? 0 : subTotal; // Handle NaN from parseFloat

                const igv = parseFloat(igvText);
                granIgv += isNaN(igv) ? 0 : igv; // Handle NaN from parseFloat
            }
            
            const granTotal = granSubTotal + granIgv;

            // --- DEBUGGING ---
            console.log("DEBUG JS: granSubTotal = ", granSubTotal);
            console.log("DEBUG JS: granIgv = ", granIgv);
            console.log("DEBUG JS: granTotal = ", granTotal);
            // --- END DEBUGGING ---

            const cuentaSection = document.querySelector('.cuenta .mb-3');
            
            // --- NEW DEBUGGING ---
            console.log("DEBUG JS: cuentaSection element found:", cuentaSection);
            if (cuentaSection) {
                const newHTML = `
<label class="form-label">SubTotal: ${granSubTotal.toFixed(2)}</label><br>
<label class="form-label">IGV: ${granIgv.toFixed(2)}</label><br>
<label class="form-label">Total: ${granTotal.toFixed(2)}</label><br>
                `;
                console.log("DEBUG JS: Setting innerHTML to:", newHTML);
                cuentaSection.innerHTML = newHTML;
                document.getElementById('montoTotalHidden').value = granTotal.toFixed(2);
            } else {
                console.error("DEBUG JS: Element with selector '.cuenta .mb-3' not found!");
            }
        }
    });
</script>
<script>
document.querySelector("form").addEventListener("submit", function (e) {
    // Fuerza el cálculo antes de enviar
    if (typeof actualizarTotales === "function") {
        actualizarTotales();
    }
});
</script>


