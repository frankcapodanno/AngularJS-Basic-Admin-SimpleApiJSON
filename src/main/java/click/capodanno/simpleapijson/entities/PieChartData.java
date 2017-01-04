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
@Table(name = "pie_chart_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PieChartData.findAll", query = "SELECT p FROM PieChartData p")
    , @NamedQuery(name = "PieChartData.findById", query = "SELECT p FROM PieChartData p WHERE p.id = :id")
    , @NamedQuery(name = "PieChartData.findByKKey", query = "SELECT p FROM PieChartData p WHERE p.kKey = :kKey")
    , @NamedQuery(name = "PieChartData.findByY", query = "SELECT p FROM PieChartData p WHERE p.y = :y")})
public class PieChartData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "k_key")
    private String kKey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "y")
    private int y;

    public PieChartData() {
    }

    public PieChartData(Integer id) {
        this.id = id;
    }

    public PieChartData(Integer id, String kKey, int y) {
        this.id = id;
        this.kKey = kKey;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKKey() {
        return kKey;
    }

    public void setKKey(String kKey) {
        this.kKey = kKey;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        if (!(object instanceof PieChartData)) {
            return false;
        }
        PieChartData other = (PieChartData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "click.capodanno.simpleapijson.entities.PieChartData[ id=" + id + " ]";
    }
    
}
