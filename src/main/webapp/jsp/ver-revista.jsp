<%-- 
    Document   : ver-revista
    Created on : 06-oct-2019, 7:25:13
    Author     : helmuthluther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/style-sheets.html"%>
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <title>Ver Revsita</title>
    </head>
    
    <body class="fondoSuscriptor">
        
        <header>
            <%@include file="../html/barra-navegacion-suscriptor.html"%>
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
                                        <h5>Categoria(s): ${categorias.contenido}</h5>
                                        <h5>Etiqueta(s): ${etiquetas.contenido}</h5>
                                        <h5>MeGusta: ${requestScope.cantidadMeGusta}</h5>
                                        <h5>Cuota Mensual: Q${revistaSeleccionada.cuotaSuscripcion}</h5>
                                    </center>    
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">${revistaSeleccionada.fechaCreacion}</small>
                                </div>    
                            </div>
                            <div>
                                <br>
                                <center>
                                    <a href="/SistemaWebDeRevistas/jsp/frontend-suscriptor.jsp?r=${revistaSeleccionada.idRevista}" class="btn btn-danger">Regresar</a>
                                    &nbsp &nbsp
                                    <a href="${revistaSeleccionada.URLPDF}" target="_blank" class="btn btn-info">Ver</a>
                                </center>
                                <br>
                            </div>            
                        </div>
                        <div class="col-md-6">
                            <br> <br> <br>
                            <form action="/SistemaWebDeRevistas/jsp/ControladorComentarios?r=${revistaSeleccionada.idRevista}" method="POST">  
                                <div>
                                    <div class="form-group">
                                        <center>
                                            <h3 class="etiquetaBlanca"> Comentarios </h3> 
                                        </center>    
                                    </div>
                                    <table class="table table-striped table-dark">   
                                        <c:forEach var="comentario" items="${requestScope.listadoComentarios}">
                                            <tbody>
                                                <tr>
                                                    <td>${comentario.nombreUsuario}</td>
                                                    <td>${comentario.comentario}</td>
                                                </tr>
                                            </tbody>
                                        </c:forEach>
                                    </table>    
                                    <c:if test="${revistaSeleccionada.bloquearComentarios == false}">
                                        <div class="form-group">
                                            <label class="campoObligatorio"> * </label>
                                            <label for=fecha class="etiquetaBlanca">Fecha de comentario</label>
                                            <input type="date" class="form-control" id="fecha" name="fecha" placeholder="Ingresar la fecha del comentario" required>
                                        </div> 
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="comentario" name="comentario" placeholder="Realize algun comentario..." required>
                                        </div>    
                                        <div>
                                            <center>
                                                <button type="submit" class="btn btn-success">Comentar</button>
                                                <a href="#" class="btn btn-info">Me Gusta</a>
                                            </center>
                                        </div> 
                                    </c:if>
                                    <c:if test="${revistaSeleccionada.bloquearComentarios == true}"> 
                                            <label class="etiquetaBlanca">No se permiten comentarios en esta revista </label>>
                                    </c:if>
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
