<%-- 
    Document   : frontend-administrador
    Created on : 24-sep-2019, 21:41:00
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
        <title>Administrador</title>
    </head>
    <body class="fondoAdministrador">
        
        <header>
            <%@include file="../html/barra-navegacion-administrador.html"%>
        </header>
        <br> <br>
        
        
        <c:if test="${usuario != null}">  
            <c:if test="${sessionScope.actualizar == 'si'}"> 
                <c:remove var="actualizar"></c:remove>
                <c:redirect url="/jsp/ControladorMostrarRevistasAdministrador"></c:redirect>
            </c:if>
            <main class="container-fluid">
                <div class="album py-5 bg-muted">
                    <div class="container">
                        <div class="row">    
                            <c:forEach var="revista" items="${sessionScope.listadoRevistas}">
                                <div class="col-md-3">
                                    <div class="card text-white bg-dark mb-4">
                                        <img src="../images/pdf.png" class="card-img-top">
                                        <div class="card-body">
                                            <center>
                                                <h5>Nombre: ${revista.nombre}</h5>
                                                <h5>Autor: ${revista.autor}</h5>
                                                <h5>Costo: Q${revista.costoPorDia}</h5>
                                                <a href="${revista.URLPDF}" target="_blank" class="btn btn-primary">Ver</a>
                                                <a href="/SistemaWebDeRevistas/jsp/ControladorModificarRevistaAdministrador?idRevista=${revista.idRevista}" class="btn btn-primary">Modificar costo</a>
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
        
        <%@include file="../html/footer.html"%>
        <%@include file="../html/modal-costo-global.html"%>
        <%@include file="../html/modal-porcentaje-pago.html"%>
        <%@include file="../html/modal-crear-administrador.html"%>
        <%@include file="../html/scripts.html"%>
    </body>
</html>

