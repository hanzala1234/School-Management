
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hanzala
 */
public class Document extends PlainDocument {
   int limit;
   String type;
    public Document(int limit,String type) {
super();    
this.limit=limit;
if(type.equals("number")) this.type="[0-9]";
if(type.equals("letters")) this.type="[^0-9]";
    
    }
      public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    
          if (str == null) return;
if(getLength()<limit&&str.matches(type)){
super.insertString(offset, str, attr);
}
    
  }
      
      
      
}
