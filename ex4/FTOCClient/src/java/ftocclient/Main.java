/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ftocclient;

import beans.FahrenheitToCelsiusRemote;
import javax.ejb.EJB;
import javax.naming.InitialContext;

/**
 *
 * @author umyong
 */
public class Main {

    @EJB
    private static FahrenheitToCelsiusRemote fahrenheitToCelsius;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Enter degree in Fahrenheit: ");
        double fahrenheit = Double.parseDouble(System.console().readLine());
        double celsius = (5.0 / 9.0) * (fahrenheit - 32.0);
        System.out.println(fahrenheit + " Fahrenheit = " + celsius + " Celsius");
    }
    
}
