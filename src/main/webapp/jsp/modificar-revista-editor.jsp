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
            <%@include file="../html/barra-navegacion-editor.html"%>
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
                                        <h5>Volumen: ${revistaSeleccionada.volumen}</h5>
                                        <h5>Descripcion: ${revista.descripcion}</h5>
                                        <h5>Suscripcion: Q${revistaSeleccionada.cuotaSuscripcion}</h5>
                                        <h5>Comentarios: 
                                            <c:if test="${revistaSeleccionada.bloquearComentarios == false}">Si</c:if>
                                            <c:if test="${revistaSeleccionada.bloquearComentarios == true}">No</c:if>
                                        </h5>
                                        <h5>MeGusta:
                                            <c:if test="${revistaSeleccionada.bloquearMeGusta == false}">Si</c:if>
                                            <c:if test="${revistaSeleccionada.bloquearMeGusta == true}">No</c:if>
                                        </h5>
                                        <h5>Suscripciones: 
                                            <c:if test="${revistaSeleccionada.bloquearSuscripciones == false}">Si</c:if>
                                            <c:if test="${revistaSeleccionada.bloquearSuscripciones == true}">No</c:if>
                                        </h5>      
                                    </center>    
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">${revistaSeleccionada.fechaCreacion}</small>
                                </div>    
                            </div>
                        </div>
                        <div class="col-md-6">
                            <form action="/SistemaWebDeRevistas/jsp/ControladorModificarRevista" method="POST">  
                                <div>
                                    <label class="campoObligatorio"> * </label>
                                    <label for="suscripciones" class="etiquetaBlanca">Suscripciones</label>   
                                    <select class="custom-select custom-select-sm" id="suscripciones" name="suscripciones" required> 
                                        <option value="" disabled selected> Seleccione una opcion </option>
                                        <option value="true" > Permitir </option>
                                        <option value="false" > Bloquear </option>
                                    </select> 
                                </div>
                                <br>
                                <div>
                                    <label class="campoObligatorio"> * </label>
                                    <label for="comentarios" class="etiquetaBlanca">Comentarios</label>   
                                    <select class="custom-select custom-select-sm" id="comentarios" name="comentarios" required> 
                                        <option value="" disabled selected> Seleccione una opcion </option>
                                        <option value="true" > Permitir </option>
                                        <option value="false" > Bloquear </option>
                                    </select> 
                                </div>
                                <br>
                                <div>
                                    <label class="campoObligatorio"> * </label>
                                    <label for="megusta" class="etiquetaBlanca">Me Gusta</label>   
                                    <select class="custom-select custom-select-sm" id="meGusta" name="meGusta" required> 
                                        <option value="" disabled selected> Seleccione una opcion </option>
                                        <option value="true" > Permitir </option>
                                        <option value="false" > Bloquear </option>
                                    </select> 
                                </div>
                                <div>
                                    <br><br>
                                    <center>
                                        <a href="/SistemaWebDeRevistas/jsp/frontend-editor.jsp" class="btn btn-danger">Cancelar</a>
                                        <button type="submit" class="btn btn-success">Modificar</button>
                                    </center>
                                    
                                </div>
                            </form>    
                        </div>        
                    </div> 
                </div>                         
        </main>
        <%@include file="../html/footer.html"%>
        <%@include file="../html/scripts.html"%>
    </body>

</html>
