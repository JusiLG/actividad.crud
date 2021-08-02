package mx.edu.utez.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import mx.edu.utez.model.pelicula.BeanPelicula;
import mx.edu.utez.model.pelicula.DaoPelicula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet(name = "ServletPelicula", value = "/ServletPelicula")
public class ServletPelicula extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ServletPelicula.class); //dentro de los () se pone la clase

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listPelicula", new DaoPelicula().findAll());
        request.getRequestDispatcher("/views/user/peliculas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                // do something
                String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String descripcion = request.getParameter("descripcion");
                String fecha_estreno = request.getParameter("fecha_estreno");
                int recaudacion = Integer.parseInt(request.getParameter("recaudacion"));
                int estado = Integer.parseInt(request.getParameter("estado"));

                BeanPelicula beanpelicula = new BeanPelicula(0, nombre, descripcion, fecha_estreno, recaudacion, estado);


                if (new DaoPelicula().create(beanpelicula)) {
                    request.setAttribute("message", "Pelicula registrado correctamente");
                } else {
                    request.setAttribute("message", "Pelicula no registrado");
                }

                doGet(request, response);
                break;
            case "getUserById":
                // do something
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("user", new DaoPelicula().findById(id));
                request.getRequestDispatcher("/views/user/update.jsp").forward(request, response);
                break;
            case "update":

                int id1 = Integer.parseInt(request.getParameter("id"));
                String nombre1 = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
                String descripcion1 = request.getParameter("descripcion");
                String fecha_estreno1 = request.getParameter("fecha_estreno");
                int recaudacion1 = Integer.parseInt(request.getParameter("recaudacion"));
                int estado1 = Integer.parseInt(request.getParameter("estado"));

                BeanPelicula beanPelicula1 = new BeanPelicula(id1, nombre1, nombre1, fecha_estreno1, recaudacion1, estado1);

                if (new DaoPelicula().create(beanPelicula1)) {
                    request.setAttribute("message", "Pelicula Modificada");
                } else {
                    request.setAttribute("message", "Pelicula no Modificada");
                }

                doGet(request, response);
                break;
            case "delete":
                int id2 = Integer.parseInt(request.getParameter("id"));
                if (new DaoPelicula().delete(id2)) {
                    request.setAttribute("message", "Pelicula eliminada correctamente");
                } else {
                    request.setAttribute("message", "Pelicula no eliminada");
                }
                doGet(request, response);
                break;
            default:
                break;
        }
    }
}