/////////Main Class///////

public class mainRuby {
 
    
    
    public static void main(String[] args) {
        
    
         Rubyg ruby=new Rubyg();
      
       
    
    
    
    } 
}


    /////////Algorthm Class///////

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import javafx.scene.paint.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class RubyAlgorithm {
    

   // define length and width dimension of Array
   public static int length=20;
   public static int width=20;
   
  public static int[][]bo=new int [20][20];
   
    // define 2 arrays to control any movement
     static int new_i[]={-1,0,1,0,1,-1,1,-1};
     static int new_j[]={0,-1,0,1,-1,-1,1,1};
    // to check if is valid to move to the next button or not
    public static boolean isValid(JButton zorar[][],boolean visited[][],int new_i,int new_j){
          return ((new_i>=0)&&(new_i<length)&& (new_j>=0)&&(new_j<width) &&
                  zorar[new_i][new_j].getActionCommand()!="O"&& !visited[new_i][new_j]);
    }
    
    ////////////////// BFS method /////////////////////
           //Xs=i    Ys=j      Xe=x    Ye=y
    public static void BFS(JButton zorar[][],int i,int j,int x,int y){
       
       
       // define new boolean 2 dimension array for nodes
        boolean [][]visited=new boolean[length][width];
       // create new queue to store visited nodes
        Queue<Node> q=new ArrayDeque<>();
        
       //****** start action ******
        visited[i][j]=true;          // make it visited
        // adding new node for the queue
        q.add(new Node(i,j,0,null));   
        
        int min_dist=Integer.MAX_VALUE;    // define min_dist as amaximum value
        Node node=null;                    // define new node to be aparent
        
        /***** start the loop *****/
        while(!q.isEmpty()){
           
            node=q.poll();  // to remove the first node from the queue
            
            i=node.x;
            j=node.y;
            int dist=node.dist;
           
           // check the end 
            if(i==x&&j==y){
               min_dist=dist;
               break;         //to break the loop and go out
            }
            // for any movement
            for(int k=0;k<8;k++){
              if(isValid(zorar, visited, i+new_i[k], j+new_j[k])) {
                 visited[i+new_i[k]][j+new_j[k]]=true;
                 q.add(new Node(i+new_i[k],j+new_j[k],dist+1,node));
               
              } 
            }
           
        }
        //********the end of the loop********//
        if(min_dist!=Integer.MAX_VALUE){
           // to get all indeces of the short path of nodes
           printPath(node);
           // to give the short path color
           for (int v=0;v<bo.length;v++) {
                for (int f=0;f<bo[v].length;f++) {
                    if(bo[v][f]==1){
                      zorar[v][f].setBackground(java.awt.Color.cyan);
                    }
                }
           }
           // to save the colors of start node and the end node
           zorar[Rubyg.Xs][Rubyg.Ys].setBackground(java.awt.Color.GREEN);
           zorar[Rubyg.Xe][Rubyg.Ye].setBackground(java.awt.Color.BLUE);
        }
        else{
              // to view amessage for the user if there is no path to the end
              JOptionPane.showMessageDialog(null,"Not Found path to End");
        }
        
    }
     //to get and select the short path
    public static void printPath(Node node) {
        if (node == null) {
            return;
        }
        printPath(node.parent);
     // to get index of every node
        int s=node.x;
        int e=node.y;
        bo[s][e]=1;
     // System.out.println(node);   //if you want to view nodes in the console
    }

}



    ///////Gragh Class////////



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import jdk.nashorn.internal.parser.TokenType;

public class Rubyg extends JFrame implements ActionListener{
 
    // define array of buttons and get them from method of hatzorar
    JButton[][] zorar = this.hatzorar();     
    //define anew object from class main 
    RubyAlgorithm rub=new RubyAlgorithm();
    
    JPanel p1 = new JPanel();                 //define panel
    JButton start = new JButton("Start");     //define the start button
    JButton end = new JButton("End");         //define the end button
    JButton ruby = new JButton("Ruby");       //define the ruby button
    JButton block = new JButton("Block");     //define the block button
    JButton exit = new JButton("Exit");       //define the exit button
    int anycase = 0;
    public static int Xs = 0;        // Xs&&Ys index of the start
    public static int Ys = 0;
    public static int Xe = 0;        // Xe&&Ye index of the end
    public static int Ye = 0;

    //-------------------------------the constructor--------------------------------// 
    public Rubyg() {

        //attributes of frame
        this.setTitle("Ruby game");
        this.setSize(950, 755);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(150, 0);
        this.setLayout(null);
        
        //attributes of the buttons
        start.setBounds(770, 210, 100, 40);
        start.setFont(new Font("atilic", Font.BOLD, 18));
        start.setForeground(Color.WHITE);
        start.setBackground(Color.gray);
        this.add(start);      // add button start
        
        end.setBounds(770, 270, 100, 40);
        end.setFont(new Font("atilic", Font.BOLD, 18));
        end.setForeground(Color.WHITE);
        end.setBackground(Color.gray);
        this.add(end);       // add button end
        
        block.setBounds(770, 330, 100, 40);
        block.setFont(new Font("atilic", Font.BOLD, 18));
        block.setForeground(Color.WHITE);
        block.setBackground(Color.gray);
        this.add(block);       // add button block
        
        ruby.setBounds(770, 390, 100, 40);
        ruby.setFont(new Font("atilic", Font.BOLD, 18));
        ruby.setForeground(Color.WHITE);
        ruby.setBackground(Color.gray);
        this.add(ruby);       // add button ruby
        
        exit.setBounds(770, 450, 100, 40);
        exit.setFont(new Font("atilic", Font.BOLD, 18));
        exit.setForeground(Color.RED);
        exit.setBackground(Color.gray);
        this.add(exit);       // add button exit

        
        
        //------------------------ Array of buttons ----------------------------      
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                zorar[i][j].setBounds((33 * (j + 1)), (33 * (i + 1)), 30, 30);
                zorar[i][j].setBackground(Color.GRAY);
                zorar[i][j].setForeground(Color.CYAN);
                zorar[i][j].setFont(new Font("atilic", Font.BOLD, 6));
                this.add(zorar[i][j]);   // add buttons
            }
        }
        //----------------------panel-------------------------------------------
        p1.setBounds(0, 0, 1000, 1000);
        p1.setBackground(Color.DARK_GRAY);
        this.add(p1);            // add panel
        //----------------------Actions-----------------------------------------
        // to add actions for all buttons
        start.addActionListener(this);      
        end.addActionListener(this);        
        block.addActionListener(this);      
        ruby.addActionListener(this);       
        block.addActionListener(this);       
        exit.addActionListener(this);

for (int i = 0; i < zorar.length; i++) {
            for (int j = 0; j < zorar[i].length; j++) {
                zorar[i][j].addActionListener(this);
            }
        }

    }

    //-------------------fuction to get buttons--------------
    public JButton[][] hatzorar() {
        JButton[][] zorar = new JButton[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                zorar[i][j] = new JButton();
            }
        }
        return zorar;
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        //Click start
        if (e.getSource() == start) {
            anycase = 1;
        }
        //Click end
        if (e.getSource() == end) {
            anycase = 2;
        }
        //Click block
        if (e.getSource() == block) {
            anycase = 3;
        }
        //Click ruby
        if (e.getSource() == ruby) {
            anycase = 4;
        }
        //Click exit
        if (e.getSource() == exit) {
            System.exit(0);
        }
        //---------------------Switch---------------------.
        switch (anycase) {
            case 1:
                // after click on start
                for (int i = 0; i < zorar.length; i++) {
                    for (int j = 0; j < zorar[i].length; j++) {
                        if (e.getSource() == zorar[i][j]) {
                            zorar[i][j].setBackground(Color.GREEN);
                            zorar[i][j].setText("S");
                            zorar[i][j].setForeground(Color.GREEN);
                            Xs=i;  Ys=j;
                            anycase = 9; //to but one start
                        }
                    }
                }
                break;
            case 2:
                // after click on end
                for (int i = 0; i < zorar.length; i++) {
                    for (int j = 0; j < zorar[i].length; j++) {
                        if (e.getSource() == zorar[i][j]) {
                            zorar[i][j].setBackground(Color.blue);
                            zorar[i][j].setText("E");
                            zorar[i][j].setForeground(Color.BLUE);
                            Xe=i;  Ye=j;
                            anycase = 10;  //to but one end
                        }
                    }
                }
                break;
            case 3:
                // after click on block
                for (int i = 0; i < zorar.length; i++) {
                    for (int j = 0; j < zorar[i].length; j++) {
                        if (e.getSource() == zorar[i][j]) {
                            zorar[i][j].setBackground(Color.red);
                            zorar[i][j].setText("O");
                            zorar[i][j].setForeground(Color.red);
                        }
                    }
                }
                break;
            case 4:
                   // after click on ruby button
                   rub.BFS(zorar, Xs, Ys, Xe, Ye);

         }
       
    }
}




     /////////Node Class//////

public class Node {
    
    
   public int x,y,dist;
    Node parent;
    
    public Node(){
    }
    public Node(int x,int y,int dist,Node parent){
       this.x=x;
       this.y=y;
       this.dist=dist;
       this.parent=parent;
    }

    }
