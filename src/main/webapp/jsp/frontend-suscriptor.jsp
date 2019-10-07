<%-- 
    Document   : frontend-suscriptor
    Created on : 24-sep-2019, 21:40:20
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
        <title>Suscriptor</title>
    </head>
    
    <body class="fondoSuscriptor">
        
        <header>
            <%@include file="../html/barra-navegacion-suscriptor.html"%>
        </header>
          
        <c:if test="${sessionScope.usuario != null}">
            <c:if test="${sessionScope.actualizar == 'si'}"> 
                <c:remove var="actualizar"></c:remove>
                <c:choose>
                    <c:when test="${sessionScope.mostrar == 'suscripciones'}">
                        
                    </c:when>
                    <c:otherwise>
                        <c:redirect url="/jsp/ControladorMostrarRevistasSuscriptor"></c:redirect>
                    </c:otherwise>   
                </c:choose>">
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
                                            <h5>Autor: ${revista.autor}</h5>
                                            <h5>Volumen: ${revista.volumen}</h5>
                                            <a href="/SistemaWebDeRevistas/jsp/ControladorVerRevista?idRevista=${revista.idRevista}" class="btn btn-primary">Ver</a>
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

