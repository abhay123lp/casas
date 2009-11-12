package demo.manning.combobox;

//see \Chapter9\1

/**
 * Example 9.1 displays information about popular cars in two 
 * symmetrical panels to provide a natural means of comparison. 
 * 
 * To be realistic, we need to take into account the fact that 
 * any car model can come in several trim lines which actually 
 * determine the car’s characteristics and price. Numerous 
 * characteristics of cars are available on the web.
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ComboBox1 extends JFrame {
    public ComboBox1() {
        super("ComboBoxes [Compare Cars]");
        getContentPane().setLayout(new BorderLayout());
        
        Vector<Car> cars = new Vector<Car>();
        
        Car maxima = new Car("Maxima", "Nissan", new ImageIcon("maxima.gif"));
        maxima.addTrim("GXE", 21499, 19658, "3.0L V6 190-hp");
        maxima.addTrim("SE", 23499, 21118, "3.0L V6 190-hp");
        maxima.addTrim("GLE", 26899, 24174, "3.0L V6 190-hp");
        cars.addElement(maxima);
        
        Car accord = new Car("Accord", "Honda", new ImageIcon("accord.gif"));
        accord.addTrim("LX Sedan", 21700, 19303, "3.0L V6 200-hp");
        accord.addTrim("EX Sedan", 24300, 21614, "3.0L V6 200-hp");
        cars.addElement(accord);
        
        Car camry = new Car("Camry", "Toyota", new ImageIcon("camry.gif"));
        camry.addTrim("LE V6", 21888, 19163, "3.0L V6 194-hp");
        camry.addTrim("XLE V6", 24998, 21884, "3.0L V6 194-hp");
        cars.addElement(camry);
        
        Car lumina = new Car("Lumina", "Chevrolet", new ImageIcon("lumina.gif"));
        lumina.addTrim("LS", 19920, 18227, "3.1L V6 160-hp");
        lumina.addTrim("LTZ", 20360, 18629, "3.8L V6 200-hp");
        cars.addElement(lumina);
        
        Car taurus = new Car("Taurus", "Ford", new ImageIcon("taurus.gif"));
        taurus.addTrim("LS", 17445, 16110, "3.0L V6 145-hp");
        taurus.addTrim("SE", 18445, 16826, "3.0L V6 145-hp");
        taurus.addTrim("SHO", 29000, 26220, "3.4L V8 235-hp");
        cars.addElement(taurus);
        
        Car passat = new Car("Passat", "Volkswagen",
                new ImageIcon("passat.gif"));
        passat.addTrim("GLS V6", 23190, 20855, "2.8L V6 190-hp");
        passat.addTrim("GLX", 26250, 23589, "2.8L V6 190-hp");
        cars.addElement(passat);
        
        
        getContentPane().setLayout(new GridLayout(1, 2, 5, 3));
        CarPanel1 pl = new CarPanel1("Base Model", cars);
        getContentPane().add(pl);
        
        CarPanel1 pr = new CarPanel1("Compare to", cars);
        getContentPane().add(pr);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pl.selectCar(maxima);
        pr.selectCar(accord);
        setResizable(false);
        pack();
        setVisible(true);
    }

    public static void main(String argv[]) {
        new ComboBox1();
    }
}
