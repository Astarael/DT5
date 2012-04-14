import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/* Four442Gui.java
 * By Samuel Baxter
 * COMP2911
 * 07/04/2012
 */

public class Four442Gui extends JFrame implements ActionListener {

    // what the hell is this?
    private static final long serialVersionUID = 1L;

    private static int NUM_INPUTS = 16;
    
    private JButton start;
    private JButton reset;
    private JLabel output;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel buttonPanel;
    private JPanel ioPanel;
    private JFormattedTextField input[];
    private Four442 four442;
    
    
    public Four442Gui() {
        
        four442 = new Four442();
        
        // initialise window
        setTitle("4442");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // create the buttons
        initButtons();
        
        // create the input boxes
        initInput();
        
        // create output label
        initOutput();
        
        // create the panels
        initPanels();
        
    }
    
    
    private void initButtons() {
        
        start = new JButton("Start");
        start.setBounds(300, 200, 50, 30);
        start.setActionCommand("Start");
        start.addActionListener(this);
        
        reset  = new JButton("Reset");
        reset.setBounds(300, 200, 50, 30);
        reset.setActionCommand("Reset");
        reset.addActionListener(this);
        
        
    }
    
    
    private void initInput() {
        
        int i;
        
        input = new JFormattedTextField[NUM_INPUTS];
        
        for (i = 0; i < NUM_INPUTS; i++) {
            
            // specify hex format
            input[i] = new JFormattedTextField(createFormatter ("H"));
            input[i].setBounds(0 + (i * 30), 0, 30, 20);
            input[i].setText("0");
            
        }
        
    }
    
    
    private void initOutput() {
        
        output = new JLabel();
        output.setBackground(Color.white);
        output.setText("Result");
        
        
    }
    
    
    private void initPanels() {
        
        int i;
        
        mainPanel = new JPanel ();
        mainPanel.setLayout (new BoxLayout (mainPanel, BoxLayout.PAGE_AXIS));
        inputPanel = new JPanel (new GridLayout (4,4));
        buttonPanel = new JPanel ();
        outputPanel = new JPanel ();
        ioPanel = new JPanel ();
        
        buttonPanel.add(start);
        buttonPanel.add(reset);
        outputPanel.add(output);
        
        for (i = 0; i < NUM_INPUTS; i++) {
            
            inputPanel.add(input[i]);
            
        }
        
        ioPanel.add(inputPanel);
        ioPanel.add(outputPanel);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(outputPanel);
        add(mainPanel);
        
    }
    
    
    // http://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        
        try {
            
            formatter = new MaskFormatter(s);
            
        } catch (java.text.ParseException exc) {
            
            exc.printStackTrace();
            
        }
        
        return formatter;
        
    }

    
    public void actionPerformed(ActionEvent action) {
        
        int print;
        
        if ("Start".equals(action.getActionCommand())) {
            
            // read in each value of the input boxes
            readInput();
            print = four442.run();
            output.setText("" + print);
            
        } else {
            
            // set everything to "0"
            int i = 0;
            
            for (i = 0; i < NUM_INPUTS; i++) {
                
                input[i].setText("0");
                
            }
            
            four442.loadR0(0);
            four442.loadR1(0);
            output.setText("0");
            
        }
        
    }
    
    
    private void readInput () {
        
        int i;
        int[] programme = new int[NUM_INPUTS];
        
        // convert the string[] to an int[]
        for (i = 0; i < NUM_INPUTS; i++) {
            
            
            if ((input[i].getText().compareTo("0") >= 0) && (input[i].getText().compareTo("9") <= 0)) {
                
                programme[i] = input[i].getText().charAt(0) - '0';
                
            } else if ((input[i].getText().compareTo("A") >= 0) && (input[i].getText().compareTo("F") <= 0)) {
                
                programme[i] = input[i].getText().charAt(0) - 'A';
                
            }
            
        }
        
        four442.setProgramme (programme);
        
    }
    
    
    public void print (String s) {
        
        output.setText(s);
        
    }
    

}
