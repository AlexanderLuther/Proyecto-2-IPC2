<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/style-sheets.html"%>
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <title>Modificar Revista</title>
    </head>
    <body class="fondoAdministrador">
        
        <header>
            <%@include file="../html/barra-navegacion-administrador.html"%>
        </header>
        
         <main class="container-fluid">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card text-white bg-dark mb-4">
                                <img src="../images/pdf.png" class="card-img-top" width="100" height="450">
                                <div class="card-body">
                                    <center>
                                        <h5>Nombre: ${revistaSeleccionada.nombre}</h5>
                                        <h5>Autor: ${revistaSeleccionada.autor}</h5>
                                        <h5>Volumen: ${revistaSeleccionada.volumen}</h5>
                                        <h5>Descripcion: ${revistaSeleccionada.descripcion}</h5>
                                        <h5>Suscripcion: Q${revistaSeleccionada.cuotaSuscripcion}</h5>
                                        <h5>Costo: Q${revistaSeleccionada.costoPorDia}</h5>                  
                                    </center>    
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">${revistaSeleccionada.fechaCreacion}</small>
                                </div>    
                            </div>
                        </div>
                        <div class="col-md-6">
                            <br> <br> <br>
                            <form action="/SistemaWebDeRevistas/jsp/ControladorModificarRevistaAdministrador" method="POST">  
                                <div>
                                    <div class="form-group">
                                        <label class="campoObligatorio"> * </label>
                                        <label class="etiquetaBlanca" for="costoIndividual">Costo por dia</label>
                                        <c:out value="${revistaSeleccionada.idRevista}"></c:out>
                                        <input type="number" step="0.01" class="form-control" id="costoRevista" name="costoRevista" placeholder="Ingresar el nuevo valor del costo por dia de la revista" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="campoObligatorio"> * </label>
                                        <label for=fecha class="etiquetaBlanca">Fecha de modificacion de costo</label>
                                        <input type="date" class="form-control" id="fecha" name="fecha" placeholder="Ingresar la fecha de modificacion de costo por dia" required>
                                    </div>    
                                    <div>
                                        <center>
                                            <a href="/SistemaWebDeRevistas/jsp/frontend-administrador.jsp" class="btn btn-danger">Cancelar</a>
                                            <button type="submit" class="btn btn-success">Modificar</button>
                                        </center>
                                    </div>    
                                </div>
                            </form>    
                        </div>        
                    </div> 
                </div>                         
        </main>
                                
        <%@include file="../html/footer.html"%>
        <%@include file="../html/modal-costo-global.html"%>
        <%@include file="../html/modal-porcentaje-pago.html"%>
        <%@include file="../html/scripts.html"%>
    </body>

</html>
