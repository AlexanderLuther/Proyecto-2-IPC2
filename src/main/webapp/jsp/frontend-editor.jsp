<%-- 
    Document   : frontend-editor
    Created on : 24-sep-2019, 21:41:29
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
        <title>Editor</title>
    </head>
    <body class="fondoEditor">
        
        <header>
            <%@include file="../html/barra-navegacion-editor.html"%>
        </header>
            <c:if test="${sessionScope.usuario != null}">
            <c:if test="${sessionScope.actualizar == 'si'}"> 
                <c:remove var="actualizar"></c:remove>
                <c:redirect url="/jsp/ControladorMostrarRevistasEditor"></c:redirect>
            </c:if>
         <main class="container-fluid">
            <div class="album py-5 bg-muted">
                <div class="container">
                    <div class="row">    
                        <c:forEach var="revista" items="${sessionScope.listadoRevistas}" varStatus="loop">
                            <div class="col-md-3">
                                <div class="card text-white bg-dark mb-4">
                                    <img src="../images/pdf.png" class="card-img-top">
                                    <div class="card-body">
                                        <center>
                                            <h5>Nombre: ${revista.nombre}</h5>
                                            <h5>Volumen: ${revista.volumen}</h5>
                                            <h5>Suscripcion: Q${revista.cuotaSuscripcion}</h5>
                                            <h5>Comentarios: 
                                                <c:if test="${revista.bloquearComentarios == false}">Si</c:if>
                                                <c:if test="${revista.bloquearComentarios == true}">No</c:if>
                                            </h5>
                                            <h5>MeGusta:
                                                <c:if test="${revista.bloquearMeGusta == false}">Si</c:if>
                                                <c:if test="${revista.bloquearMeGusta == true}">No</c:if>
                                            </h5>
                                            <h5>Suscripciones: 
                                                <c:if test="${revista.bloquearSuscripciones == false}">Si</c:if>
                                                <c:if test="${revista.bloquearSuscripciones == true}">No</c:if>
                                            </h5>      
                                            <a href="${revista.URLPDF}" target="_blank" class="btn btn-primary">Ver</a>
                                            <a href="/SistemaWebDeRevistas/jsp/ControladorModificarRevista?idRevista=${revista.idRevista}" class="btn btn-primary">Modificar</a>
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
        <%@include file="../html/scripts.html"%>
    </body>
</html>
