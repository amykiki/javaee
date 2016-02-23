package emp.view;

import emp.dao.EmpDao;
import emp.model.Dep;
import emp.model.Emp;
import emp.model.EmpException;
import emp.util.Gender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/2/2.
 */
public class EmpPanel extends JPanel {
    private JLabel jlb1, jlb2, jlb3;
    private EmpTable    etm;
    private JTable      jt;
    private JScrollPane jsp;
    private JButton     jb1, jb2, jb3, jbselect;
    private JPanel jp1, jp11, jp12, jp2, jp3;
    private JTextField jtf;
    private EmpDao     ed;
    private JComboBox  jcb;
    private DepCombo   dcb;
    private DepCombo   diaDcb;
    private DepPanel   dPanel;
    private MyDialog   addWin;
    private MyDialog   updateWin;

    public EmpPanel() {
        ed = new EmpDao();
        this.setLayout(new BorderLayout());

        jlb1 = new JLabel("Employee Infomation Table");
        jlb2 = new JLabel("select Dept.");
        dcb = new DepCombo(1);
        jcb = new JComboBox(dcb);
        jlb3 = new JLabel("input Name");
        jtf = new JTextField(18);
        jbselect = new JButton("Select");
        jp1 = new JPanel(new GridLayout(2, 1));
        jp11 = new JPanel();
        jp12 = new JPanel();
        jp11.add(jlb1);
        jp12.add(jlb2);
        jp12.add(jcb);
        jp12.add(jlb3);
        jp12.add(jtf);
        jp12.add(jbselect);
        jp1.add(jp11);
        jp1.add(jp12);
        this.add(jp1, BorderLayout.NORTH);

        etm = new EmpTable();
        jt = new JTable(etm);
        jt.setRowHeight(23);
        jsp = new JScrollPane(jt);
        jsp.getViewport().setPreferredSize(new Dimension(650, 400));
        jp2 = new JPanel();
        jp2.add(jsp);
        this.add(jp2, BorderLayout.CENTER);

        jb1 = new JButton("Add Employee");
        jb2 = new JButton("Update Employee");
        jb3 = new JButton("Delete Employee");
        jp3 = new JPanel();
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        this.add(jp3, BorderLayout.SOUTH);
        btnClick btnListen = new btnClick();
        jbselect.addActionListener(btnListen);
        jb1.addActionListener(btnListen);
        jb2.addActionListener(btnListen);
        jb3.addActionListener(btnListen);

        diaDcb = new DepCombo(0);
        addWin = new MyDialog("add", diaDcb);
        updateWin = new MyDialog("update", diaDcb);

    }

    public void setdPanel(DepPanel dPanel) {
        this.dPanel = dPanel;
    }

    public void refresh(Dep dep, String action) {
        if (action.equals("del")) {
            dcb.removeElement(dep);
            diaDcb.removeElement(dep);
//            addWin.getDcmDia().removeElement(dep);
//            updateWin.getDcmDia().removeElement(dep);

        } else if (action.equals("add")) {
            dcb.addElement(dep);
            diaDcb.addElement(dep);
//            addWin.getDcmDia().addElement(dep);
//            updateWin.getDcmDia().addElement(dep);
        }
    }

    public void refresh(Dep dep, Dep oldDep, String action) {
        int index = dcb.getIndexOf(oldDep);
        dcb.removeElementAt(index);
        dcb.insertElementAt(dep, index);
        index--;
        diaDcb.removeElementAt(index);
        diaDcb.insertElementAt(dep, index);
//        addWin.getDcmDia().removeElementAt(index);
//        addWin.getDcmDia().insertElementAt(dep, index);
//        updateWin.getDcmDia().removeElementAt(index);
//        updateWin.getDcmDia().insertElementAt(dep, index);

        int depid = dep.getId();
        etm.updateDep(depid, oldDep.getName());
    }

    private class btnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbselect) {
                getSelect();
            } else if (e.getSource() == jb1) {
                addDialog();
            } else if (e.getSource() == jb2) {
                updateDialog();
            } else if (e.getSource() == jb3) {
                try {
                    delEmp();
                } catch (EmpException e1) {
                    ManagerFrame.showMsg(EmpPanel.this, e1.getMessage());
                }
            }
        }
    }

    private void delEmp() throws EmpException{
        int[] rows = jt.getSelectedRows();
        if (rows.length == 0) {
            ManagerFrame.showMsg(this, "You haven't select Empoloyee.");
            return;
        }
        int count = 0;
        for (int row : rows) {
            row -= count;
            int depid = etm.removeEmp(row);
            if (depid > 0) {
                count++;
                dPanel.refresh(depid, "del");
            }
        }
    }

    private void updateDialog() {
        int[] rows = jt.getSelectedRows();
        if (rows.length == 0) {
            ManagerFrame.showMsg(this, "You haven't select Employ to Update");
            return;
        }
        if (rows.length > 1) {
            ManagerFrame.showMsg(this, "You can only Update one Employ one time");
            return;
        }
        int row    = rows[0];
        Emp oldEmp = etm.getEmp(row);
        updateWin.setUpdate(oldEmp, row);
        updateWin.setVisible(true);
    }

    private void addDialog() {
        addWin.setVisible(true);
        addWin.resetData();
    }

    private void getSelect() {
        Dep    dep   = (Dep) jcb.getSelectedItem();
        int    depid = dep.getId();
        String name  = jtf.getText();
        etm.search(depid, name);
    }

    private class MyDialog extends JDialog {
        private JPanel jpId, jpName, jpGender, jpSalary, jpDepid, jpBtn;
        private JLabel jlbId, jlbName, jlbGender, jlbSalary, jlbDepid;
        private JComboBox    jcbDia;
        private DepCombo     dcmDia;
        private JRadioButton jrbMale, jrbFemale;
        private ButtonGroup bgDia;
        private JTextField  jtfId, jtfName, jtfSalary;
        private JButton jbd1, jbd2;
        private Emp           oldEmp;
        private String        diaAction;
        private int           selectRow;
        private DiaBtnListner dblisner;

        public MyDialog(String diaAction, DepCombo dcmDia) {
            this.diaAction = diaAction;
            this.setModalityType(ModalityType.APPLICATION_MODAL);
            this.setLocationRelativeTo(EmpPanel.this);
            oldEmp = new Emp();
            int row    = 5;
            int width  = 400;
            int height = 300;
            this.setTitle("Add New Employee");

            jpName = new JPanel();
            jlbName = new JLabel("Name:  ");
            jtfName = new JTextField(20);
            jpName.add(jlbName);
            jpName.add(jtfName);

            jpGender = new JPanel();
            jlbGender = new JLabel("Gender:");
            jrbMale = new JRadioButton("Male");
            jrbMale.setSelected(true);
            jrbFemale = new JRadioButton("Female");
            bgDia = new ButtonGroup();
            bgDia.add(jrbMale);
            bgDia.add(jrbFemale);
            jpGender.add(jlbGender);
            jpGender.add(jrbMale);
            jpGender.add(jrbFemale);

            jpSalary = new JPanel();
            jlbSalary = new JLabel("Salary:");
            jtfSalary = new JTextField(20);
            jpSalary.add(jlbSalary);
            jpSalary.add(jtfSalary);

            jpDepid = new JPanel();
            jlbDepid = new JLabel("Dept:");
            this.dcmDia = dcmDia;
            jcbDia = new JComboBox(dcmDia);
            jpDepid.add(jlbDepid);
            jpDepid.add(jcbDia);

            jbd1 = new JButton("Add Emp");
            jbd2 = new JButton("Reset Emp");

            if (diaAction.equals("update")) {
                this.setTitle("Update Empolyee");
                row = 6;
                height = 330;
                jpId = new JPanel();
                jlbId = new JLabel("ID:    ");
                jtfId = new JTextField(20);
                jtfId.setEditable(false);
                jpId.add(jlbId);
                jpId.add(jtfId);

                jbd1 = new JButton("Update Emp");

            }
            this.setLayout(new GridLayout(row, 1));
            if (row == 6) {
                this.add(jpId);
            }
            this.add(jpName);
            this.add(jpGender);
            this.add(jpSalary);
            this.add(jpDepid);

            jpBtn = new JPanel();
            jpBtn.add(jbd1);
            jpBtn.add(jbd2);
            this.add(jpBtn);

            this.setSize(width, height);
            dblisner = new DiaBtnListner();
            jbd1.addActionListener(dblisner);
            jbd2.addActionListener(dblisner);
        }

        public void setUpdate(Emp oldEmp, int selectRow) {
            this.oldEmp = oldEmp;
            this.selectRow = selectRow;
            resetData();
        }

        public DepCombo getDcmDia() {
            return dcmDia;
        }

        private class DiaBtnListner implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jbd1) {
                    if (diaAction.equals("add")) {
                        addEmp();
                    } else if (diaAction.equals("update")) {
                        updateEmp();
                    }
                } else if (e.getSource() == jbd2) {
                    resetData();
                }
            }
        }

        private void updateEmp() {
            try {
                Emp emp = getData();
                if (emp.getDepid() != oldEmp.getDepid()) {
                    dPanel.refresh(oldEmp.getDepid(), "del");
                    dPanel.refresh(emp.getDepid(), "add");
                }
                etm.updateEmp(emp, selectRow);
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }
            this.setVisible(false);
        }

        private void addEmp() {
            try {
                Emp emp = getData();
                etm.addEmp(emp);
                dPanel.refresh(emp.getDepid(), "add");
            } catch (EmpException e) {
                ManagerFrame.showMsg(this, e.getMessage());
            }
            this.setVisible(false);
        }

        private Emp getData() throws EmpException{
            Emp emp = new Emp();
            if (diaAction.equals("update")) {
                int id = oldEmp.getId();
                emp.setId(id);
            }
            String name = jtfName.getText();
            if (!name.matches("^[a-zA-Z]\\w*(?:\\s\\w+)*")) {
                throw new EmpException("Employee name must begins with charaters");
            }
            String salaryStr = jtfSalary.getText();
            if (!salaryStr.matches("^[1-9]\\d*(?:\\.\\d+)?")) {
                throw new EmpException("Salary can only be numbers");
            }
            double salary = Double.parseDouble(salaryStr);

            emp.setName(name);
            if (jrbMale.isSelected()) {
                emp.setGender(Gender.MALE);
            } else {
                emp.setGender(Gender.FEMALE);
            }
            emp.setSalary(salary);
            Dep dep = (Dep) dcmDia.getSelectedItem();
            emp.setDepid(dep.getId());
            return emp;
        }

        private void resetData() {
            if (diaAction.equals("add")) {
                jtfName.setText("");
                jrbMale.setSelected(true);
                jtfSalary.setText("");
                jcbDia.setSelectedIndex(0);
            } else if (diaAction.equals("update")) {
                jtfId.setText(String.valueOf(oldEmp.getId()));
                jtfName.setText(oldEmp.getName());
                if (oldEmp.getGender() == Gender.MALE) {
                    jrbMale.setSelected(true);
                } else {
                    jrbFemale.setSelected(true);
                }
                jtfSalary.setText(String.valueOf(oldEmp.getSalary()));
                dcmDia.selectDep(oldEmp.getDepid());
            }
        }


    }


}
