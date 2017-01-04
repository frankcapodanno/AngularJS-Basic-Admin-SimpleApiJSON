/*
 * Copyright (C) 2017 Francesco Capodanno <francesco@capodanno.click>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package click.capodanno.simpleapijson.servlets;

import click.capodanno.simpleapijson.entities.Alerts;
import click.capodanno.simpleapijson.session.AlertsFacade;
import click.capodanno.simpleapijson.session.LineChartDataFacade;
import click.capodanno.simpleapijson.session.NotificationsFacade;
import click.capodanno.simpleapijson.session.PieChartDataFacade;
import click.capodanno.simpleapijson.session.ProductsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Francesco Capodanno <francesco@capodanno.click>
 */
@WebServlet(name = "GetFromDB", urlPatterns = {"/get-json-from-db"})
public class GetFromDB extends HttpServlet {
    
    @EJB
    private AlertsFacade alertsFacade;
    
    @EJB
    private NotificationsFacade notificationsFacade;
    
    @EJB
    private ProductsFacade productsFacade;
    
    @EJB
    private LineChartDataFacade lineChartFacade;
    
    @EJB
    private PieChartDataFacade pieChartFacade;
    
    @Override
    public void init() throws ServletException{
        getServletContext().setAttribute("alerts", alertsFacade.findAll());
    }

    /**
     * Processes requests HTTP <code>GET</code> 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
           List<List<String>> key = new ArrayList<>();
           List<Object> value = new ArrayList<>();
           /*
           *normally I use Gson or Jackson library for convertion from entinties to json.
           *but in this example is not necessary
           */ 
           JsonParserSimple parser;
         
          if (!(request.getParameter("data") == null))
          {    
          switch (request.getParameter("data")){
              case "linegraph":
                    /*
          EXAMPLE OF functional use of a Web API resource. This feature is 
          usable when for example is obvious where the data is. If for example
          for any table per user there is the same data at the same position it
          is possibile to simplify the request. Also this is usable when it is
          not important to parse in JSON becouse the data extracted is yet parsed.
          */
                  out.println(lineChartFacade.findAll().get(0).getData());
                  break;
              case "piegraph":
                  System.out.println("piegraph called!");
                  key.clear();
                  value.clear();
                  for (int i = 0; i < pieChartFacade.findAll().size(); i++)
                  {
                      List<String> tm = new ArrayList<>();
                      tm.add("\"key\"");
                      value.add(pieChartFacade.findAll().get(i).getKKey());
                      tm.add("\"y\"");
                      value.add(pieChartFacade.findAll().get(i).getY()); 
                      key.add(tm);
                  }
                  parser = new JsonParserSimple(key, value);  
                  out.println(parser.getResult());
                  break;
              default :
                  out.print("[]");
                  break;
          }
          
          /*
          Is possible to simplify this code using an abstractFacade class and a private method. 
          But here I want to explain clearly how work this simple servlet
          */
          
          } else if (!(request.getParameter("table") == null))
          { 
          switch (request.getParameter("table")){
              case "alerts": 
                  key.clear();
                  value.clear();
                  for (int i = 0; i < alertsFacade.findAll().size(); i++)
                  {
                      List<String> tm = new ArrayList<>();
                     //tm.add("\"id\"");
                     //value.add(alertsFacade.findAll().get(i).getId());
                      tm.add("\"type\"");
                      value.add(alertsFacade.findAll().get(i).getType());
                      tm.add("\"msg\"");
                      value.add(alertsFacade.findAll().get(i).getMsg()); 
                      key.add(tm);
                  }
                  parser = new JsonParserSimple(key, value);  
                  out.println(parser.getResult());
                  break;
              case "notifications":
                 
                  key.clear();
                  value.clear();
                  
                  for(int i=0; i < notificationsFacade.findAll().size(); i++)
                  {
                      List<String> tm = new ArrayList<>();   
                      tm.add("\"type\"");
                      value.add(notificationsFacade.findAll().get(i).getType());
                      tm.add("\"num\"");
                      value.add(notificationsFacade.findAll().get(i).getNum());
                      key.add(tm);
                  }
                 parser = new JsonParserSimple(key, value);  
                 out.println(parser.getResult());
                  break;
             case "products":
                 
                 key.clear();
                  value.clear();
                  
                  for(int i=0; i < productsFacade.findAll().size(); i++)
                  {
                      List<String> tm = new ArrayList<>();   
                      tm.add("\"name\"");
                      value.add(productsFacade.findAll().get(i).getName());
                      tm.add("\"price\"");
                      value.add(productsFacade.findAll().get(i).getPrice());
                      tm.add("\"pubdate\"");
                      value.add(productsFacade.findAll().get(i).getPubdate());
                      tm.add("\"cover\"");
                      value.add(productsFacade.findAll().get(i).getCover());
                      tm.add("\"description\"");
                      value.add(productsFacade.findAll().get(i).getDescription());
                      tm.add("\"likes\"");
                      value.add(productsFacade.findAll().get(i).getLikes());
                      key.add(tm);
                  }
                 parser = new JsonParserSimple(key, value);  
                 out.println(parser.getResult());
                  break;
             default: 
                 out.print("[]");
                 break;
          }
          } else
          {
              out.print("[]");
          }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Simple servlet that create a JSON `close´ API service ";
    }// </editor-fold>
}
