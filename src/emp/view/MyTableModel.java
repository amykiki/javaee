package emp.view;

import emp.dao.UserDao;
import emp.model.User;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Created by Amysue on 2016/2/2.
 */
public class MyTableModel extends DefaultTableModel {
    private UserDao ud;

    public MyTableModel() {
        ud = new UserDao();
        this.addColumn("name");
        this.addColumn("password");
        this.addColumn("nickname");
        this.addColumn("salary");

        List<User> users = ud.loadLists();
        for (User u : users) {
            Vector data = new Vector();
            data.add(u.getName());
            data.add(u.getPassword());
            data.add(u.getNickname());
            data.add(u.getSalary());
            this.addRow(data);
        }
    }

    public boolean addRowByTable(String[] data) {
        System.out.println(Arrays.toString(data));
        User u = data2User(data);
        if (ud.addUser(u)) {
            this.addRow(data);
            return true;
        }
        return false;
    }

    public boolean updateRowByTable(String[] data, int row) {
        for (int i = 0; i < UserPanel.USER_COL; i++) {
            this.setValueAt(data[i], row, i);
        }
        User u = data2User(data);
        ud.updateUser(u);
        return true;
    }

    private User data2User(String[] data) {
        User u = new User();
        u.setName(data[0]);
        u.setPassword(data[1]);
        u.setNickname(data[2]);
        u.setSalary(Double.parseDouble(data[3]));
        return u;
    }

    @Override
    public void removeRow(int row) {
        String name = (String)getValueAt(row, 0);
        ud.delUser(name);
        super.removeRow(row);

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
