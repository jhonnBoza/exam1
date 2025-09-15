package com.tecsup.exam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Información del Sistema</title>");
        out.println("<meta charset='UTF-8'>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println(".info-box { background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 5px; padding: 15px; margin: 10px 0; }");
        out.println(".btn { display: inline-block; padding: 10px 20px; margin: 5px; text-decoration: none; border-radius: 3px; }");
        out.println(".btn-primary { background-color: #007bff; color: white; }");
        out.println(".btn-secondary { background-color: #6c757d; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>Información del Sistema</h1>");
        
        out.println("<div class='info-box'>");
        out.println("<h3>Estado del Servidor</h3>");
        out.println("<p><strong>Servidor:</strong> Apache Tomcat 9.0.108</p>");
        out.println("<p><strong>Java EE:</strong> Servlet API 4.0</p>");
        out.println("<p><strong>Base de Datos:</strong> H2 Database (en memoria)</p>");
        out.println("<p><strong>Fecha/Hora:</strong> " + new Date() + "</p>");
        out.println("</div>");
        
        out.println("<div class='info-box'>");
        out.println("<h3>Funcionalidades Disponibles</h3>");
        out.println("<ul>");
        out.println("<li>✅ Listar todos los estudiantes</li>");
        out.println("<li>✅ Agregar nuevos estudiantes</li>");
        out.println("<li>✅ Editar información de estudiantes</li>");
        out.println("<li>✅ Eliminar estudiantes</li>");
        out.println("<li>✅ Base de datos con datos de ejemplo</li>");
        out.println("</ul>");
        out.println("</div>");
        
        out.println("<div class='info-box'>");
        out.println("<h3>Datos de Ejemplo</h3>");
        out.println("<p>El sistema incluye 3 estudiantes de ejemplo:</p>");
        out.println("<ul>");
        out.println("<li>Juan Pérez - juan.perez@email.com</li>");
        out.println("<li>María García - maria.garcia@email.com</li>");
        out.println("<li>Carlos López - carlos.lopez@email.com</li>");
        out.println("</ul>");
        out.println("</div>");
        
        out.println("<a href='estudiantes' class='btn btn-primary'>Ir al Sistema de Estudiantes</a>");
        out.println("<a href='/exam_war_exploded/' class='btn btn-secondary'>Volver al Inicio</a>");
        
        out.println("</body>");
        out.println("</html>");
    }
}