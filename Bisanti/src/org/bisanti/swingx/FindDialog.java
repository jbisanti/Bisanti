package org.bisanti.swingx;

import java.awt.Component;
import javax.swing.text.JTextComponent;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 *
 * @author Jason Bisanti
 */
public final class FindDialog 
{
    private static FindFrame findFrame;
    
    private FindDialog(){}
    
    public static void showFind(Component relativeTo, JTextComponent textComponent)
    {
        int caret = 0;
        String text = "";
        if(findFrame != null)
        {            
            if(textComponent.equals(findFrame.getTextComponent()))
            {
                caret = findFrame.getCaretPosition();
                text = findFrame.getText();
            }
            
            findFrame.dispose();
            findFrame = null;
        }
        
        findFrame = new FindFrame();
        findFrame.setTextComponent(textComponent);
        findFrame.setCaretPosition(caret);
        findFrame.setText(text);
        findFrame.setLocationRelativeTo(relativeTo);
        findFrame.setVisible(true);
        findFrame.requestFocus();
    }
    
}
