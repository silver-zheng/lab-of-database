package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JinhuoTable {
	private final StringProperty col_regid;
	private final StringProperty col_patname;
	private final StringProperty col_regtime;
	private final StringProperty col_regtype;
	
	public JinhuoTable(String col_regid,String col_patname,String col_regtime,String col_regtype) {
        this.col_regid = new SimpleStringProperty(this, "col_regid", col_regid);
        this.col_patname = new SimpleStringProperty(this,"col_patname", col_patname);
        this.col_regtime = new SimpleStringProperty(this,"col_regtime",col_regtime);
        this.col_regtype = new SimpleStringProperty(this,"col_regtype",col_regtype);
    }

    public final String getcol_regid() {
        return this.col_regid.get();
    }
    public final void setcol_regid(String value) {
        this.col_regid.set(value);
    }
    public final StringProperty col_regidProperty() {
        return this.col_regid;
    }

    public final String getcol_patname() {
        return this.col_patname.get();
    }
    public final void setcol_patname(String value) {
        this.col_patname.set(value);
    }
    public final StringProperty col_patnameProperty() {
        return this.col_patname;
    }
    
    public final String getcol_regtime() {
        return this.col_regtime.get();
    }
    public final void setcol_regtime(String value) {
        this.col_regtime.set(value);
    }
    public final StringProperty col_regtimeProperty() {
        return this.col_regtime;
    }
    
    public final String getcol_regtype() {
        return this.col_regtype.get();
    }
    public final void setcol_regtype(String value) {
        this.col_regtype.set(value);
    }
    public final StringProperty col_regtypeProperty() {
        return this.col_regtype;
    }
}
