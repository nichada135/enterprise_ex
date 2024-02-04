/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package beans;

import javax.ejb.Remote;

/**
 *
 * @author umyong
 */
@Remote
public interface FahrenheitToCelsiusRemote {

    double fToC(double fahrenheit);
    
}
