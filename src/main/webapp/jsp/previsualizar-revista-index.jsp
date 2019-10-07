<%-- 
    Document   : previsualizar-revista-index
    Created on : 06-oct-2019, 8:43:46
    Author     : helmuthluther
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/style-sheets.html"%>
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <title>Previsualizar Revista</title>
    </head>
    
    <body class="fondoPrincipal">
        
        <header>
            <%@include file="../html/barra-navegacion.html"%>
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
                                        <h5>Suscripcion: Q${revistaSeleccionada.cuotaSuscripcion}</h5>
       
                                    </center>    
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">${revistaSeleccionada.fechaCreacion}</small>
                                </div>    
                            </div>
                        </div>
                        <div class="col-md-6">
                            <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
                            <form action="#" method="POST">  
                                <div>
                                    <center>
                                        <a href="/SistemaWebDeRevistas/index.jsp" class="btn btn-danger">Regresar</a>
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
