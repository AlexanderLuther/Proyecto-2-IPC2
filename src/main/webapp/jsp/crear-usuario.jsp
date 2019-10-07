<%-- 
    Document   : crear-usuario
    Created on : 23-sep-2019, 1:04:43
    Author     : helmuthluther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/style-sheets.html" %> 
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <title>Crear Usuario</title>
    </head>
    
    <body class="fondoPrincipal">
        <header>
            <%@include file="../html/barra-navegacion.html"%>
        </header>  
        <div class="container-fluid">
            <section class="main row">
                <aside class="col-md-4"></aside>        
                <form class="col-md-4" action="/SistemaWebDeRevistas/ControladorUsuario" enctype="multipart/form-data" method="POST">
                    <!--DATOS USUARIO -->
                    <center><h2 class="etiquetaBlanca"> Detalles de la  cuenta </h2></center>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="nombreUsuario" class="etiquetaBlanca">Nombre de Usuario</label>
                        <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Ingresar nombre de usuario" maxlength="50" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="contrasena" class="etiquetaBlanca">Contrasena</label>
                        <input type="password" class="form-control" id="contrasena" name="contrasena" placeholder="Ingresar contrasena" maxlength="50" required>
                    </div>
                    <div>
                        <label class="campoObligatorio"> * </label>
                        <label for="tipoUsuario" class="etiquetaBlanca">Tipo de Usuario</label>   
                        <select class="custom-select custom-select-sm" id="tipoUsuario" name="tipoUsuario" required> 
                            <option value="" disabled selected> Seleccione un tipo de usuario </option>
                            <option value="3" > Suscriptor </option>
                            <option value="2" > Editor </option>
                        </select> 
                    </div>
                    <br> <br>
                    
                    <!--DATOS PERFIL -->
                    <center><h2 class="etiquetaBlanca"> Detalles del perfil </h2></center>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="nombre" class="etiquetaBlanca">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre" maxlength="50" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="apellido" class="etiquetaBlanca">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresar apellido" maxlength="50" required>
                    </div> 
                    <div class="form-group">
                        <label for="hobbies" class="etiquetaBlanca">Foto de Perfil</label> <br>
                        <input type="file" class="etiquetaBlanca" name="fotoPerfil" accept="image/*"> <br>
                    </div>
                    <div class="form-group">
                        <label for="hobbies" class="etiquetaBlanca">Hobbies</label>
                        <textarea class="form-control" aria-label="With textarea" id="hobbies" name="hobbies" placeholder="Ingresar hobbies"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="temasInters" class="etiquetaBlanca">Temas de Interes</label>
                         <textarea class="form-control" aria-label="With textarea" id="temasInteres" name="temasInteres" placeholder="Ingresar temas de interes"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="descripcion" class="etiquetaBlanca">Descripcion</label>
                        <textarea class="form-control" aria-label="With textarea" id="descripcion" name="descripcion" placeholder="Ingresar descripcion"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="gustos" class="etiquetaBlanca">Gustos</label>
                         <textarea class="form-control" aria-label="With textarea" id="gustos" name="gustos" placeholder="Ingresar gustos"></textarea>
                    </div>  
                    <div>
                        <center>
                            <button type="submit" class="btn btn-success">Registar</button>
                        </center>
                    </div>
                    <br><br>
                </form>    
                <aside class="col-md-4"></aside>
            </section>
        </div> 
        
        <%@include file="../html/footer.html"%>
        <!--CUADRO EMERGENTE DE INICIO DE SESION -->
        <%@include file="../html/modal-inicio-sesion.html"%>
        <!-- ARCHIVOS JS -->    
        <%@include file="../html/scripts.html" %>       
    </body>
</html>
