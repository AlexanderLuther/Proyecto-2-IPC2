<%-- 
    Document   : crear-usuario
    Created on : 23-sep-2019, 1:04:43
    Author     : helmuthluther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/style-sheets.html" %> 
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <title>Modificar Perfil</title>
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
            <section class="main row">
                <aside class="col-md-4"></aside>        
                <form class="col-md-4" action="/SistemaWebDeRevistas/jsp/ControladorModificarPerfil" enctype="multipart/form-data" method="POST">
                    <div class="form-group" hidden>
                        <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Ingresar nombre de usuario" maxlength="50" value="${perfilSolicitado.nombreUsuario}" required>
                    </div>
                    <center><h2 class="etiquetaBlanca"><c:out value="${perfilSolicitado.nombreUsuario}"></c:out></h2></center>
                    <!--DATOS PERFIL -->
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="nombre" class="etiquetaBlanca">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre" maxlength="50" value="${perfilSolicitado.nombre}" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="apellido" class="etiquetaBlanca">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresar apellido" maxlength="50" value="${perfilSolicitado.apellido}" required>
                    </div> 
                    <div class="form-group">
                        <label for="hobbies" class="etiquetaBlanca">Foto de Perfil</label> <br>
                        <input type="file" class="etiquetaBlanca" name="fotoPerfil" accept="image/*"> <br>
                    </div>
                    <div class="form-group">
                        <label for="hobbies" class="etiquetaBlanca">Hobbies</label>
                        <textarea class="form-control" aria-label="With textarea" id="hobbies" name="hobbies" placeholder="Ingresar hobbies"><c:out value="${perfilSolicitado.hobbies}"></c:out> </textarea>
                    </div>
                    <div class="form-group">
                        <label for="temasInters" class="etiquetaBlanca">Temas de Interes</label>
                         <textarea class="form-control" aria-label="With textarea" id="temasInteres" name="temasInteres" placeholder="Ingresar temas de interes"><c:out value="${perfilSolicitado.temasInteres}"></c:out></textarea>
                    </div>
                    <div class="form-group">
                        <label for="descripcion" class="etiquetaBlanca">Descripcion</label>
                        <textarea class="form-control" aria-label="With textarea" id="descripcion" name="descripcion" placeholder="Ingresar descripcion"><c:out value="${perfilSolicitado.descripcion}"></c:out></textarea>
                    </div>
                    <div class="form-group">
                        <label for="gustos" class="etiquetaBlanca">Gustos</label>
                         <textarea class="form-control" aria-label="With textarea" id="gustos" name="gustos" placeholder="Ingresar gustos"><c:out value="${perfilSolicitado.gustos}"></c:out></textarea>
                    </div>  
                    <div>
                        <center>
                            <a href="/SistemaWebDeRevistas/jsp/ControladorPerfil" class="btn btn-danger">Cancelar</a>
                            <button type="submit" class="btn btn-success">Aceptar</button>
                        </center>
                    </div>
                    <br><br>
                </form>    
                <aside class="col-md-4"></aside>
            </section>
        </div> 
        
        <%@include file="../html/footer.html"%>
        <%@include file="../html/modal-costo-global.html"%>
        <%@include file="../html/modal-porcentaje-pago.html"%>
        <!-- ARCHIVOS JS -->    
        <%@include file="../html/scripts.html" %>       
    </body>
</html>
