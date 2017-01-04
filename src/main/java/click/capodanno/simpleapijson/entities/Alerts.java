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
package click.capodanno.simpleapijson.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francesco Capodanno <francesco@capodanno.click>
 */
@Entity
@Table(name = "alerts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alerts.findAll", query = "SELECT a FROM Alerts a")
    , @NamedQuery(name = "Alerts.findById", query = "SELECT a FROM Alerts a WHERE a.id = :id")
    , @NamedQuery(name = "Alerts.findByType", query = "SELECT a FROM Alerts a WHERE a.type = :type")
    , @NamedQuery(name = "Alerts.findByMsg", query = "SELECT a FROM Alerts a WHERE a.msg = :msg")})
public class Alerts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "msg")
    private String msg;

    public Alerts() {
    }

    public Alerts(Integer id) {
        this.id = id;
    }

    public Alerts(Integer id, String type, String msg) {
        this.id = id;
        this.type = type;
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alerts)) {
            return false;
        }
        Alerts other = (Alerts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "click.capodanno.simpleapijson.entities.Alerts[ id=" + id + " ]";
    }
    
}
