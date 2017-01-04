package click.capodanno.simpleapijson.servlets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

/**
 * @author Francesco Capodanno <francesco@capodanno.click>
 */
public class JsonParserSimple {
    
    private StringBuilder result;
    /**
     * this is a simple parser that I created to transform entities in json string
     * normally jackson and gson provide good parsers that in many situations 
     * are more useful.
     * This parser is design to transform a sql table in json object and transfrom
     * an object Date in a ISO compatible date that is useful in frontend with AngularJS
     * @param key the name of the column in the table
     * @param value the value of the column
     */
    public JsonParserSimple(List<List<String>> key, List<Object> value)
            {
                result = new StringBuilder("[");
   
                if (key.isEmpty() || value.isEmpty())
                {
                    result = new StringBuilder("Server: " + this.getClass().getName() + " No data is passed to the parser");
                } else
                {
                   int c = 0; int i = 0;
                   for (List<String> m : key)
                   {
                     result.append("{ ");  
                     
                     i = m.size()*c; 
                     for (String str : m)
                     {    
                         result.append(str).append(": ");
                               if (value.get(i) instanceof Number)
                               {
                                   result.append(value.get(i)).append(", ");
                               } else if(value.get(i) instanceof Date)
                               {
                                   result.append("\"")
                                          .append(convertTOISODate(value.get(i)))
                                          .append("\", "); 
                               } else
                               {
                                   result.append("\"").append(value.get(i))
                                         .append("\", ");
                               }
                         i++;      
                     }
                     result.setLength(result.length() - 2);
                     result.append("}, ");
                     c++;
                   }
                   result.setLength(result.length() - 2);
                   result.append("]");
                }
            }

    public StringBuilder getResult() {
        return result;
    }   

    private String convertTOISODate(Object get) {
        Date date = (Date) get; 
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // ISO converting
        return df.format(date);
    }
}