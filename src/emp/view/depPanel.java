package emp.view;

import emp.model.Dep;
import emp.model.EmpException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/2/2.
 */
public class DepPanel extends JPanel {
    private JLabel      jlb;
    private DepTable    dtm;
    private JTable      jt;
    private JScrollPane jsp;
    private JButton     jb1, jb2, jb3;
    private JPanel jp1, jp2, jp3;
    private MyDialog addDialog;
    private MyDialog upDialog;
    private EmpPanel ePanel;

    public DepPanel() {
        this.setLayout(new BorderLayout());

        jlb = new JLabel("Department Infomation Table");
        jp1 = new JPanel();
        jp1.add(jlb);
        this.add(jp1, BorderLayout.NORTH);

        dtm = new DepTable();
        jt = new JTable(dtm);
        jt.setRowHeight(23);
        jsp = new JScrollPane(jt);
        jsp.getViewport().setPreferredSize(new Dimension(650, 450));
        jp2 = new JPanel();
        jp2.add(jsp);
        this.add(jp2, BorderLayout.CENTER);

        jb1 = new JButton("Add Department");
        jb2 = new JButton("Update Department");
        jb3 = new JButton("Delete Department");
        jp3 = new JPanel();
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        BtnListener btnlistener = new BtnListener();
        jb1.addActionListener(btnlistener);
        jb2.addActionListener(btnlistener);
        jb3.addActionListener(btnlistener);
        this.add(jp3, BorderLayout.SOUTH);

        addDialog = new MyDialog("add");
        upDialog = new MyDialog("update");
    }

    public void setePanel(EmpPanel ePanel) {
        this.ePanel = ePanel;
    }

    public void refresh(int depid, String action) {
        if (action.equals("del")) {
            dtm.updatePepCount(depid, "del");
        } else if (action.equals("add")) {
            dtm.updatePepCount(depid, "add");
        }
    }

    private class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb1) {
                addDep();
            } else if (e.getSource() == jb2) {
                updateDep();
            } else if (e.getSource() == jb3) {
                delDep();
            }
        }
    }

    private void delDep() {
        int[] rows = jt.getSelectedRows();
        if (rows.length == 0) {
            ManagerFrame.showMsg(this, "You haven't select Depts.");
            return;
        }
        int count = 0;
        for (int row : rows) {
            row -= count;
            try {
                Dep dep = dtm.removeDep(row);
                if (dep != null) {
                    count++;
                    ePanel.refresh(dep, "del");
                }
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }
        }
    }

    private void updateDep() {
        int[] rows = jt.getSelectedRows();
        if (rows.length == 0) {
            ManagerFrame.showMsg(this, "You haven't select Depts to Update");
            return;
        }
        if (rows.length > 1) {
            ManagerFrame.showMsg(this, "You can only Update one Dept one time");
            return;
        }
        int row    = rows[0];
        Dep oldDep = dtm.data2Dep(row);
        upDialog.setUpdate(oldDep, row);
        upDialog.showOldDep();
        upDialog.setVisible(true);
    }

    private void addDep() {
        addDialog.setVisible(true);
        addDialog.resetData();
    }

    private class MyDialog extends JDialog {
        private JPanel jp1, jp2, jp3;
        private JButton jbt1, jbt2;
        private JLabel jlb1, jlb2;
        private JTextField jtf1, jtf2;
        private Dep    oldDep;
        private String action;
        private int    selectRow;

        public MyDialog(String action) {
            this.action = action;
            this.setModalityType(ModalityType.APPLICATION_MODAL);
            this.setLocationRelativeTo(DepPanel.this);
            oldDep = new Dep();
            int row    = 2;
            int width  = 350;
            int height = 200;
            this.setTitle("Add New Dept.");
            jbt1 = new JButton("Add Dept");
            jbt2 = new JButton("Reset Dept");
            if (action.equals("update")) {
                this.setTitle("Update Dept.");
                jbt1 = new JButton("Update Dept");
                jlb1 = new JLabel("ID:  ");
                jtf1 = new JTextField(20);
                jtf1.setEditable(false);
                jp1 = new JPanel();
                jp1.add(jlb1);
                jp1.add(jtf1);
                row = 3;
                width = 350;
                height = 250;
            }
            jlb2 = new JLabel("Name:");
            jtf2 = new JTextField(20);
            jp2 = new JPanel();
            jp2.add(jlb2);
            jp2.add(jtf2);

            jp3 = new JPanel();
            jp3.add(jbt1);
            jp3.add(jbt2);
            this.setLayout(new GridLayout(row, 1));
            if (row == 3) {
                this.add(jp1);
            }
            this.add(jp2);
            this.add(jp3);
            diaListener lis = new diaListener();
            jbt1.addActionListener(lis);
            jbt2.addActionListener(lis);
            this.setSize(width, height);
        }

        public void setUpdate(Dep oldDep, int row) {
            this.oldDep = oldDep;
            this.selectRow = row;
        }

        public void showOldDep() {
            jtf1.setText(String.valueOf(oldDep.getId()));
            jtf2.setText(oldDep.getName());
        }

        public void resetData() {
            if (action.equals("add")) {
                jtf2.setText("");
            } else {
                jtf2.setText(oldDep.getName());
            }
        }

        private String getInfo() throws EmpException{
            String str = jtf2.getText();
            if (!str.matches("^[a-zA-Z]\\w*(?:\\s\\w+)*")) {
                throw new EmpException("Dept name must begins with charaters");
            }
            return str;
        }

        private void addDep() {
            Dep dep = null;
            try {
                String name = getInfo();
                dep = dtm.addDep(name);
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }
            if (dep != null) {
                ePanel.refresh(dep, "add");
            }
            resetData();
            return;
        }

        private void updateDep() {
            Dep dep = null;
            try {
                String name = getInfo();
                dep = dtm.updateDep(selectRow, name);
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }
            if (dep != null) {
                ePanel.refresh(dep, oldDep, "update");
            }
            resetData();
            this.setVisible(false);
            return;
        }

        private class diaListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jbt1) {
                    if (MyDialog.this.action.equals("add")) {
                        addDep();
                    } else {
                        updateDep();
                    }
                } else if (e.getSource() == jbt2) {
                    resetData();
                }
            }
        }
    }

}
