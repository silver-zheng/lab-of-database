package application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IncomeTable {
	private final StringProperty col_offname;
	private final StringProperty col_docnum;
	private final StringProperty col_docname;
	private final StringProperty col_regtype2;
	private final StringProperty col_regcount;
	private final StringProperty col_income;
	
	public IncomeTable(String col_offname,String col_docnum,String col_docname,String col_regtype2,String col_regcount,String col_income) {
		this.col_offname = new SimpleStringProperty(this,"col_offname",col_offname);
		this.col_docnum = new SimpleStringProperty(this,"col_docnum",col_docnum);
		this.col_docname = new SimpleStringProperty(this,"col_docname",col_docname);
        this.col_regtype2 = new SimpleStringProperty(this, "col_regtype2", col_regtype2);
        this.col_regcount = new SimpleStringProperty(this,"peopleNum",col_regcount);
        this.col_income = new SimpleStringProperty(this,"col_income",col_income);
		
	}

    public final String getcol_offname() {
        return this.col_offname.get();
    }
    public final void setcol_offname(String value) {
        this.col_offname.set(value);
    }
    public final StringProperty col_offnameProperty() {
        return this.col_offname;
    }

    public final String getcol_docnum() {
        return this.col_docnum.get();
        
    }
    public final void setcol_docnum(String value) {
        this.col_docnum.set(value);
    }
    public final StringProperty col_docnumProperty() {
        return this.col_docnum;
    }
    
    public final String getcol_docname() {
        return this.col_docname.get();
    }
    public final void setcol_docname(String value) {
        this.col_docname.set(value);
    }
    public final StringProperty col_docnameProperty() {
        return this.col_docname;
    }
    
    public final String getcol_regtype2() {
        return this.col_regtype2.get();
    }
    public final void setcol_regtype2(String value) {
        this.col_regtype2.set(value);
    }
    public final StringProperty col_regtype2Property() {
        return this.col_regtype2;
    }
    
    public final String getcol_regcount() {
        return this.col_regcount.get();
    }
    public final void setcol_regcount(String value) {
        this.col_regcount.set(value);
    }
    public final StringProperty col_regcountProperty() {
        return this.col_regcount;
    }
    
    public final String getcol_income() {
        return this.col_income.get();
    }
    public final void setcol_income(String value) {
        this.col_income.set(value);
    }
    public final StringProperty col_incomeProperty() {
        return this.col_income;
    }
    
    
}
