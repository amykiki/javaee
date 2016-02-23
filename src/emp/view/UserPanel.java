package emp.view;

import emp.model.EmpException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/2/2.
 */
public class UserPanel extends JPanel {
    private JLabel  jlb;
    private JButton jb1, jb2, jb3;
    private JPanel jp1, jp2, jp3;
    private JTable      jt;
    private JScrollPane jsp;
    private UserTable   tbModel;
    private buttonClick btClick;
    private MyDialog    addDialog;
    private MyDialog    upDialog;
    public final static int USER_COL = 4;

    public UserPanel() {
        this.setLayout(new BorderLayout());

        jlb = new JLabel("用户信息表");
        jp1 = new JPanel();
        jp1.add(jlb);
        this.add(jp1, BorderLayout.NORTH);

        tbModel = new UserTable();
        jt = new JTable(tbModel);
        jt.setRowHeight(25);
        jsp = new JScrollPane(jt);
        jsp.getViewport().setPreferredSize(new Dimension(650, 450));
        jp2 = new JPanel();
        jp2.add(jsp);
        this.add(jp2, BorderLayout.CENTER);


        btClick = new buttonClick();
        jb1 = new JButton("增加用户");
        jb1.addActionListener(btClick);
        jb2 = new JButton("更改用户");
        jb2.addActionListener(btClick);
        jb3 = new JButton("删除用户");
        jb3.addActionListener(btClick);
        jp3 = new JPanel();
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        this.add(jp3, BorderLayout.SOUTH);

        addDialog = new MyDialog("Add");
        upDialog = new MyDialog("Update");

    }

    private class buttonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb1) {
                addUser();

            } else if (e.getSource() == jb2) {
                updateUser();

            } else if (e.getSource() == jb3) {
                delUser();
            }

        }
    }

    private void addUser() {
        addDialog.setVisible(true);
    }

    private void updateUser() {
        int count = jt.getSelectedRowCount();
        if (count == 0) {
            return;
        } else if (count > 1) {
            showMsg("Only Update one User one Time");
            jt.clearSelection();
            return;
        }
        int      row  = jt.getSelectedRow();
        Object[] data = new Object[USER_COL];
        for (int i = 0; i < data.length; i++) {
            data[i] = jt.getValueAt(row, i);
        }
        upDialog.setUser(data, row);
        upDialog.setVisible(true);
    }

    private void delUser() {
        int[] rows = jt.getSelectedRows();
        int count = 0;
        for (int row : rows) {
            row -= count;
            tbModel.removeRow(row);
            count++;
        }

    }

    private class MyDialog extends JDialog {
        private JPanel jp1, jp2, jp3, jp4, jpp;
        private JLabel jl1, jl2, jl3, jlp;
        private JTextField jtf1, jtf2, jtf3;
        private JPasswordField jpf;
        private JButton        jb1, jb2;
        private btnListen btnClicked;
        private String    action;
        private Object[]  tbData;
        private int       selectRow;

        public MyDialog(String action) {
            this.action = action;
            selectRow = -1;
            initial(action);
            if (action.equalsIgnoreCase("add")) {
                this.setTitle("Add an new user");
            } else {
                this.setTitle("Update User...");
                jtf1.setEditable(false);
            }
        }


        private void initial(String action) {
            this.setLayout(new GridLayout(5, 1));
            this.setLocationRelativeTo(UserPanel.this);

            this.setSize(500, 300);
            this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            jl1 = new JLabel("name    :");
            jtf1 = new JTextField(25);
            jp1 = new JPanel();
            jp1.add(jl1);
            jp1.add(jtf1);

            jlp = new JLabel("password:");
            jpf = new JPasswordField(25);
            jpp = new JPanel();
            jpp.add(jlp);
            jpp.add(jpf);

            jl2 = new JLabel("nickname:");
            jtf2 = new JTextField(25);
            jp2 = new JPanel();
            jp2.add(jl2);
            jp2.add(jtf2);

            jl3 = new JLabel("salary  :");
            jtf3 = new JTextField(25);
            jp3 = new JPanel();
            jp3.add(jl3);
            jp3.add(jtf3);

            this.add(jp1);
            this.add(jpp);
            this.add(jp2);
            this.add(jp3);

            btnClicked = new btnListen();
            jp4 = new JPanel();
            if (action.equalsIgnoreCase("add")) {
                jb1 = new JButton("Add User");
            } else {
                jb1 = new JButton("Update User");
            }
            jb1.addActionListener(btnClicked);
            jb2 = new JButton("Reset Data");
            jb2.addActionListener(btnClicked);
            jp4.add(jb1);
            jp4.add(jb2);
            this.add(jp4);
        }

        private class btnListen implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jb1) {
                    String[] data = getUserInfo();
                    if (data != null) {
                        if (action.equalsIgnoreCase("add")) {
                            addUser(data);
                        } else {
                            updateUser(data);
                        }
                    }
                } else if (e.getSource() == jb2) {
                    resetUser();
                }

            }
        }

        private String[] getUserInfo() {
            String[] data = new String[USER_COL];
            data[0] = jtf1.getText().trim();
            data[1] = new String(jpf.getPassword());
            data[2] = jtf2.getText().trim();
            data[3] = jtf3.getText().trim();

            String[] names = new String[]{"name", "password", "nickname", "salary"};
            String   msg   = "";
            int      count = 0;
            for (int i = 0; i < names.length; i++) {
                if (data[i] == null || data[i].equals("")) {
                    if (count == 0) {
                        msg = names[i];
                    } else {
                        msg += ", " + names[i];
                    }
                    count++;
                }
            }
            if (!msg.equals("")) {
                if (count > 1) {
                    msg += " are Empty, re-input";
                } else {
                    msg += " is Empty, re-input";
                }
                showMsg(msg);
                return null;
            }
            if (!data[0].matches("^[a-zA-z].*")) {
                showMsg("name must begin with letters");
                return null;
            }
            if (!data[3].matches("^[1-9][0-9]*\\.?[0-9]*$")) {
                showMsg("Salary must be numbers");
                return null;
            }
            return data;

        }

        private void addUser(String[] data) {
            try {
                if (tbModel.addRowByTable(data)) {
                    resetUser();
                    MyDialog.this.setVisible(false);
                } else {
                    showMsg("user " + data[0] + " has already be added");
                    return;
                }
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }


        }

        private void updateUser(String[] data) {
            try {
                tbModel.updateRowByTable(data, selectRow);
                MyDialog.this.setVisible(false);
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }
        }


        private void resetUser() {
            if (action.equalsIgnoreCase("add")) {
                jtf1.setText("");
                jpf.setText("");
                jtf2.setText("");
                jtf3.setText("");
            } else {
                setUserInfo();
            }
        }

        private void setUserInfo() {
            jtf1.setText(tbData[0].toString());
            jpf.setText(tbData[1].toString());
            jtf2.setText(tbData[2].toString());
            jtf3.setText(tbData[3].toString());
        }

        public void setUser(Object[] data, int row) {
            this.tbData = data;
            this.selectRow = row;
            setUserInfo();
        }

    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
