package com.tecsup.exam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            mostrarListaEstudiantes(request, response);
        } else if ("nuevo".equals(action)) {
            mostrarFormularioNuevo(request, response);
        } else if ("editar".equals(action)) {
            mostrarFormularioEditar(request, response);
        } else if ("eliminar".equals(action)) {
            eliminarEstudiante(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("crear".equals(action)) {
            crearEstudiante(request, response);
        } else if ("actualizar".equals(action)) {
            actualizarEstudiante(request, response);
        }
    }

    private void mostrarListaEstudiantes(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        List<Estudiante> estudiantes = obtenerTodosLosEstudiantes();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Sistema de Gestión de Estudiantes</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".btn { padding: 5px 10px; margin: 2px; text-decoration: none; border-radius: 3px; }");
        out.println(".btn-primary { background-color: #007bff; color: white; }");
        out.println(".btn-success { background-color: #28a745; color: white; }");
        out.println(".btn-danger { background-color: #dc3545; color: white; }");
        out.println(".btn-warning { background-color: #ffc107; color: black; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>Sistema de Gestión de Estudiantes</h1>");
        out.println("<a href='/exam_war_exploded/' class='btn btn-secondary'>← Volver al Inicio</a>");
        out.println("<a href='?action=nuevo' class='btn btn-success'>Nuevo Estudiante</a>");
        out.println("<br><br>");
        
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Apellido</th>");
        out.println("<th>Email</th>");
        out.println("<th>Teléfono</th>");
        out.println("<th>Acciones</th>");
        out.println("</tr>");
        
        for (Estudiante estudiante : estudiantes) {
            out.println("<tr>");
            out.println("<td>" + estudiante.getId() + "</td>");
            out.println("<td>" + estudiante.getNombre() + "</td>");
            out.println("<td>" + estudiante.getApellido() + "</td>");
            out.println("<td>" + estudiante.getEmail() + "</td>");
            out.println("<td>" + estudiante.getTelefono() + "</td>");
            out.println("<td>");
            out.println("<a href='?action=editar&id=" + estudiante.getId() + "' class='btn btn-warning'>Editar</a>");
            out.println("<a href='?action=eliminar&id=" + estudiante.getId() + "' class='btn btn-danger' onclick='return confirm(\"¿Está seguro de eliminar este estudiante?\")'>Eliminar</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Nuevo Estudiante</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("form { max-width: 500px; }");
        out.println("input, label { display: block; margin: 5px 0; }");
        out.println("input[type='text'], input[type='email'] { width: 100%; padding: 8px; }");
        out.println(".btn { padding: 10px 20px; margin: 5px; text-decoration: none; border-radius: 3px; border: none; cursor: pointer; }");
        out.println(".btn-primary { background-color: #007bff; color: white; }");
        out.println(".btn-secondary { background-color: #6c757d; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>Nuevo Estudiante</h1>");
        out.println("<a href='/exam_war_exploded/' class='btn btn-secondary'>← Volver al Inicio</a>");
        out.println("<a href='estudiantes' class='btn btn-secondary'>← Ver Estudiantes</a>");
        out.println("<br><br>");
        out.println("<form method='post' action='estudiantes'>");
        out.println("<input type='hidden' name='action' value='crear'>");
        
        out.println("<label for='nombre'>Nombre:</label>");
        out.println("<input type='text' id='nombre' name='nombre' required>");
        
        out.println("<label for='apellido'>Apellido:</label>");
        out.println("<input type='text' id='apellido' name='apellido' required>");
        
        out.println("<label for='email'>Email:</label>");
        out.println("<input type='email' id='email' name='email' required>");
        
        out.println("<label for='telefono'>Teléfono:</label>");
        out.println("<input type='text' id='telefono' name='telefono'>");
        
        out.println("<br>");
        out.println("<button type='submit' class='btn btn-primary'>Crear Estudiante</button>");
        out.println("<a href='estudiantes' class='btn btn-secondary'>Cancelar</a>");
        
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = obtenerEstudiantePorId(id);
        
        if (estudiante == null) {
            response.sendRedirect("estudiantes");
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Editar Estudiante</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("form { max-width: 500px; }");
        out.println("input, label { display: block; margin: 5px 0; }");
        out.println("input[type='text'], input[type='email'] { width: 100%; padding: 8px; }");
        out.println(".btn { padding: 10px 20px; margin: 5px; text-decoration: none; border-radius: 3px; border: none; cursor: pointer; }");
        out.println(".btn-primary { background-color: #007bff; color: white; }");
        out.println(".btn-secondary { background-color: #6c757d; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>Editar Estudiante</h1>");
        out.println("<a href='/exam_war_exploded/' class='btn btn-secondary'>← Volver al Inicio</a>");
        out.println("<a href='estudiantes' class='btn btn-secondary'>← Ver Estudiantes</a>");
        out.println("<br><br>");
        out.println("<form method='post' action='estudiantes'>");
        out.println("<input type='hidden' name='action' value='actualizar'>");
        out.println("<input type='hidden' name='id' value='" + estudiante.getId() + "'>");
        
        out.println("<label for='nombre'>Nombre:</label>");
        out.println("<input type='text' id='nombre' name='nombre' value='" + estudiante.getNombre() + "' required>");
        
        out.println("<label for='apellido'>Apellido:</label>");
        out.println("<input type='text' id='apellido' name='apellido' value='" + estudiante.getApellido() + "' required>");
        
        out.println("<label for='email'>Email:</label>");
        out.println("<input type='email' id='email' name='email' value='" + estudiante.getEmail() + "' required>");
        
        out.println("<label for='telefono'>Teléfono:</label>");
        out.println("<input type='text' id='telefono' name='telefono' value='" + estudiante.getTelefono() + "'>");
        
        out.println("<br>");
        out.println("<button type='submit' class='btn btn-primary'>Actualizar Estudiante</button>");
        out.println("<a href='estudiantes' class='btn btn-secondary'>Cancelar</a>");
        
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    private void crearEstudiante(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        
        String sql = "INSERT INTO estudiantes (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, email);
            pstmt.setString(4, telefono);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("estudiantes");
    }

    private void actualizarEstudiante(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, email=?, telefono=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, email);
            pstmt.setString(4, telefono);
            pstmt.setInt(5, id);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("estudiantes");
    }

    private void eliminarEstudiante(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        String sql = "DELETE FROM estudiantes WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("estudiantes");
    }

    private List<Estudiante> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes ORDER BY id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setEmail(rs.getString("email"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiantes.add(estudiante);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return estudiantes;
    }

    private Estudiante obtenerEstudiantePorId(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setEmail(rs.getString("email"));
                estudiante.setTelefono(rs.getString("telefono"));
                return estudiante;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
