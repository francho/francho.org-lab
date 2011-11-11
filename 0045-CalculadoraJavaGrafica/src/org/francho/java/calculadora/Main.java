/**
 * By Francho Joven - http://francho.org/lab/
 *
 * This work is licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 *
 */

package org.francho.java.calculadora;

/**
 * Ejercicio: Calculadora sencilla - Implementar la Interfaz de usuario -
 * Implementar los mtodos siguientes a través de los listeners adecuados: -
 * Sumar - Restar - Multiplicar - Dividir
 * 
 * @author: $Author: franchux $
 * @version: $Rev: 70 $
 * @date: $Date: 2009-04-13 13:06:00 +0200 (lun 13 de abr de 2009) $ $Id:
 *        Main.java 70 2009-04-13 11:06:00Z franchux $
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		VentanaCalculadora calculadora = new VentanaCalculadora();
		calculadora.setVisible(true);
	}
}
