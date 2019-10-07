<%-- 
    Document   : perfil
    Created on : 03-oct-2019, 23:27:23
    Author     : helmuthluther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
    </head>
    
    <body class="<c:choose>
                    <c:when test="${tipo == 1}">
                        fondoAdministrador
                    </c:when>
                    <c:when test="${tipo == 2}">
                        fondoEditor
                    </c:when>
                    <c:when test="${tipo == 3}">
                        fondoSuscriptor
                    </c:when>   
                </c:choose>">
            
        <header>  
            <%@include file="../html/style-sheets.html" %> 
            <link href="../css/style.css" rel="stylesheet" type="text/css">  
            <c:choose>
                <c:when test="${tipo == 1}">
                    <%@include file="../html/barra-navegacion-administrador.html"%>
                </c:when>
                <c:when test="${tipo == 2}">
                    <%@include file="../html/barra-navegacion-editor.html"%>
                </c:when>
                <c:when test="${tipo == 3}">
                    <%@include file="../html/barra-navegacion-suscriptor.html"%>
                </c:when>   
            </c:choose>            
        </header>    
            
        <div class="container-fluid">
            <center>
                <br><br>  
                <h1 class="etiquetaBlanca"> <c:out value="${perfil.nombreUsuario}"></c:out> </h1>       
                <div class="form-group">
                    <img src="${perfil.URLFotoPerfil}" width="400" height="400">
                    <br>
                </div>
                
                <div class="container">    
                    <table class="table table-borderless table-dark">
                        <tbody>
                            <tr>
                                <td>Nombre de usuario:</td>
                                <td><c:out value="${perfil.nombreUsuario}"></c:out></td>
                            </tr>
                            <tr class="bg-primary">
                                <td>Nombre:</td>
                                <td><c:out value="${perfil.nombre}"></c:out></td>
                            </tr>
                            <tr>
                                <td>Apellido:</td>
                                <td><c:out value="${perfil.apellido}"></c:out></td>
                            </tr>
                            <tr class="bg-success">
                                <td>Hobbies:</td>
                                <td><c:out value="${perfil.hobbies}"></c:out></td>
                            </tr>
                            <tr>
                                <td>Temas de interes:</td>
                                <td><c:out value="${perfil.temasInteres}"></c:out></td>
                            </tr>
                            <tr class="bg-danger">
                                <td>Descripcion:</td>
                            <td><c:out value="${perfil.descripcion}"></c:out></td>
                            </tr>
                            <tr>
                                <td>Gustos:</td>
                                <td><c:out value="${perfil.gustos}"></c:out></td>
                            </tr>
                        </tbody>
                    </table>  
                    <br>
                    <div    <c:if test="${requestScope.ocultar == 'si'}"> 
                                <c:remove var="ocultar"></c:remove>
                                hidden
                            </c:if> >
                        <a href="/SistemaWebDeRevistas/jsp/ControladorModificarPerfil" class="btn btn-danger">Modificar</a>
                    </div>
                </div>
            </center>
        </div>
        <%@include file="../html/footer.html"%>  
        <%@include file="../html/modal-costo-global.html"%>
        <%@include file="../html/modal-porcentaje-pago.html"%>
        <%@include file="../html/modal-crear-administrador.html"%>
        <!-- ARCHIVOS JS -->    
        <%@include file="../html/scripts.html" %>       
    </body>
</html>
