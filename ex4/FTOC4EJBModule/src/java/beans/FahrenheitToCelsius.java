/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package beans;

import javax.ejb.Stateless;

/**
 *
 * @author umyong
 */
@Stateless
public class FahrenheitToCelsius implements FahrenheitToCelsiusRemote {

    public double fToC(double fahrenheit) {
        return (5.0 / 9.0) * (fahrenheit - 32.0);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
