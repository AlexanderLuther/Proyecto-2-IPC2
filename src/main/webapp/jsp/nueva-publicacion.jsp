<%-- 
    Document   : nueva-publicacion
    Created on : 04-oct-2019, 8:09:11
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
        <title>Crear Publicacion</title>
    </head>
    
    <body class="fondoEditor">
        
        <header>
            <%@include file="../html/barra-navegacion-editor.html"%>
        </header> 
        
        <main class="container-fluid">
            <section class="main row">
                <aside class="col-md-4"></aside>        
                <form class="col-md-4" action="/SistemaWebDeRevistas/ControladorPublicacionRevista" enctype="multipart/form-data" method="POST">
                    <center><h2 class="etiquetaBlanca"> Detalles de la Publicacion </h2></center>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="nombrePubliacion" class="etiquetaBlanca">Nombre de la Publicacion</label>
                        <input type="text" class="form-control" id="nombrePublicacion" name="nombrePublicacion" placeholder="Ingresar nombre de la publicacion" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="cuotaSuscripcion" class="etiquetaBlanca">Cuota de Suscripcion</label>
                        <input type="number" step="0.01" class="form-control" id="cuotaSuscripcion" name="cuotaSuscripcion" placeholder="Ingresar el valor de la cuota de suscripcion" required>
                    </div>
                    <center><h2 class="etiquetaBlanca"> Detalles de la Revista </h2></center>
                      <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="revista" class="etiquetaBlanca">Revista</label> <br>
                        <input type="file" class="etiquetaBlanca" name="revista" accept=".pdf" required> <br>
                        </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="nombre" class="etiquetaBlanca">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre de la revista" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="autor" class="etiquetaBlanca">Autor</label>
                        <input type="text" class="form-control" id="autor" name="autor" placeholder="Ingresar el autor" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="descripcion" class="etiquetaBlanca">Descripcion</label>
                        <textarea class="form-control" aria-label="With textarea" id="descripcion" name="descripcion" placeholder="Ingresar una descripcion" required></textarea>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="volumen" class="etiquetaBlanca">Volumen</label>
                        <input type="number"  min="1" pattern="^[0-9]+" class="form-control" id="volumen" name="volumen" placeholder="Ingresar el volumen" required>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="categorias" class="etiquetaBlanca">Categorias:</label>
                        <input type="text" class="form-control" id="categorias" name="categorias" placeholder="Ingresar categoria(s)" required>
                        <label class="etiquetaInstruccion">Cada categoria debe estar separada por una coma.</label>
                    </div>
                    <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for="etiquetas" class="etiquetaBlanca">Etiquetas:</label>
                        <input type="text" class="form-control" id="etiquetas" name="etiquetas" placeholder="Ingresar etiqueta(s)" required>
                        <label class="etiquetaInstruccion">Cada etiqueta debe estar separada por una coma.</label>
                    </div>
                     <div class="form-group">
                        <label class="campoObligatorio"> * </label>
                        <label for=fechaCreacion class="etiquetaBlanca">Fecha de creacion</label>
                        <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" placeholder="Ingresar la fecha de creacion" required>
                    </div>
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
                    <br>
                        <center>
                            <button type="submit" class="btn btn-success">Publicar</button>
                        </center>
                        <br><br>
                </form>    
                <aside class="col-md-4"></aside>
            </section>
        </main> 
        
        <%@include file="../html/footer.html"%>
        <!-- ARCHIVOS JS -->    
        <%@include file="../html/scripts.html" %>       
    </body>
</html>
