<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gestión de Estudiantes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; text-align: center; }
        .container { max-width: 600px; margin: 0 auto; }
        .btn { display: inline-block; padding: 15px 30px; margin: 10px; 
               text-decoration: none; border-radius: 5px; font-size: 16px; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-secondary { background-color: #6c757d; color: white; }
        h1 { color: #333; }
        p { color: #666; font-size: 18px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Sistema de Gestión de Estudiantes</h1>
        <p>Administra la información de los estudiantes de manera fácil y eficiente</p>
        <br/>
        <a href="estudiantes" class="btn btn-primary">Ver Estudiantes</a>
        <br/>
        <a href="hello" class="btn btn-secondary">Información del Sistema</a>
    </div>
</body>
</html>