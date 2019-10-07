<%-- 
    Document   : index2
    Created on : 24-sep-2019, 18:05:18
    Author     : helmuthluther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GT Magazine</title>
        <%@include file="html/style-sheets.html"%>
         <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body class="fondoPrincipal">  
        
        <header>
            <!-- BARRA DE NAVEGACION PRINCIPAL -->
            <%@include file="html/barra-navegacion.html"%>
        </header> 
        
         <c:if test="${usuario == null}">
             <c:if test="${sessionScope.listadoRevistas == null}"> 
                <c:redirect url="/ControladorMostrarRevistas"></c:redirect>
             </c:if>    
            <main class="container-fluid">
                <div class="album py-5 bg-muted">
                    <div class="container">
                        <div class="row">    
                            <c:forEach var="revista" items="${sessionScope.listadoRevistas}">
                                <div class="col-md-3">
                                    <div class="card text-white bg-dark mb-4">
                                        <img src="images/pdf.png" class="card-img-top">
                                        <div class="card-body">
                                            <center>
                                                <h5>Nombre: ${revista.nombre}</h5>
                                                <h5>Autor: ${revista.autor}</h5>
                                                <h5>Suscripcion: Q${revista.cuotaSuscripcion}</h5>
                                                <a href="/SistemaWebDeRevistas/jsp/ControladorPreVisualizarRevistaIndex?idRevista=${revista.idRevista}" class="btn btn-primary">PreVisualizar</a>
                                            </center>
                                        </div>
                                        <div class="card-footer">
                                            <small class="text-muted">${revista.fechaCreacion}</small>
                                        </div>    
                                    </div>
                                </div>
                            </c:forEach>
                        </div> 
                    </div>
                </div>  
            </main>
        </c:if>
        
            <%@include file="html/footer.html"%>   
        <!--CUADRO EMERGENTE DE INICIO DE SESION -->
        <%@include file="html/modal-inicio-sesion.html"%>
        <!--ARCHIVOS JS -->
        <%@include file="html/scripts.html"%>
    </body> 
</html>
