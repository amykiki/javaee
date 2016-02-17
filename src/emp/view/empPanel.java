package emp.view;

import emp.dao.EmpDao;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Amysue on 2016/2/2.
 */
public class empPanel extends JPanel {
    private JLabel      jlb1, jlb2, jlb3;
    private empTable    etm;
    private JTable      jt;
    private JScrollPane jsp;
    private JButton     jb1, jb2, jb3, jbselect;
    private JPanel jp1, jp11, jp12, jp2, jp3;
    private JTextField jtf;
    private EmpDao ed;
    private JComboBox jcb;
    private depCombo dcb;

    public empPanel() {
        ed = new EmpDao();
        this.setLayout(new BorderLayout());

        jlb1 = new JLabel("Employee Infomation Table");
        jlb2 = new JLabel("select Dept.");
        dcb = new depCombo(1);
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

        etm = new empTable();
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
    }
}
