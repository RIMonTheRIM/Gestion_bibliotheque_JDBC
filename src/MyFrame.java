import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class MyFrame extends JFrame implements ActionListener {
    //
    int selectedID = -1;
    int rowindex;

    //JTextfields
    JTextField T_libelle;
    JTextField T_auteur;
    JDateChooser T_date;
    JTextField T_code;
    //Image icons
    ImageIcon crossIcon;
    ImageIcon checkIcon;
    ImageIcon libelleIcon;
    ImageIcon auteurIcon;
    ImageIcon dateIcon;
    ImageIcon codeIcon;
    ImageIcon addIcon;
    ImageIcon modifIcon;
    ImageIcon deleteIcon;
    ImageIcon generateIcon;
    //Jbuttons
    JButton supprimer;
    JButton modifier;
    JButton ajouter;
    JButton generer;
    //checkbox
    JCheckBox dispo;
    //tablemodel
    DefaultTableModel tableModel = new DefaultTableModel();
    int table_count = 0;
    SimpleDateFormat SDFormat = new SimpleDateFormat("dd-MM-yyyy");
    MyFrame(){


        //Image icons
        crossIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cross.png")));
        checkIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/check.png")));
        libelleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/label.png")));
        auteurIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/author.png")));
        dateIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/date.png")));
        codeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/qr.png")));
        addIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/add.png")));
        modifIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/modif.png")));
        deleteIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/delete.png")));
        generateIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/generate.png")));

        //Jcheckbox
        dispo = new JCheckBox();
        dispo.setText("Disponibilité");
        dispo.setFocusable(false);
        dispo.setBounds(0,0,700,60);
        dispo.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        dispo.setBackground(new Color(37, 42, 52));
        dispo.setForeground(new Color(8, 217, 214));
        dispo.setIcon(crossIcon);
        dispo.setSelectedIcon(checkIcon);
        //Jbuttons
        generer = new JButton("Génerer");
        generer.setBounds(5,68,180,50);
        generer.setFocusable(false);
        generer.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        generer.setMargin(new Insets(0,0,0,0));
        generer.setBackground(new Color(255, 46, 99));
        generer.setForeground(new Color(37, 42, 52));
        generer.setBorder(null);
        generer.addActionListener(this);
        generer.setIcon(generateIcon);

        ajouter = new JButton("Ajouter");
        ajouter.setBounds(0,68,160,50);
        ajouter.setFocusable(false);
        ajouter.setFont(new Font("Clash Grotesk", Font.BOLD,27));
        ajouter.setMargin(new Insets(0,0,0,0));
        ajouter.setBackground(new Color(255, 46, 99));
        ajouter.setBorder(null);
        ajouter.setForeground(new Color(37, 42, 52));
        ajouter.setIcon(addIcon);
        ajouter.addActionListener(this);

        modifier = new JButton("Modifier");
        modifier.setBounds(175,68,160,50);
        modifier.setFocusable(false);
        modifier.setFont(new Font("Clash Grotesk", Font.BOLD,27));
        modifier.setMargin(new Insets(0,0,0,0));
        modifier.setBackground(new Color(255, 46, 99));
        modifier.setBorder(null);
        modifier.setForeground(new Color(37, 42, 52));
        modifier.setIcon(modifIcon);
        modifier.addActionListener(this);

        supprimer = new JButton("Supprimer");
        supprimer.setBounds(350,68,200,50);
        supprimer.setFocusable(false);
        supprimer.setFont(new Font("Clash Grotesk", Font.BOLD,27));
        supprimer.setMargin(new Insets(0,0,0,0));
        supprimer.setBackground(new Color(255, 46, 99));
        supprimer.setBorder(null);
        supprimer.setForeground(new Color(37, 42, 52));
        supprimer.setIcon(deleteIcon);
        supprimer.addActionListener(this);

        //textfields
        T_libelle = new JTextField();
        T_libelle.setBounds(0,0,550,55);
        T_libelle.setFont(new Font("Clash Grotesk", Font.BOLD,30));

        T_auteur = new JTextField();
        T_auteur.setBounds(0,0,550,55);
        T_auteur.setFont(new Font("Clash Grotesk", Font.BOLD,30));

        T_date = new JDateChooser();
        T_date.setDateFormatString("dd-MM-yyyy");
        T_date.setBounds(0,0,550,55);
        T_date.getJCalendar().setBounds(0,0,500,500);
        T_date.getJCalendar().getMonthChooser().setPreferredSize(new Dimension(200,50));
        T_date.getJCalendar().setForeground(new Color(37, 42, 52));
        T_date.getJCalendar().getDayChooser().setDecorationBackgroundColor(new Color(8, 217, 214));
        T_date.getJCalendar().getDayChooser().getDayPanel().setBackground(new Color(8, 217, 214));
        T_date.setFont(new Font("Clash Grotesk", Font.BOLD,30));

        T_code = new JTextField();
        T_code.setBounds(0,70,550,55);
        T_code.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        T_code.setEditable(false);
        T_code.setBackground(Color.gray);

        //labels
        JLabel l_libelle = new JLabel("",SwingConstants.LEADING);
        l_libelle.setText("Libellé");
        l_libelle.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        l_libelle.setForeground(new Color(8, 217, 214));
        l_libelle.setOpaque(true);
        l_libelle.setBackground(new Color(37, 42, 52));
        l_libelle.setBounds(0,68,200,60);
        l_libelle.setIcon(libelleIcon);


        JLabel l_auteur = new JLabel("",SwingConstants.LEADING);
        l_auteur.setText("Auteur");
        l_auteur.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        l_auteur.setForeground(new Color(8, 217, 214));
        l_auteur.setOpaque(true);
        l_auteur.setBackground(new Color(37, 42, 52));
        l_auteur.setBounds(0,68,200,60);
        l_auteur.setIcon(auteurIcon);

        JLabel l_date = new JLabel("",SwingConstants.LEADING);
        l_date.setText("Date de publication");
        l_date.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        l_date.setForeground(new Color(8, 217, 214));
        l_date.setOpaque(true);
        l_date.setBackground(new Color(37, 42, 52));
        l_date.setBounds(0,68,400,60);
        l_date.setIcon(dateIcon);

        JLabel l_code = new JLabel("",SwingConstants.LEADING);
        l_code.setText("Code Barre");
        l_code.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        l_code.setForeground(new Color(8, 217, 214));
        l_code.setOpaque(true);
        l_code.setBackground(new Color(37, 42, 52));
        l_code.setBounds(0,10,300,60);
        l_code.setIcon(codeIcon);

        JLabel l_titre = new JLabel("", SwingConstants.LEADING);
        l_titre.setText("Gestion des emprunts");
        l_titre.setFont(new Font("Clash Grotesk", Font.BOLD,30));
        l_titre.setForeground(new Color(37, 42, 52));
        l_titre.setOpaque(true);
        l_titre.setBackground(new Color(8, 217, 214));
        l_titre.setBounds(0,0,700,60);

        JLabel l_message = new JLabel("<html>Double clic pour sélectionner<br/> une ligne du tableau!!</html>",SwingConstants.LEADING);
        l_message.setFont(new Font("Clash Grotesk", Font.BOLD,25));
        l_message.setForeground(Color.white);
        l_message.setOpaque(true);
        l_message.setBackground(new Color(37, 42, 52));
        l_message.setBounds(0,0,700,60);

        //panels
        JPanel left0 = new JPanel();
        left0.add(l_titre);
        left0.setBackground(new Color(37, 42, 52));
        left0.setLayout(null);
        left0.add(l_libelle);

        JPanel left1 = new JPanel();
        left1.setBackground(new Color(37, 42, 52));
        left1.setLayout(null);
        left1.add(T_libelle);
        left1.add(l_auteur);

        JPanel left2 = new JPanel();
        left2.setBackground(new Color(37, 42, 52));
        left2.setLayout(null);
        left2.add(T_auteur);
        left2.add(l_date);

        JPanel left3 = new JPanel();
        left3.setBackground(new Color(37, 42, 52));
        left3.setLayout(null);
        left3.add(T_date);
        left3.add(generer);

        JPanel left4 = new JPanel();
        left4.add(l_code);
        left4.setBackground(new Color(37, 42, 52));
        left4.setLayout(null);
        left4.add(T_code);

        JPanel left5 = new JPanel();
        left5.setLayout(null);
        left5.setBackground(new Color(37, 42, 52));
        left5.add(dispo);
        left5.add(ajouter);
        left5.add(modifier);
        left5.add(supprimer);

        JPanel left6 = new JPanel();
        left6.setLayout(null);
        left6.setBackground(new Color(37, 42, 52));
        left6.add(l_message);

        //panel input left
        JPanel p_input = new JPanel();
        p_input.setPreferredSize(new Dimension(550, 900));
        p_input.setBackground(new Color(37, 42, 52));
        p_input.add(left0);
        p_input.add(left1);
        p_input.add(left2);
        p_input.add(left3);
        p_input.add(left4);
        p_input.add(left5);
        p_input.add(left6);
        p_input.setLayout(new GridLayout(7,1));

        //Jtable

        JTable table = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Libellé");
        tableModel.addColumn("Auteur");
        tableModel.addColumn("Date Publication");
        tableModel.addColumn("Code Barre");
        tableModel.addColumn("Disponibilité");
        JScrollPane sp = new JScrollPane(table);

        DBConnect db1 = new DBConnect();
        ArrayList<Livre> livres = db1.peupler();
        if(livres.isEmpty()){
            System.out.println("empty database");
        }
        else {
            for(Livre x:livres){
                String[] tab = {String.valueOf(x.getID()),x.getLibelle(),x.getAuteur(),x.getDate(),x.getCode(), String.valueOf(x.getDisponible())};
                System.out.println(Arrays.toString(tab));
                tableModel.addRow(tab);
                table_count =x.getID();
                System.out.println(table_count);
            }
        }

//        tableModel.addRow(tab_Table2);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(255, 46, 99));
        tableHeader.setForeground(new Color(37, 42, 52));
        tableHeader.setFont(new Font("Clash Grotesk", Font.BOLD,25));//06bebb

        table.setFont(new Font("Serif", Font.BOLD, 20));
        table.setBackground(new Color(37, 42, 52));
        table.setForeground(new Color(8, 217, 214));
        table.setFont(new Font("Clash Grotesk", Font.BOLD,25));
        table.setRowHeight(40);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setOpaque(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(!event.getValueIsAdjusting()){
                    rowindex = table.getSelectedRow();
                    System.out.println("index " +rowindex);
                    selectedID = Integer.parseInt(table.getValueAt(rowindex, 0).toString()) ;
                    System.out.println("selected ID = "+selectedID);

                    String selectedLib =  table.getValueAt(table.getSelectedRow(), 1).toString();
                    T_libelle.setText(selectedLib);

                    String selectedAut =  table.getValueAt(table.getSelectedRow(), 2).toString();
                    T_auteur.setText(selectedAut);

                    Date selectedDate = null;
                    try {
                        selectedDate = new SimpleDateFormat("dd-MM-yyyy").parse(table.getValueAt(table.getSelectedRow(), 3).toString());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    T_date.setDate(selectedDate);

                    String selectedCode =  table.getValueAt(table.getSelectedRow(), 4).toString();
                    T_code.setText(selectedCode);

                    dispo.setSelected(Boolean.parseBoolean(table.getValueAt(table.getSelectedRow(), 5).toString()));

                }
            }
        });

        //panel table right
        JPanel p_table = new JPanel();
        p_table.setPreferredSize(new Dimension(1200, 900));
        p_table.setBackground(Color.blue);
        p_table.add(sp);
        p_table.setLayout(new GridLayout(1,1));

        //frame
        this.setTitle("Database usage");//changes title
        this.setSize(1800,960);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(p_input);
        this.add(p_table);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(37, 42, 52));
        this.setTitle("Library Manager");
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        ImageIcon image = new ImageIcon("src/book.png");//creates new image icon object
        this.setIconImage(image.getImage());//change icon of frame to icon
        this.setVisible(true);//make frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==generer){
            System.out.println("bouton générer");
            String format = SDFormat.format(T_date.getDate());
            System.out.println(format);
            String[] dateSep = format.split("-");
            System.out.println("année "+ dateSep[2]);
            String  genere = T_libelle.getText().charAt(0) + String.valueOf(T_libelle.getText().charAt(1)) + T_auteur.getText().charAt(T_auteur.getText().length()-2) + T_auteur.getText().charAt(T_auteur.getText().length()-1)+dateSep[2];
            T_code.setText(genere);
            System.out.println("généré");
        }
        if(e.getSource()==ajouter){
            Livre l1 = new Livre();
            int error = 0;
            System.out.println("ajouterr");
            l1.setLibelle(T_libelle.getText());
            if(l1.getLibelle()==null){
                error+=1;
            }
            l1.setAuteur(T_auteur.getText());
            if(l1.getAuteur()==null){
                error+=1;
            }
            l1.setDate(SDFormat.format(T_date.getDate()));
            if(l1.getDate()==null){
                error+=1;
            }
            l1.setCode(T_code.getText());
            if(l1.getCode()==null){
                error+=1;
            }
            if(error>0){
                //error message
                System.out.println("error");
            }
            else {
                table_count += 1;
                l1.setID(table_count);
                l1.setCode(T_code.getText());
                l1.setDisponible(dispo.isSelected());
                String[] tab = {String.valueOf(l1.getID()),l1.getLibelle(),l1.getAuteur(),l1.getDate(),l1.getCode(), String.valueOf(l1.getDisponible())};
                tableModel.addRow(tab);//add to table

                DBConnect dbConnect = new DBConnect();
                dbConnect.ajouter(l1);//add to database

            }
        }
        if(e.getSource()==supprimer){
            System.out.println("supprimer cmd");
        }
        if(e.getSource()==modifier){
            if(selectedID != -1){
                Livre l1 = new Livre();
                l1.setID(selectedID);
                System.out.println(l1.getID()+ "= id");
                l1.setLibelle(T_libelle.getText());
                l1.setAuteur(T_auteur.getText());
                l1.setDate(SDFormat.format(T_date.getDate()));
                l1.setCode(T_code.getText());
                l1.setDisponible(dispo.isSelected());

                tableModel.setValueAt(T_libelle.getText(),rowindex,1);
                tableModel.setValueAt(T_auteur.getText(),rowindex,2);
                tableModel.setValueAt(SDFormat.format(T_date.getDate()),rowindex,3);
                tableModel.setValueAt(T_code.getText(),rowindex,4);
                tableModel.setValueAt(dispo.isSelected(),rowindex,5);

                DBConnect dbConnect = new DBConnect();
                dbConnect.modifier(l1);
            }
        }
    }
}

