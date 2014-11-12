package perceptron.visualization;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GeneralizedTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 8294113836242780567L;

	List<List<Object>> data = new ArrayList<List<Object>>();
	List<String> title = new ArrayList<String>();

	public List<List<Object>> getData() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	
	
//	@Override
//	public String getColumnName(int index) {
//		return title.get(index);
//	}

	@Override
	public int getColumnCount() {
		return title.size();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row).get(col);
	}
}
