package ud.prog3.proyecto;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/** Clase para poner límite al JPassword a la hora de hacer la comprobacion de contraseña
 * @author Alumno
 */
public class JTextFieldLimit extends PlainDocument {
  private int limit;
  // optional uppercase conversion
  //SIRVE PARA LIMITAR LOS TEXTFIELD A UN NUMERO DE CARACTERES
  private boolean toUppercase = false;
  
  JTextFieldLimit(int limit) {
   super();
   this.limit = limit;
   }
   
  JTextFieldLimit(int limit, boolean upper) {
   super();
   this.limit = limit;
   toUppercase = upper;
   }
 
  public void insertString
    (int offset, String  str, AttributeSet attr)
      throws BadLocationException {
   if (str == null) return;

   if ((getLength() + str.length()) <= limit) {
     if (toUppercase) str = str.toUpperCase();
     super.insertString(offset, str, attr);
     }
   }
}